package toastwars.client.ui;

import java.util.ArrayList;

import toastwars.client.Controller;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.user.Group;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;

public class StartGameWindow extends Window {

	private StartGameWindow	window;
	private ComboBox		userAmount;

	public StartGameWindow() {

		window = this;

		setTitle("Spiel starten");
		setWidth(220);
		setClosable(false);
		setDraggable(false);
		setModal(true);
		setResizable(false);

		FormPanel panel = createPanel();

		add(panel);
		show();
	}

	private FormPanel createPanel() {
		FormPanel panel = new FormPanel();
		panel.setPaddings(10);
		panel.setStyle("background: url(images/starfield_JPG.jpg);");

		userAmount = new ComboBox("Gruppenanzahl");
		Store store = new SimpleStore(new String[] { "col" }, new String[][] {
				new String[] { "4" }, new String[] { "5" },
				new String[] { "6" }, new String[] { "7" },
				new String[] { "8" }, new String[] { "9" },
				new String[] { "10" } });
		store.load();
		userAmount.setStore(store);
		userAmount.setDisplayField("col");
		userAmount.setWidth(60);
		userAmount.setEditable(false);
		userAmount.setListWidth(40);
		userAmount.setValue("4");

		panel.add(userAmount);

		panel.addButton(new Button("Spiel starten",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						Controller
								.getInstance()
								.startGame(
										Integer.parseInt(userAmount.getValue()),
										window);
					}
				}));

		panel.addButton(new Button("Abbrechen", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				window.close();
			}
		}));

		return panel;
	}

	public void startGame(Game game) {
		if (game == null || game.getGroupList().size() <= 0)
			MessageBox.alert("Spiel konnte nicht gestartet werden!");
		else {
			window.close();
			MasterPanel.getInstance().startGame(game);
		}
	}
}
