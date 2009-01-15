package toastwars.client;

import toastwars.client.ui.LoginWindow;
import toastwars.client.ui.ToastWars;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Controller {

	private static Controller	controller;
	private LoginWindow			loginWindow;
	private ToastWars			toastWars;

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

	public void logout(ToastWars tw) {
		toastWars = tw;

		if (userType == SPIELLEITER) {
			toastWars.reloadPage(true);
			return;
		}

		ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();

		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Boolean result) {
				toastWars.reloadPage(result.booleanValue());
			}
		};

		service.logout(user.getUsername(), user.getPassword(), callback);
	}

	public void startGame(int userAmount) {
		// ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();
		//
		// AsyncCallback<ArrayList<Group>> callback = new
		// AsyncCallback<ArrayList<Group>>() {
		// public void onFailure(Throwable caught) {
		// }
		//
		// public void onSuccess(ArrayList<Group> result) {
		//				
		// }
		// };
		//
		// service.startGame(userAmount, callback);
	}

	public void endGame() {

	}

	public void simulate() {

	}

	public void save() throws Exception {
		ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();

		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Boolean result) {
			}
		};

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
