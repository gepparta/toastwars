package toastwars.client;

import toastwars.client.ui.LoginWindow;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Controller {

	private static Controller	controller;
	private LoginWindow			loginWindow;
	private IUser				user;

	// Benutzer-Parameter
	public static final int		SPIELLEITER	= 1;
	public static final int		GRUPPE		= 2;
	private int					userType;

	private Controller() {
	}

	public static Controller getInstance() {
		if (controller == null)
			controller = new Controller();

		return controller;
	}

	public void login(String name, String pwd, LoginWindow loginW) {
		loginWindow = loginW;
		ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();

		AsyncCallback<IUser> callback = new AsyncCallback<IUser>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(IUser result) {
				user = result;
				loginWindow.login(result);
			}
		};

		service.login(name, pwd, callback);
	}

	public void save() {
		ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();

		AsyncCallback callback = new AsyncCallback() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Object result) {
			}
		};

		((Group) user).getCompany().getToasterList().get(0).setPrice(5);

		service.save((Group) user, callback);
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public IUser getUser() {
		return user;
	}
}
