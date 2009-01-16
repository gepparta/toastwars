package toastwars.client;

import toastwars.server.datamodel.user.Group;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ToastWarsServiceAsync {

	public void login(String name, String pwd, AsyncCallback callback);

	public void save(Group group, AsyncCallback callback);

	public void logout(String name, String pwd, AsyncCallback callback);

	public void startGame(int userAmount, AsyncCallback callback);

	public void endGame(AsyncCallback callback);

	public void simulate(AsyncCallback callback);
}
