package toastwars.server;

import javax.servlet.ServletException;

import toastwars.client.ToastWarsService;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.IUser;
import toastwars.server.datamodel.user.UserFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ToastWarsServiceImpl extends RemoteServiceServlet implements
		ToastWarsService {

	private static final long	serialVersionUID	= 1L;
	private Master				master;

	@Override
	public void init() throws ServletException {
		super.init();
		master = (Master) UserFactory.createUser("Master", "master", "master");
	}

	public IUser login(String name, String pwd) {

		IUser user = null;

		if (name.equals(master.getUsername())
				&& pwd.equals(master.getPassword()))
			user = master;
		else if (name.equals("gruppe"))
			user = UserFactory.createUser("Group", name, pwd);

		return user;
	}
}
