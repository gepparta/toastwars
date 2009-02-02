package toastwars.server;

import rocket.remoting.server.comet.CometConnection;
import rocket.remoting.server.comet.CometServerServlet;
import toastwars.server.datamodel.core.Game;

public class CometServiceImpl extends CometServerServlet
{
	final static int TIMEOUT_SLEEP = 2 * 1000;
	final static int MAXIMUM_BYTES_WRITTEN = 100 * 1024;
	final static int CONNECTION_TIMEOUT = 600 * 1000;

	private static boolean changed;

	public CometServiceImpl()
	{
	}

	public void init()
	{
		this.setConnectionTimeout(CONNECTION_TIMEOUT);
		this.setMaximumBytesWritten(MAXIMUM_BYTES_WRITTEN);
	}

	@Override
	protected void poll(final CometConnection cometConnection)
	{
		try
		{

			Thread.sleep(TIMEOUT_SLEEP);

		} catch (InterruptedException ignore)
		{
			ignore.printStackTrace();
		}
		if (isChanged())
		{
			cometConnection.push(Game.getInstance());
			setChanged(false);
		}
	}

	public boolean isChanged()
	{
		return changed;
	}

	public static void setChanged(boolean c)
	{
		changed = c;
	}

}
