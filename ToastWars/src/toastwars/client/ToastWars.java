package toastwars.client;

import toastwars.client.sliders.SliderBar;
import toastwars.client.sliders.SliderBar.LabelFormatter;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.IUser;

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
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.PieChart;
import com.rednels.ofcgwt.client.model.elements.BarChart.BarStyle;

public class ToastWars implements EntryPoint {

	private Window		loginWindow;
	private TabPanel	mainPanel;
	private ToastWars	toastWars;

	// Login
	private TextField	userName;
	private TextField	userPass;
	private IUser		user;

	// Ansicht des Spielleiters
	private Panel		configPanel;
	private Panel		gamePanel;

	// Ansicht der Gruppen
	private Panel		infoPanel;
	private Panel		decissionPanel;
	private Panel		reportPanel;
	private NumberField	price;
	private NumberField	research;
	private NumberField	marketing;
	private Button		btnSave;
	private Button		btnEnd;

	// Benutzer-Parameter
	public static int	SPIELLEITER	= 1;
	public static int	GRUPPE		= 2;
	private int			userType;

	public void onModuleLoad() {
		toastWars = this;

		loginWindow = new Window("Anmeldung");
		loginWindow.setSize(300, 150);
		loginWindow.setClosable(false);
		loginWindow.setDraggable(false);
		loginWindow.setModal(true);
		loginWindow.setResizable(false);

		FormPanel loginPanel = new FormPanel();
		loginPanel.setPaddings(10);

		userName = new TextField("Benutzer");
		userName.setValue("gruppe");
		userPass = new TextField("Kennwort");
		userPass.setValue("master");
		userPass.setPassword(true);

		loginPanel.add(userName);
		loginPanel.add(userPass);

		loginPanel.addButton(new Button("Anmelden",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						// User und Passwort checken
						Controller controller = new Controller();
						controller.login(userName.getText(),
								userPass.getText(), toastWars);
					}
				}));

		loginWindow.add(loginPanel);
		loginWindow.show();

		RootPanel.get().add(loginWindow);
	}

	public void login(IUser user) {
		if (user != null) {
			this.user = user;
			if (user instanceof Master)
				userType = SPIELLEITER;
			else if (user instanceof Group)
				userType = GRUPPE;
			loginWindow.close();
			createUI();
		} else
			MessageBox.alert("Anmeldung fehlgeschlagen!");
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

		Label text = new Label("Angemeldet als: " + user.getUsername());
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
		Label text = new Label("Willkommen bei ToastWars " + user.getUsername());
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

	private Component createDecissionPanel() {
		decissionPanel = new Panel("Entscheidungen");
		decissionPanel.setPaddings(5);
		decissionPanel.setSize(580, 290);

		Panel horizontalPanel = new Panel();
		horizontalPanel.setLayout(new HorizontalLayout(0));
		horizontalPanel.setPaddings(0);

		// Formular
		FormPanel form = new FormPanel();
		form.setWidth(250);
		// form.setBorder(false);
		form.setPaddings(30, 15, 0, 0);
		form.setLabelWidth(160);

		price = createNumberField("Preis in &euro;", "Preis", 55, 15, 300, true);
		marketing = createNumberField("Werbung", "Werbung", 55, 1, 100000,
				false);
		research = createNumberField("Forschung und Entwicklung", "FE", 55, 1,
				100000, false);

		form.add(price);
		form.add(marketing);
		form.add(research);

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

		// Slider
		Panel sliderPanel = new Panel();
		sliderPanel.setSize(320, 200);
		sliderPanel.setMargins(0, 0, 0, 0);
		// sliderPanel.setLayout(new VerticalLayout(0));
		// sliderPanel.setBorder(false);

		SliderBar slider = new SliderBar(0, 60);
		slider.setHeight("55");
		slider.setStepSize(1);
		slider.setCurrentValue(15);
		slider.setNumTicks(12);
		slider.setNumLabels(4);
		slider.setLabelFormatter(new LabelFormatter() {
			public String formatLabel(SliderBar slider, double value) {
				return (int) value + "";
			}
		});

		SliderBar slider2 = new SliderBar(0, 100000);
		slider2.setStepSize(10000);
		slider2.setCurrentValue(0);
		slider2.setNumTicks(10);
		slider2.setNumLabels(5);
		slider2.setLabelFormatter(new LabelFormatter() {
			public String formatLabel(SliderBar slider, double value) {
				return (int) value / 1000 + "k";
			}
		});

		sliderPanel.add(slider);
		sliderPanel.add(slider2);

		horizontalPanel.add(form);
		horizontalPanel.add(sliderPanel);

		decissionPanel.add(horizontalPanel);

		return decissionPanel;
	}

	private NumberField createNumberField(String text, String name, int width,
			int value, int maxvalue, boolean decimal) {
		NumberField numField = new NumberField(text, name, width, value);
		numField.setLabelStyle("height:50");
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
				if (price.isValid() && research.isValid()
						&& marketing.isValid()) {
					btnSave.enable();
					btnEnd.enable();
				}
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

	private Component createReportPanel() {
		reportPanel = new Panel("Analyse-Bericht");

		Panel horizPanel = new Panel();
		horizPanel.setLayout(new HorizontalLayout(2));
		horizPanel.setSize(600, 300);
		horizPanel.add(createPieChart());
		horizPanel.add(createBarChart());

		reportPanel.add(horizPanel);

		return reportPanel;
	}

	private ChartWidget createPieChart() {
		ChartWidget chart = new ChartWidget();
		ChartData cd = new ChartData("Sales by Region",
				"font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		PieChart pie = new PieChart();
		pie.setAlpha(0.3f);
		pie.setNoLabels(false);
		pie.setTooltip("#label#<br>#val# Toaster<br>#percent#");
		pie.setAnimate(false);
		pie.setGradientFill(true);
		pie.setColours("#ff0000", "#00ff00", "#0000ff", "#ff9900");
		pie.addSlices(new PieChart.Slice(1000, "Gruppe 1"));
		pie.addSlices(new PieChart.Slice(2000, "Gruppe 2"));
		pie.addSlices(new PieChart.Slice(6000, "Gruppe 3"));
		pie.addSlices(new PieChart.Slice(1000, "Gruppe 4"));
		cd.addElements(pie);
		chart.setSize("300", "300");
		chart.setJsonData(cd.toString());
		return chart;
	}

	private ChartWidget createBarChart() {
		ChartWidget chart = new ChartWidget();
		ChartData cd = new ChartData("Kapital nach Runde 1",
				"font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");

		XAxis xa = new XAxis();
		xa.setLabels("Gruppe 1", "Gruppe 2", "Gruppe 3", "Gruppe 4");
		xa.setMax(3);
		cd.setXAxis(xa);

		YAxis ya = new YAxis();
		ya.setSteps(40000);
		ya.setMax(200000);
		cd.setYAxis(ya);

		BarChart bchart = new BarChart(BarStyle.GLASS);
		bchart.setColour("#00aa00");
		bchart.setTooltip("#val# &#8364;");
		bchart.addValues(40000, 60000, 30000, 150000);
		cd.addElements(bchart);

		chart.setSize("300", "250");
		chart.setJsonData(cd.toString());

		return chart;
	}
}
