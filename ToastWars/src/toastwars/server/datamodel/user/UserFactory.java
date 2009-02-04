package toastwars.server.datamodel.user;
/*
 * @ author Alexander Geppart
 */
public class UserFactory
{
	private static boolean masterCreated = false;

	public static IUser createUser(String className, String username, String password)
	{
		IUser obj = null;
		try
		{
			if (className.equals("Master"))
			{
				obj = Master.getInstance(username, password);
				masterCreated = true;
			} else if (className.equals("Group"))
			{
				if (masterCreated == true)
					obj = new Group(username, password);
				else
					throw new Exception("No master exist");
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return obj;
	}
}
