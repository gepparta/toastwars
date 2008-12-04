package toastwars.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.gwtext.client.widgets.Panel;

public class Slider extends Panel {

	public Slider() {
		setSize(300, 20);
		setBorder(false);
		Image back = new Image(GWT.getModuleBaseURL()
				+ "images/slider_back.jpg");
		add(back);
	}
}
