package toastwars.server.datamodel.user;

import com.google.gwt.user.client.rpc.IsSerializable;
/*
 * @ author Alexander Geppart
 */

public interface IUser extends IsSerializable {
	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	public boolean isMaster();

}
