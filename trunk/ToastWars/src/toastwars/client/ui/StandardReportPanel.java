package toastwars.client.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import toastwars.client.Controller;
import toastwars.server.datamodel.user.Group;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.PieChart;
import com.rednels.ofcgwt.client.model.elements.BarChart.BarStyle;

public class StandardReportPanel extends Panel {

	private static StandardReportPanel	reportPanel;
	private final String				COLOR_1		= "#ff0000";
	private final String				COLOR_2		= "#00ff00";
	private final String				COLOR_3		= "#0000ff";
	private final String				COLOR_4		= "#ff9900";
	private final String				COLOR_5		= "#ff0099";
	private final String				COLOR_6		= "#99ff00";
	private final String				COLOR_7		= "#9900ff";
	private final String				COLOR_8		= "#009900";
	private final String				COLOR_9		= "#99ffff";
	private final String				COLOR_10	= "#ffff99";

	private Group						group;

	private StandardReportPanel() {
		group = (Group) Controller.getInstance().getUser();

		setTitle("Analyse-bericht");
		setPaddings(15);

		Panel horizPanel = new Panel();
		horizPanel.setLayout(new HorizontalLayout(2));
//		horizPanel.add(createPieChart());
//		horizPanel.add(createBarChart());

		add(horizPanel);
	}

	public static StandardReportPanel getInstance() {
		if (reportPanel == null)
			reportPanel = new StandardReportPanel();
		return reportPanel;
	}

	private ChartWidget createPieChart() {
		ChartWidget chart = new ChartWidget();

		ChartData cd = new ChartData(
				"Marktanteile",
				"filter:Alpha(opacity=100, finishopacity=80, startx=10, "
						+ "finishx=484, style=1); -moz-opacity: 0.9 ; font-size: 14px; "
						+ "font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");

		PieChart pie = new PieChart();
		pie.setAlpha(1f);
		pie.setNoLabels(true);
		pie.setTooltip("#label#<br>#val# Toaster<br>#percent#");
		pie.setAnimate(false);
		pie.setGradientFill(true);
		pie.setColours(COLOR_1, COLOR_2);
		pie.addSlices(new PieChart.Slice(group.getCompany().getMarketShare(),
				Controller.getInstance().getUser().getUsername()));
		int restMarketShare = 0;
		for (int i = 0; i < group.getCompany().getToasterList().size(); i++)
			restMarketShare += group.getCompany().getToasterList().get(i)
					.getType().getMarketVolume();
		restMarketShare -= group.getCompany().getMarketShare();
		pie.addSlices(new PieChart.Slice(restMarketShare, "Rest"));

		cd.addElements(pie);

		chart.setSize("300", "250");
		chart.setJsonData(cd.toString());
		return chart;
	}

	private ChartWidget createBarChart() {
		ChartWidget chart = new ChartWidget();
		ChartData cd = new ChartData("Kapital nach Runde 1",
				"font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");

		XAxis xa = new XAxis();
		List<String> labels = new ArrayList<String>();
		List<Number> bchartValues = new ArrayList<Number>();
		Map<Number, String> capitalList = group.getCompany()
				.getKapitalOverview(Controller.getInstance().getGame());
		Set<Number> keySet = capitalList.keySet();
		Iterator<Number> iter = keySet.iterator();
		while (iter.hasNext()) {
			Number key = iter.next();
			String value = capitalList.get(key);
			labels.add(value);
			bchartValues.add(key);
		}

		xa.setLabels(labels);
		xa.setMax(3);
		cd.setXAxis(xa);

		YAxis ya = new YAxis();
		ya.setSteps(40000);
		ya.setMax(200000);
		cd.setYAxis(ya);

		BarChart bchart = new BarChart(BarStyle.GLASS);
		bchart.setColour("#00aa00");
		bchart.setTooltip("#val# &#8364;");
		bchart.addValues(bchartValues);
		cd.addElements(bchart);

		chart.setSize("300", "250");
		chart.setJsonData(cd.toString());

		return chart;
	}
}
