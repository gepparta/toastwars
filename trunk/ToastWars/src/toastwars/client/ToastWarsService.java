package toastwars.client;

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

	public Boolean save(Group group);
	
	public Boolean logout(String name, String pwd);
}
