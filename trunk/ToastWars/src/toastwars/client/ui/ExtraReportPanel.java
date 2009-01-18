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

public class ExtraReportPanel extends Panel {

	private static ExtraReportPanel	reportPanel;

	private ExtraReportPanel() {
		setTitle("Marktforschungsbericht");
		setPaddings(15);

		Panel horizPanel = new Panel();
		horizPanel.setLayout(new HorizontalLayout(2));
//		horizPanel.add(createPieChart());
//		horizPanel.add(createBarChart());

		add(horizPanel);
	}

	public static ExtraReportPanel getInstance() {
		if (reportPanel == null)
			reportPanel = new ExtraReportPanel();
		return reportPanel;
	}

	private ChartWidget createPieChart() {
		ChartWidget chart = new ChartWidget();

		ChartData cd = new ChartData("Marktanteile",
				"font-size: 14px; text-align: center; border: 1px solid gold;");
		cd.setBackgroundColour("#000000");

		PieChart pie = new PieChart();
		pie.setAlpha(1f);
		pie.setNoLabels(true);
		pie.setTooltip("#label#<br>#val# Toaster<br>#percent#");
		pie.setAnimate(false);
		pie.setGradientFill(true);
		pie.setColours("#ff0000", "#00ff00", "#0000ff", "#ff9900");
		pie.addSlices(new PieChart.Slice(1000, "Gruppe 1"));
		pie.addSlices(new PieChart.Slice(2000, "Gruppe 2"));
		pie.addSlices(new PieChart.Slice(6000, "Gruppe 3"));
		pie.addSlices(new PieChart.Slice(1000, "Gruppe 4"));

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
