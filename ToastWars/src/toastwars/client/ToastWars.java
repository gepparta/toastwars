package toastwars.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Node;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.event.TreeNodeListenerAdapter;

public class ToastWars implements EntryPoint {

	private Window		loginWindow;
	private TabPanel	mainPanel;

	// Login
	private TextField	userName;
	private TextField	userPass;

	// Ansicht des Spielleiters
	private Panel		configPanel;
	private Panel		gamePanel;

	// Ansicht der Gruppen
	private Panel		infoPanel;
	private Panel		decissionPanel;
	private Panel		reportPanel;
	private Button		btnSave;
	private Button		btnEnd;

	// Benutzer-Parameter
	private static int	SPIELLEITER	= 1;
	private static int	GRUPPE		= 2;
	private int			userType;

	public void onModuleLoad() {
		loginWindow = new Window("Anmeldung");
		loginWindow.setSize(300, 150);
		loginWindow.setClosable(false);
		loginWindow.setDraggable(false);
		loginWindow.setModal(true);
		loginWindow.setResizable(false);

		FormPanel loginPanel = new FormPanel();
		loginPanel.setPaddings(10);

		userName = new TextField("Benutzer");
		userPass = new TextField("Kennwort");
		userPass.setPassword(true);

		loginPanel.add(userName);
		loginPanel.add(userPass);

		loginPanel.addButton(new Button("Anmelden",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						// User und Passwort checken
						Controller controller = new Controller();
						String user = controller.login(userName.getText(),
								userPass.getText());

						if (user != null) {
							loginWindow.close();
							createUI();
						} else
							MessageBox.alert("Anmeldung fehlgeschlagen!");
					}
				}));

		loginWindow.add(loginPanel);
		loginWindow.show();

		RootPanel.get().add(loginWindow);
	}

	private void createUI() {
		Panel panel = new Panel();
		panel.setBorder(false);

		TreePanel navigationPanel = createNavigationPanel();
		mainPanel = createMainPanel();

		HorizontalLayout horizLayout = new HorizontalLayout(2);

		Panel horizontalPanel = new Panel();
		horizontalPanel.setLayout(horizLayout);
		horizontalPanel.add(navigationPanel);
		horizontalPanel.add(mainPanel);

		final Panel footerPanel = createFooterPanel();

		Panel verticalPanel = new Panel();
		verticalPanel.setLayout(new VerticalLayout(2));
		verticalPanel.add(horizontalPanel);
		verticalPanel.add(footerPanel);

		panel.add(verticalPanel);

		RootPanel.get("main").add(panel);
	}

	private Panel createFooterPanel() {

		Panel footerPanel = new Panel();
		footerPanel.setLayout(new HorizontalLayout(0));
		footerPanel.setSize(802, 22);
		footerPanel.setPaddings(5);

		Label text = new Label("Angemeldet als: " + userType);
		text
				.setStyle("color:#15428b;font:bold 11px tahoma,arial,verdana,sans-serif");

		footerPanel.add(text);

		return footerPanel;
	}

	private TabPanel createMainPanel() {

		TabPanel mainPanel = new TabPanel();
		mainPanel.setSize(600, 400);

		// Willkommen Panel
		Panel welcome = new Panel("Willkommen");
		welcome.setPaddings(5);
		welcome.setStyle("text-align:center");
		Label text = new Label("Willkommen bei ToastWars " + userType);
		text
				.setStyle("color:#15428b;font:bold 20px tahoma,arial,verdana,sans-serif");
		welcome.add(text);

		mainPanel.add(welcome);
		mainPanel.setActiveTab(0);

		return mainPanel;
	}

	private TreePanel createNavigationPanel() {
		final TreePanel treePanelNavigation = new TreePanel("Men&uuml;");
		treePanelNavigation.setWidth(200);
		treePanelNavigation.setHeight(400);
		treePanelNavigation.setUseArrows(true);

		TreeNode root = new TreeNode("menu");

		if (userType == SPIELLEITER) {

			TreeNode config = new TreeNode("Konfiguration");
			config.addListener(new TreeNodeListenerAdapter() {
				public void onClick(Node node, EventObject e) {
					super.onClick(node, e);

					if (configPanel == null || gamePanel == null) {
						mainPanel.add(createConfigPanel());
						mainPanel.add(createGamePanel());
					}

					mainPanel.hideTabStripItem(0);
					mainPanel.hideTabStripItem(2);
					mainPanel.unhideTabStripItem(1);
					mainPanel.activate(1);
				}
			});

			TreeNode game = new TreeNode("Aktuelles Spiel");
			game.addListener(new TreeNodeListenerAdapter() {
				public void onClick(Node node, EventObject e) {
					super.onClick(node, e);

					if (configPanel == null || gamePanel == null) {
						mainPanel.add(createConfigPanel());
						mainPanel.add(createGamePanel());
					}

					mainPanel.hideTabStripItem(0);
					mainPanel.hideTabStripItem(1);
					mainPanel.unhideTabStripItem(2);
					mainPanel.activate(2);
				}
			});

			root.appendChild(config);
			root.appendChild(game);

		} else if (userType == GRUPPE) {

			TreeNode info = new TreeNode("Information");
			TreeNode decissions = new TreeNode("Entscheidungen");
			TreeNode report = new TreeNode("Analyse-Bericht");

			info.addListener(new TreeNodeListenerAdapter() {
				public void onClick(Node node, EventObject e) {
					super.onClick(node, e);

					if (infoPanel == null || decissionPanel == null
							|| reportPanel == null) {
						mainPanel.add(createInfoPanel());
						mainPanel.add(createDecissionPanel());
						mainPanel.add(createReportPanel());
					}

					mainPanel.hideTabStripItem(0);
					mainPanel.hideTabStripItem(2);
					mainPanel.hideTabStripItem(3);
					mainPanel.unhideTabStripItem(1);
					mainPanel.activate(1);
				}
			});

			decissions.addListener(new TreeNodeListenerAdapter() {
				public void onClick(Node node, EventObject e) {
					super.onClick(node, e);

					if (infoPanel == null || decissionPanel == null
							|| reportPanel == null) {
						mainPanel.add(createInfoPanel());
						mainPanel.add(createDecissionPanel());
						mainPanel.add(createReportPanel());
					}

					mainPanel.hideTabStripItem(0);
					mainPanel.hideTabStripItem(1);
					mainPanel.hideTabStripItem(3);
					mainPanel.unhideTabStripItem(2);
					mainPanel.activate(2);
				}
			});

			report.addListener(new TreeNodeListenerAdapter() {
				public void onClick(Node node, EventObject e) {
					super.onClick(node, e);

					if (infoPanel == null || decissionPanel == null
							|| reportPanel == null) {
						mainPanel.add(createInfoPanel());
						mainPanel.add(createDecissionPanel());
						mainPanel.add(createReportPanel());
					}

					mainPanel.hideTabStripItem(0);
					mainPanel.hideTabStripItem(1);
					mainPanel.hideTabStripItem(2);
					mainPanel.unhideTabStripItem(3);
					mainPanel.activate(3);
				}
			});

			root.appendChild(info);
			root.appendChild(decissions);
			root.appendChild(report);
		}

		treePanelNavigation.setRootVisible(false);
		treePanelNavigation.setRootNode(root);
		root.setExpanded(true);

		return treePanelNavigation;
	}

	private Component createReportPanel() {
		reportPanel = new Panel("Analyse-Bericht");
		reportPanel.add(new Label("Analyse-Bericht"));

		return reportPanel;
	}

	private Component createDecissionPanel() {
		decissionPanel = new Panel("Entscheidungen");
		decissionPanel.setPaddings(5);

		FormPanel form = new FormPanel();
		form.setPaddings(5);
		form.setLabelWidth(200);

		form.add(createNumberField("Preis in &euro;", "Preis", 55, 15, 300,
				true));
		form.add(createNumberField("Werbung", "Werbung", 55, 1, 100000, false));
		form.add(createNumberField("Forschung und Entwicklung", "FE", 55, 1,
				100000, false));

		btnSave = new Button("Speichern", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				super.onClick(button, e);
				MessageBox.alert("Daten speichern");
			}
		});
		form.addButton(btnSave);

		btnEnd = new Button("Runde abschlie&szlig;en",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						super.onClick(button, e);
						MessageBox.alert("Runde abschlie&szlig;en");
					}
				});
		form.addButton(btnEnd);

		decissionPanel.add(form);

		return decissionPanel;
	}

	private NumberField createNumberField(String text, String name, int width,
			int value, int maxvalue, boolean decimal) {
		NumberField numField = new NumberField(text, name, width, value);
		numField.setAllowNegative(false);
		numField.setMaxValue(maxvalue);
		numField.setMaxText("Der maximale Wert f&uuml;r dieses Feld ist: "
				+ maxvalue);
		numField.setNanText("Ung&uuml;ltige Zahl");

		numField.setDecimalPrecision(2);
		numField.setDecimalSeparator(",");
		numField.setAllowDecimals(decimal);

		numField.setStyle("text-align:right");

		numField.addListener(new FieldListenerAdapter() {
			public void onInvalid(Field field, String msg) {
				super.onInvalid(field, msg);
				btnSave.disable();
				btnEnd.disable();
			}

			public void onValid(Field field) {
				super.onValid(field);
				btnSave.enable();
				btnEnd.enable();
			}
		});

		return numField;
	}

	private Component createInfoPanel() {
		infoPanel = new Panel("Information");
		infoPanel.setPaddings(5);

		Label text = new Label("Informationen zum Unternehmen");
		text
				.setStyle("color:#15428b;font:bold 11px tahoma,arial,verdana,sans-serif");
		infoPanel.add(text);

		return infoPanel;
	}

	private Component createGamePanel() {
		gamePanel = new Panel("Aktuelles Spiel");
		gamePanel.add(new Label("Game Panel"));

		return gamePanel;
	}

	private Component createConfigPanel() {
		configPanel = new Panel("Konfiguration");
		configPanel.add(new Label("Konfigurations Panel"));

		return configPanel;
	}
}
