package toastwars.client;

import java.util.ArrayList;

import toastwars.client.ui.LoginWindow;
import toastwars.client.ui.ToastWars;
import toastwars.client.ui.group.DecissionPanel;
import toastwars.client.ui.master.MasterPanel;
import toastwars.client.ui.master.StartGameWindow;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
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
	
	// attributes for group only
	private Game				previousGame;
	private ArrayList<Toaster>	newToasterList;

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

				if (result != null) {
					if (result instanceof Group)
						getPreviousGame();
					else if (result instanceof Master) {
						loginWindow.loginSuccess(result);
					}
				} else
					loginWindow.loginFailure();

			}
		};

		service.login(name, pwd, callback);
	}

	public void getPreviousGame() {
		ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();

		AsyncCallback<Game> callback = new AsyncCallback<Game>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Game result) {
				previousGame = result;

				if (previousGame != null) {
					Type.TYPE1.setMarketVolumeTT1(previousGame.getUserAmount());
					Type.TYPE2.setMarketVolumeTT2(previousGame.getUserAmount());
					Type.TYPE3.setMarketVolumeTT3(previousGame.getUserAmount());
					loginWindow.loginSuccess(user);
				} else
					loginWindow.loginFailure();
			}
		};

		service.getPreviousGame(callback);
	}

	public void logout(ToastWars tw) {
		toastWars = tw;

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
		ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();

		AsyncCallback<Game> callback = new AsyncCallback<Game>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Game result) {
				((Master) user).setGame(result);
				MasterPanel.getInstance().simulate(result);
			}
		};

		service.simulate(callback);
	}

	public void save() throws Exception {
		ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();

		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Boolean result) {
				DecissionPanel.getInstance().createUserMessage(result);
			}
		};

		service.save((Group) user, callback);
	}

	public void createNewToaster(ArrayList<Toaster> toasterList)
			throws Exception {
		newToasterList = toasterList;
		ToastWarsServiceAsync service = ToastWarsService.Util.getInstance();

		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Boolean success) {
				try {
					if (!success) {
						DecissionPanel.getInstance().createUserMessage(false);
						throw new Exception(
								"Waldi: Toaster konnte nicht erzeugt werden");
					}
					newToasterList.clear();
					save();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		service.createNewToaster(toasterList, ((Group) user).getCompany()
				.getCompanyID(), callback);
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

	public Game getGame() {
		return previousGame;
	}
}
