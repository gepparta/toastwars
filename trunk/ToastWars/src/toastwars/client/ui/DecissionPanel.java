package toastwars.client.ui;

import toastwars.client.Controller;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

public class DecissionPanel extends Panel {

	private Button					btnSave;
	private Button					btnEnd;
	private static DecissionPanel	decissionPanel;

	private DecissionPanel() {
		setTitle("Entscheidungen");
		setPaddings(5);
		setSize(580, 400);
		setButtonAlign(Position.CENTER);

		btnSave = new Button("Speichern", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				super.onClick(button, e);
				try {
					Controller.getInstance().save();
				} catch (Exception e1) {
					MessageBox.alert("Speichern fehlgeschlagen!");
				}
				MessageBox.alert("Daten gespeichert!");
			}
		});

		btnEnd = new Button("Runde abschlie&szlig;en",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						super.onClick(button, e);
						MessageBox.alert("Runde abschlie&szlig;en");
					}
				});

		// Formular
		DecissionForm form = new DecissionForm(new Button[] { btnSave, btnEnd });

		add(form);
		addButton(btnSave);
		addButton(btnEnd);
	}

	public static DecissionPanel getInstance() {
		if (decissionPanel == null)
			decissionPanel = new DecissionPanel();
		return decissionPanel;
	}
}
