package toastwars.server.datamodel.user;

public class UserFactory
{
	public IUser createUser(String className, String username, String password)
	{
		IUser obj = null;
		try
		{
			if (className.equals("Master"))
				obj = Master.getInstance(username, password);
			else if(className.equals("Group")) 
			{
				obj = new Group(username, password);
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return obj;
	}
}
