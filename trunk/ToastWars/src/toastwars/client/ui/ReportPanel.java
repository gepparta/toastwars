package toastwars.client.ui;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.PieChart;
import com.rednels.ofcgwt.client.model.elements.BarChart.BarStyle;

public class ReportPanel extends Panel {

	private static ReportPanel	reportPanel;

	private ReportPanel() {
		setTitle("Analyse-Bericht");

		Panel horizPanel = new Panel();
		horizPanel.setLayout(new HorizontalLayout(2));
		horizPanel.setSize(600, 300);
		horizPanel.add(createPieChart());
		horizPanel.add(createBarChart());

		add(horizPanel);
	}

	public static ReportPanel getInstance() {
		if (reportPanel == null)
			reportPanel = new ReportPanel();
		return reportPanel;
	}

	private ChartWidget createPieChart() {
		ChartWidget chart = new ChartWidget();
		chart.setStyleName("background-color:transparent");
		ChartData cd = new ChartData("Sales by Region",
				"font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("transparent");
		PieChart pie = new PieChart();
		pie.setAlpha(0.7f);
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
