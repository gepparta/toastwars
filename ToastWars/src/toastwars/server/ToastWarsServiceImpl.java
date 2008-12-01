package toastwars.server;

import toastwars.client.ToastWarsService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ToastWarsServiceImpl extends RemoteServiceServlet implements
		ToastWarsService {

	private static final long	serialVersionUID	= 1L;

	public String login(String name, String pwd) {

		if (name.equals("master"))
			return "Spielleiter";
		else if (name.equals("gruppe"))
			return "Gruppe";
		else
			return null;
	}
}
