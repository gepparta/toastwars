package toastwars.client;

import java.util.ArrayList;

import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ToastWarsServiceAsync {

	public void login(String name, String pwd, AsyncCallback callback);

	public void logout(String name, String pwd, AsyncCallback callback);

	public void startGame(int userAmount, AsyncCallback callback);

	public void endGame(AsyncCallback callback);

	public void simulate(AsyncCallback callback);

	public void getCurrentGame(AsyncCallback callback);

	public void save(Group group, AsyncCallback callback);

	public void createNewToaster(ArrayList<Toaster> toasterList,
			int companyID, AsyncCallback callback);
}
