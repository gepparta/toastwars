package toastwars.client.ui;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.TextArea;

public class InfoPanel extends Panel {

	private static InfoPanel	infoPanel;

	private InfoPanel() {
		setTitle("Anleitung");
		setPaddings(0, 15, 0, 0);

		TextArea textArea = new TextArea();
		textArea.setStyle("background: black; border-color: gold; "
				+ "color: gold; padding: 15px; text-align: center;");
		textArea
				.setValue("Spielanleitung f&uuml;r ToastWars Spielanleitung f&uuml;r "
						+ "ToastWars ToastWars Spielanleitung f&uuml;r ToastWars "
						+ "ToastWars ToastWars Spielanleitung f&uuml;r ToastWars "
						+ "ToastWars ToastWars Spielanleitung f&uuml;r ToastWars "
						+ "ToastWars ToastWars Spielanleitung f&uuml;r ToastWars "
						+ "ToastWars ToastWars Spielanleitung f&uuml;r ToastWars");
		textArea.setHideLabel(true);
		textArea.setPreventScrollbars(true);
		textArea.setReadOnly(true);
		textArea.setSize(960, 390);

		add(textArea);
	}

	public static InfoPanel getInstance() {
		if (infoPanel == null)
			infoPanel = new InfoPanel();
		return infoPanel;
	}
}
