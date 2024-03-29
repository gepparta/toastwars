package toastwars.client;

/*
 * Author: Waldemar Geppart
 * */

import java.util.ArrayList;

import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface ToastWarsService extends RemoteService {

	public static final String	SERVICE_URI	= "/ToastWarsService";

	public static class Util {

		public static ToastWarsServiceAsync getInstance() {

			ToastWarsServiceAsync instance = (ToastWarsServiceAsync) GWT
					.create(ToastWarsService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
			return instance;
		}
	}

	public IUser login(String name, String pwd);

	public Boolean logout(String name, String pwd);

	public Game startGame(int userAmount);

	public Boolean endGame();

	public Game simulate();

	public Game getPreviousGame();

	public Boolean save(Group group);

	public Boolean createNewToaster(ArrayList<Toaster> toasterList,
			int companyID);
}
