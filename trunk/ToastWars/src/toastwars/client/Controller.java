package toastwars.client;

import toastwars.client.ui.LoginWindow;
import toastwars.client.ui.MasterPanel;
import toastwars.client.ui.StartGameWindow;
import toastwars.client.ui.ToastWars;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;
import toastwars.server.datamodel.user.Master;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class Controller {

	private static Controller	controller;
	private LoginWindow			loginWindow;
	private ToastWars			toastWars;
	private StartGameWindow		startGameWindow;

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

	public void startGame(int userAmount, StartGameWindow window) {
		startGameWindow = window;

		ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();

		AsyncCallback<Game> callback = new AsyncCallback<Game>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Game result) {
				((Master) user).setGame(result);
				startGameWindow.startGame(result);
			}
		};

		service.startGame(userAmount, callback);
	}

	public void endGame() {
		ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();

		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Boolean result) {
				if (result) {
					((Master) user).setGame(null);
					MasterPanel.getInstance().endGame();
				}
			}
		};

		service.endGame(callback);
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
