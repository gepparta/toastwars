package toastwars.server.datamodel.user;

public class Master implements IUser {
	private String	username;
	private String	password;

	public Master() {
	}

	public Master(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isMaster() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
