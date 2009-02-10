package toastwars.client.ui.group;

/*
 * Author: Waldemar Geppart
 * */

import toastwars.client.Controller;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.user.Group;

import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;

public class ReportPanel extends Panel {

	private static ReportPanel	reportPanel;

	private ReportPanel() {

		Game game = Controller.getInstance().getGame();
		Group myGroup = (Group) Controller.getInstance().getUser();
		Company company = null;

		for (Group g : game.getGroupList()) {
			if (g.getUsername().equals(myGroup.getUsername())) {
				company = g.getCompany();
				break;
			}
		}

		setTitle("Analyse-Bericht");
		setPaddings(0, 15, 0, 0);

		TabPanel tabPanel = new TabPanel();
		tabPanel.setSize(965, 390);
		tabPanel.setTabPosition(Position.BOTTOM);
		tabPanel.addListener(new TabChangeListener());

		tabPanel.add(StandardReportPanel.getInstance());

		if (company.getReportListe() != null
				&& company.getReportListe().size() > 0)
			tabPanel.add(ExtraReportPanel.getInstance());

		add(tabPanel);
	}

	public static ReportPanel getInstance() {
		if (reportPanel == null)
			reportPanel = new ReportPanel();
		return reportPanel;
	}
}
