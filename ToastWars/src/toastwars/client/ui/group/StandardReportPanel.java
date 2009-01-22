package toastwars.client.ui.group;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import toastwars.client.Controller;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
import toastwars.util.NumberUtil;

import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;
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

	public static StandardReportPanel getInstance() {
		if (reportPanel == null)
			reportPanel = new StandardReportPanel();
		return reportPanel;
	}

	private StandardReportPanel() {
		group = (Group) Controller.getInstance().getUser();

		setTitle("Analyse-Bericht");
		setPaddings(15);
		setLayout(new VerticalLayout(3));
		setAutoScroll(true);
		setStyle("text-align: center;");

		Panel topPanel = new Panel();
		topPanel.setStyle("text-align: center;");
		topPanel.setLayout(new HorizontalLayout(3));
		topPanel.add(createCapitalBarChart());
		topPanel.add(createProfitBarChart());

		Panel bottomPanel = createPieCharts();

		add(topPanel);
		add(bottomPanel);
	}

	private Panel createPieCharts() {
		Panel horizPanel = new Panel();
		horizPanel.setStyle("text-align: center;");
		horizPanel.setLayout(new HorizontalLayout(3));

		// empty panel
		Panel emptyPanel = new Panel();
		emptyPanel.setSize(300, 250);
		emptyPanel.setBorder(true);
		emptyPanel.setPaddings(30);
		emptyPanel.setStyle("text-align: center;");
		Label label = new Label("Unbekannter Markt");
		label.setStyle("font:bold 11px tahoma,arial,verdana,sans-serif;");
		emptyPanel.add(label);

		ArrayList<Toaster> toasterList = group.getCompany().getToasterList();
		DecissionPanel dPanel = DecissionPanel.getInstance();

		// add type 1
		horizPanel.add(createPieChart(toasterList.get(0)));

		// add type 2 or empty
		if (toasterList.size() > 1) {
			if (!dPanel.isNewToaster(toasterList.get(1).getType()))
				horizPanel.add(createPieChart(toasterList.get(1)));
			else
				horizPanel.add(emptyPanel);
		} else
			horizPanel.add(emptyPanel);

		// add type 3 or empty
		if (toasterList.size() > 2) {
			if (!dPanel.isNewToaster(toasterList.get(2).getType()))
				horizPanel.add(createPieChart(toasterList.get(2)));
			else
				horizPanel.add(emptyPanel);
		} else
			horizPanel.add(emptyPanel);

		return horizPanel;
	}

	private ChartWidget createPieChart(Toaster toaster) {
		ChartWidget chart = new ChartWidget();

		ChartData cd = new ChartData(
				"Marktanteile f&#252;r " + toaster.getType().getDescription(),
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
		pie.setColours(COLOR_1, COLOR_2, COLOR_3, COLOR_4, COLOR_5, COLOR_6,
				COLOR_7, COLOR_8, COLOR_9, COLOR_10);

		// Slice 1: my own market share
		int marktGesamt = Type.TYPE1.getMarketVolume()
				+ Type.TYPE2.getMarketVolume() + Type.TYPE3.getMarketVolume();
		int meinMarketShare = toaster.getMarketShare();
		pie.addSlices(new PieChart.Slice(meinMarketShare, Controller
				.getInstance().getUser().getUsername()));

		// Slice 2: rest market share
		int restMarketShare = 0;
		int sumMarketshare = 0;
		ArrayList<Toaster> toasterList = group.getCompany().getToasterList();
		for (Toaster toaster2 : toasterList) {
			sumMarketshare += toaster2.getMarketShare();
		}
		restMarketShare = sumMarketshare - meinMarketShare;
		pie.addSlices(new PieChart.Slice(restMarketShare, "Rest"));

		// Slice 3: not saturated
		int ungesaettigterTeil = 0;
		// wenn es noch ungesättigte Teile am Markt gibt
		// stelle den ungesättigten Teil dar
		if (marktGesamt > sumMarketshare) {
			ungesaettigterTeil = marktGesamt - sumMarketshare;
			pie.addSlices(new PieChart.Slice(ungesaettigterTeil,
					"Unges&#228;ttigter Teil"));
		}

		cd.addElements(pie);

		chart.setSize("300", "250");
		chart.setJsonData(cd.toString());
		return chart;
	}

	private ChartWidget createCapitalBarChart() {
		Game game = Controller.getInstance().getGame();

		ChartWidget chart = new ChartWidget();
		ChartData cd = new ChartData("Kapital in &#8364; nach Runde "
				+ (game.getCurrentRound() - 1),
				"font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");

		XAxis xa = new XAxis();
		List<String> labels = new ArrayList<String>();
		List<Number> bchartValues = new ArrayList<Number>();

		ArrayList<Number> capitalList = group.getCompany()
				.getCapitalRankingInternList();
		int setMax = 0;
		int setMin = 0;
		for (int i = 0; i < capitalList.size(); i++) {
			Number key = capitalList.get(i);
			String value = game.getGroupList().get(i).getUsername();
			labels.add(value);
			bchartValues.add(key);
			if (key.intValue() > setMax)
				setMax = key.intValue();
			if (key.intValue() < setMin)
				setMin = key.intValue();
		}
		xa.setLabels(labels);
		xa.setMax(capitalList.size() - 1);
		cd.setXAxis(xa);

		YAxis ya = new YAxis();
		ya.setSteps(40000);
		ya.setMax(20000 + setMax);
		ya.setMin(NumberUtil.roundIntUp(setMin, 1000));
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

	private ChartWidget createProfitBarChart() {
		Game game = Controller.getInstance().getGame();
		ChartWidget chart = new ChartWidget();
		ChartData cd = new ChartData("Gewinn in &#8364; nach Runde "
				+ (game.getCurrentRound() - 1),
				"font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");

		XAxis xa = new XAxis();
		List<String> labels = new ArrayList<String>();
		List<Number> bchartValues = new ArrayList<Number>();

		ArrayList<Number> profitList = group.getCompany()
				.getProfitRankingList();
		int setMax = 0;
		int setMin = 0;
		for (int i = 0; i < profitList.size(); i++) {

			Number key = profitList.get(i);
			String value = game.getGroupList().get(i).getUsername();
			labels.add(value);
			bchartValues.add(key);
			if (key.intValue() > setMax)
				setMax = key.intValue();
			if (key.intValue() < setMin)
				setMin = key.intValue();
		}
		xa.setLabels(labels);
		xa.setMax(profitList.size() - 1);
		cd.setXAxis(xa);

		YAxis ya = new YAxis();
		// ya.setMax(20000 + setMax);
		// ya.setMin(NumberUtil.roundIntUp(setMin, 1000));
		setMax = NumberUtil.roundIntUp(setMax, 1000);
		setMin = NumberUtil.roundIntUp(setMin, 1000);
		ya.setRange(setMin, setMax);
		int steps = (ya.getMax().intValue() + Math.abs(ya.getMin().intValue())) / 5;
		ya.setSteps(steps);
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
