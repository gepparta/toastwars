package toastwars.client;

import com.google.gwt.core.client.GWT;

public class Controller {

	public String login(String name, String pwd) {

		String returnValue = null;

		ToastWarsService service = GWT.create(ToastWarsService.class);
		returnValue = service.login(name, pwd);

		return returnValue;
	}
}
