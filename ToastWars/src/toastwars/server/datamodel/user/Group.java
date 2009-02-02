package toastwars.server.datamodel.user;

import com.google.gwt.user.client.rpc.IsSerializable;
import toastwars.server.datamodel.core.Company;

public class Group implements IUser, IsSerializable
{
	private String password;
	private String username;
	private Company company;
	private boolean isOnline;
	private Status status;
	public Group()
	{
	}

	protected Group(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	public void completeRound()
	{
		this.setStatus(Status.COMPLETED);
	}
	
	public void setInactive()
	{
		this.setStatus(Status.INACTIVE);
	}
	
	public void setStarted()
	{
		this.setStatus(Status.STARTED);
	}

	public Company getCompany()
	{
		return company;
	}

	public String getPassword()
	{
		return password;
	}

	public Status getStatus()
	{
		return status;
	}

	public String getUsername()
	{
		return username;
	}

	public boolean isMaster()
	{
		return false;
	}

	public boolean isOnline()
	{
		return isOnline;
	}

	public void save()
	{
		this.setStatus(Status.EDITED);
	}

	public void setCompany(Company company)
	{
		if (this.company == null)
			this.company = company;
	}

	public void setOnline(boolean isOnline)
	{
		this.isOnline = isOnline;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
}
