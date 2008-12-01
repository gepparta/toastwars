package toastwars.server.datamodel.user;

public interface User
{
	public String getUsername();
	public void setUsername(String username);
	
	public String getPassword();
	public void setPassword(String password);
	
	public boolean isMaster();

}
