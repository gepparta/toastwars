package toastwars.client;

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

	public String login(String name, String pwd);

}
