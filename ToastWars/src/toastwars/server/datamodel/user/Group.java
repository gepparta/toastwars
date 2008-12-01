package toastwars.server.datamodel.user;

public class Group implements User
{
	private String password;
	private String username;
	public Group(String password, String username)
	{
		this.password = password;
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}

	public String getUsername()
	{
		return username;
	}

	public boolean isMaster()
	{
		return false;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
}
