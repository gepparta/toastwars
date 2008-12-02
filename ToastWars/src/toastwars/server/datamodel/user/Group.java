package toastwars.server.datamodel.user;

public class Group implements IUser {
	private String	password;
	private String	username;

	public Group() {
	}

	public Group(String username, String password) {
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
		return false;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
