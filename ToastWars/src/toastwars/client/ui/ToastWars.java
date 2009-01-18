package toastwars.client.ui;

import toastwars.client.Controller;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Node;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.event.TreeNodeListenerAdapter;

public class ToastWars implements EntryPoint {

	private ToastWars	toastWars;
	private Controller	controller;
	private TabPanel	mainPanel;
	private LoginWindow	loginWindow;

	public void onModuleLoad() {

		toastWars = this;
		controller = Controller.getInstance();

		loginWindow = new LoginWindow(toastWars);

		RootPanel.get().add(loginWindow);
	}

	public void createUI() {
		Panel root = new Panel();
		root.setBorder(false);
		root.setLayout(new VerticalLayout(0));

		Panel header = new Panel();
		header.setSize(1200, 150);
		header.setBorder(false);
		header.setPaddings(0);
		header.setStyle("background: url(images/logo_1200.jpg);");

		Panel body = new Panel();
		body.setBorder(false);
		body.setPaddings(0, 2, 1, 0);
		body.setStyle("background: url(images/starfield_JPG.jpg);");

		Panel horizontalPanel = new Panel();
		horizontalPanel.setLayout(new HorizontalLayout(2));

		if (controller.getUserType() == Controller.GRUPPE) {
			TreePanel navigationPanel = createNavigationPanel();
			horizontalPanel.add(navigationPanel);
		}
		mainPanel = createMainPanel();

		horizontalPanel.add(mainPanel);

		final Panel footerPanel = createFooterPanel();

		Panel verticalPanel = new Panel();
		verticalPanel.setLayout(new VerticalLayout(2));
		verticalPanel.add(horizontalPanel);
		verticalPanel.add(footerPanel);

		body.add(verticalPanel);

		root.add(header);
		root.add(body);

		RootPanel.get("main").add(root);
	}

	private Panel createFooterPanel() {

		Panel footerPanel = new Panel();
		footerPanel.setLayout(new HorizontalLayout(10));
		footerPanel.setSize(1195, 25);
		footerPanel.setPaddings(3, 5, 0, 0);

		Label text = new Label("Angemeldet als: "
				+ controller.getUser().getUsername());
		text.setStyle("font:bold 11px tahoma,arial,verdana,sans-serif;");

		footerPanel.add(text);
		footerPanel.add(new Button("Abmelden", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				Controller.getInstance().logout(toastWars);
			}
		}));

		Label round = new Label("Runde: "
				+ Controller.getInstance().getGame().getCurrentRound());
		round.setWidth(930);
		round.setStyle("font: bold 11px tahoma, arial, verdana, sans-serif;"
				+ "text-align: right;");
		footerPanel.add(round);

		return footerPanel;
	}

	public native void reloadPage(boolean success)/*-{
       if(success == true)
       		$wnd.location.reload();
       else
       		@com.gwtext.client.widgets.MessageBox::alert(Ljava/lang/String;)("Abmelden fehlgeschlagen!");
   }-*/;

	private TabPanel createMainPanel() {

		TabPanel mainPanel = new TabPanel();
		mainPanel.setSize(995, 450);
		mainPanel.setBorder(true);
		mainPanel.setPaddings(15, 0, 0, 0);

		if (controller.getUserType() == Controller.GRUPPE) {
			// Willkommen Panel
			Panel welcome = new Panel("Willkommen");
			welcome.setStyle("text-align:center");
			Label text = new Label("Willkommen bei ToastWars "
					+ controller.getUser().getUsername());
			text.setStyle("font:bold 20px tahoma,arial,verdana,sans-serif");
			welcome.add(text);
			mainPanel.add(welcome);
		} else {
			mainPanel.add(MasterPanel.getInstance());
			mainPanel.setWidth(1195);
		}
		mainPanel.setActiveTab(0);

		return mainPanel;
	}

	private TreePanel createNavigationPanel() {
		final TreePanel treePanelNavigation = new TreePanel("Men&uuml;");
		treePanelNavigation.setWidth(197);
		treePanelNavigation.setHeight(450);
		treePanelNavigation.setUseArrows(true);
		treePanelNavigation.setPaddings(5);

		TreeNode root = new TreeNode("menu");

		TreeNode info = new TreeNode("Anleitung");
		TreeNode decissions = new TreeNode("Entscheidungen");
		TreeNode report = new TreeNode("Analyse-Bericht");

		info.addListener(new TreeNodeListenerAdapter() {
			public void onClick(Node node, EventObject e) {
				super.onClick(node, e);
				createInitialContentGroup();
				activateTab(1);
			}
		});

		decissions.addListener(new TreeNodeListenerAdapter() {
			public void onClick(Node node, EventObject e) {
				super.onClick(node, e);
				createInitialContentGroup();
				activateTab(2);
			}
		});

		report.addListener(new TreeNodeListenerAdapter() {
			public void onClick(Node node, EventObject e) {
				super.onClick(node, e);
				createInitialContentGroup();
				activateTab(3);
			}
		});

		root.appendChild(info);
		root.appendChild(decissions);
		root.appendChild(report);

		treePanelNavigation.setRootVisible(false);
		treePanelNavigation.setRootNode(root);
		root.setExpanded(true);

		return treePanelNavigation;
	}

	private void createInitialContentGroup() {
		if (mainPanel.getItems().length < 2) {
			mainPanel.add(InfoPanel.getInstance());
			mainPanel.add(DecissionPanel.getInstance());
			mainPanel.add(ReportPanel.getInstance());
		}
	}

	private void activateTab(int activeTab) {
		int tabAmount = mainPanel.getItems().length;
		for (int i = 0; i < tabAmount; i++) {
			if (i == activeTab) {
				mainPanel.unhideTabStripItem(activeTab);
				mainPanel.activate(activeTab);
			} else
				mainPanel.hideTabStripItem(i);
		}
	}

}
