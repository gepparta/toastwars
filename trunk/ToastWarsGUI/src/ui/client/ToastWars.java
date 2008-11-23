package ui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;

public class ToastWars implements EntryPoint {

	public void onModuleLoad() {
		Panel panel = new Panel();
		panel.setBorder(false);
		panel.setPaddings(15);

		final Panel headerPanel = createHeaderPanel();
		final TreePanel treePanelNavigation = createNavigationPanel();
		final Panel mainPanel = createMainPanel();

		Panel horizontalPanel = new Panel();
		horizontalPanel.setLayout(new HorizontalLayout(2));
		horizontalPanel.add(treePanelNavigation);
		horizontalPanel.add(mainPanel);

		Panel verticalPanel = new Panel();
		verticalPanel.setLayout(new VerticalLayout(2));
		verticalPanel.add(headerPanel);
		verticalPanel.add(horizontalPanel);

		panel.add(verticalPanel);

		RootPanel.get().add(panel);
	}

	private Panel createHeaderPanel() {
		final Panel panel = new Panel();
		panel.setWidth(802);
		panel.setHeight(100);
		
		return panel;
	}

	private Panel createMainPanel() {
		
		final Panel panel = new Panel("&Uuml;bersicht");
		panel.setWidth(600);
		panel.setHeight(400);
		
		return panel;
	}

	private TreePanel createNavigationPanel() {
		final TreePanel treePanelNavigation = new TreePanel();
		treePanelNavigation.setTitle("Men&uuml;");
		treePanelNavigation.setWidth(200);
		treePanelNavigation.setHeight(400);
		treePanelNavigation.setUseArrows(true);

		TreeNode root = new TreeNode("Menü");

		TreeNode info = new TreeNode("Information");
		TreeNode decissions = new TreeNode("Entscheidungen");
		TreeNode report = new TreeNode("Analyse-Bericht");

		root.appendChild(info);
		root.appendChild(decissions);
		root.appendChild(report);

		treePanelNavigation.setRootVisible(false);
		treePanelNavigation.setRootNode(root);
		root.setExpanded(true);

		return treePanelNavigation;
	}
}
