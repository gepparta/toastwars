package toastwars.server.datamodel.user;

import toastwars.server.datamodel.core.Company;

public class Group implements IUser
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

	public Company getCompany()
	{
		return company;
	}

	public void setCompany(Company company)
	{
		if (this.company == null)
			this.company = company;
	}

	public boolean isOnline()
	{
		return isOnline;
	}

	public void setOnline(boolean isOnline)
	{
		this.isOnline = isOnline;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public void save()
	{
		this.setStatus(Status.EDITED);
	}

	public void completeRound()
	{
		this.setStatus(Status.COMPLETED);
	}
}
