package toastwars.client.comet;

/*
 * Author: Waldemar Geppart, Alexander Geppart
 * */

import rocket.remoting.client.CometCallback;
import rocket.remoting.client.GwtSerializationCometClient;
import toastwars.client.ui.master.MasterPanel;
import toastwars.server.datamodel.core.Game;
import com.google.gwt.core.client.GWT;

public class CometController
{
	static GameCometClient cometClient = (GameCometClient) GWT.create(GameCometClient.class);
	public static void startPushService()
	{
		cometClient.setServiceEntryPoint(GWT.getModuleBaseURL() + "CometService");
		cometClient.setCallback(new CometCallback()
		{
			public void onPayload(Object object)
			{
				Game game = (Game) object;
				MasterPanel.getInstance().refreshGrid(game);
			}

			public void onTerminate()
			{
				System.out.println("Comet terminated");
			}

			public void onFailure(Throwable throwable)
			{
				throwable.printStackTrace();
			}
		});

		cometClient.start();
	}
	
	public static void endPushService()
	{
		cometClient.stop();
	}

	/**
	 * @comet-payloadType toastwars.server.datamodel.core.Game
	 */
	abstract public static class GameCometClient extends GwtSerializationCometClient
	{

	}
}
