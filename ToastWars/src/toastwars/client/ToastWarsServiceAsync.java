package toastwars.client;

import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ToastWarsServiceAsync {

	public void login(String name, String pwd, AsyncCallback callback);

	public void save(Group group, AsyncCallback callback);
}
