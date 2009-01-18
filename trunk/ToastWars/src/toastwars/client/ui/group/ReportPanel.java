package toastwars.client.ui.group;

import toastwars.client.Controller;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.user.Group;

import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;

public class ReportPanel extends Panel {

	private static ReportPanel	reportPanel;

	private ReportPanel() {

		Company company = ((Group) Controller.getInstance().getUser())
				.getCompany();

		setTitle("Analyse-Bericht");
		setPaddings(0, 15, 0, 0);

		TabPanel tabPanel = new TabPanel();
		tabPanel.setSize(965, 390);
		tabPanel.setTabPosition(Position.BOTTOM);

		tabPanel.add(StandardReportPanel.getInstance());

		if (company.isMarketResearchReportON())
			tabPanel.add(ExtraReportPanel.getInstance());

		add(tabPanel);
	}

	public static ReportPanel getInstance() {
		if (reportPanel == null)
			reportPanel = new ReportPanel();
		return reportPanel;
	}
}
