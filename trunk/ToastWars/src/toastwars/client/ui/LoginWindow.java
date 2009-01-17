package toastwars.client.ui;

import toastwars.client.Controller;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;
import toastwars.server.datamodel.user.Master;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;

public class LoginWindow extends Window {

	private LoginWindow	loginWindow;
	private TextField	userName;
	private TextField	userPass;
	private ToastWars	toastWars;
	private Controller	controller;

	public LoginWindow(ToastWars tw) {

		loginWindow = this;
		toastWars = tw;
		controller = Controller.getInstance();

		setTitle("Anmeldung");
		setSize(300, 150);
		setClosable(false);
		setDraggable(false);
		setModal(true);
		setResizable(false);

		FormPanel loginPanel = createLoginPanel();

		add(loginPanel);
		show();
	}

	private FormPanel createLoginPanel() {
		FormPanel loginPanel = new FormPanel();
		loginPanel.setPaddings(10);
		loginPanel.setStyle("background: url(images/starfield_JPG.jpg);");

		userName = new TextField("Benutzer");
		userName.setValue("Gruppe 1");
		userPass = new TextField("Kennwort");
		userPass.setValue("pass1");
		userPass.setPassword(true);

		loginPanel.add(userName);
		loginPanel.add(userPass);

		loginPanel.addButton(new Button("Anmelden",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						// User und Passwort checken
						Controller.getInstance().login(userName.getText(),
								userPass.getText(), loginWindow);
					}
				}));

		loginPanel.addButton(new Button("Master", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				userName.setValue("Master");
				userPass.setValue("master");
			}
		}));

		loginPanel.addButton(new Button("Gruppe", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				userName.setValue("Gruppe 2");
				userPass.setValue("pass2");
			}
		}));

		return loginPanel;
	}

	public void login(IUser user) {
		if (user != null) {
			if (user instanceof Master)
				controller.setUserType(Controller.SPIELLEITER);
			else if (user instanceof Group)
				controller.setUserType(Controller.GRUPPE);
			loginWindow.close();
			toastWars.createUI();
		} else
			MessageBox.alert("Anmeldung fehlgeschlagen!");
	}
}
