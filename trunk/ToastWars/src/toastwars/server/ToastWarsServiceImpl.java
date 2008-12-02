package toastwars.server;

import toastwars.client.ToastWarsService;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.IUser;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ToastWarsServiceImpl extends RemoteServiceServlet implements
		ToastWarsService {

	private static final long	serialVersionUID	= 1L;

	public IUser login(String name, String pwd) {

		if (name.equals("master"))
			return new Master(name, pwd);
		else if (name.equals("gruppe"))
			return new Group(name, pwd);
		else
			return null;
	}
}
