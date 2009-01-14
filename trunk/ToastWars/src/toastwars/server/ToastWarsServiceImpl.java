package toastwars.server;

import javax.servlet.ServletException;
import toastwars.client.ToastWarsService;
import toastwars.server.dao.DAOUser;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.IUser;
import toastwars.server.datamodel.user.UserFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ToastWarsServiceImpl extends RemoteServiceServlet implements ToastWarsService
{

	private static final long serialVersionUID = 1L;
	private Master master;

	@Override
	public void init() throws ServletException
	{
		super.init();
		master = (Master) UserFactory.createUser("Master", "Master", "master");
	}

	public IUser login(String name, String pwd)
	{

		IUser user = null;

		if (name.equals(master.getUsername()) && pwd.equals(master.getPassword()))
			user = master;
		else if (name.equals("gruppe"))
			try
			{
				user = DAOUser.findUser(name, pwd);
			} catch (Exception e)
			{
				return null;
			}
		return user;
	}

	public void save(Group group)
	{
		DAOUser.saveUser(group);
	}
}
