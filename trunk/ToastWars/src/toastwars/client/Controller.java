package toastwars.client;

import toastwars.server.datamodel.user.IUser;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Controller {

	private ToastWars	toastWars;

	public void login(String name, String pwd, ToastWars tw) {
		toastWars = tw;
		ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();

		AsyncCallback<IUser> callback = new AsyncCallback<IUser>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(IUser result) {
				toastWars.login(result);
			}
		};

		service.login(name, pwd, callback);
	}
}
