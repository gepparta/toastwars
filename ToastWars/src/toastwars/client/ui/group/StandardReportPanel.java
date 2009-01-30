package toastwars.client.ui.group;

import java.util.ArrayList;
import java.util.List;

import toastwars.client.Controller;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
import toastwars.util.NumberUtil;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.chart.yui.Axis;
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
	private final String				COLOR_1		= "#FDD017";
	private final String				COLOR_2		= "#ff0000";
	private final String				COLOR_3		= "#0000ff";
	private final String				COLOR_4		= "#00ff00";
	private final String				COLOR_5		= "#ff0099";
	private final String				COLOR_6		= "#99ff00";
	private final String				COLOR_7		= "#9900ff";
	private final String				COLOR_8		= "#009900";
	private final String				COLOR_9		= "#99ffff";
	private final String				COLOR_10	= "#ffff99";

	private Group						group;
	private Game						game;

	public static StandardReportPanel getInstance() {
		if (reportPanel == null)
			reportPanel = new StandardReportPanel();
		return reportPanel;
	}

	private StandardReportPanel() {
		game = Controller.getInstance().getGame();
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
		horizPanel.add(createMarketShareChart(toasterList.get(0)));

		// add type 2 or empty
		if (toasterList.size() > 1) {
			if (!dPanel.isNewToaster(toasterList.get(1).getType()))
				horizPanel.add(createMarketShareChart(toasterList.get(1)));
			else
				horizPanel.add(emptyPanel);
		} else
			horizPanel.add(emptyPanel);

		// add type 3 or empty
		if (toasterList.size() > 2) {
			if (!dPanel.isNewToaster(toasterList.get(2).getType()))
				horizPanel.add(createMarketShareChart(toasterList.get(2)));
			else
				horizPanel.add(emptyPanel);
		} else
			horizPanel.add(emptyPanel);

		return horizPanel;
	}

	private ChartWidget createMarketShareChart(Toaster toaster) {
		ChartWidget chart = new ChartWidget();

		ChartData cd = new ChartData(
				"Marktanteile f&#252;r " + toaster.getType().getDescription(),
				"filter:Alpha(opacity=100, finishopacity=80, startx=10, "
						+ "finishx=484, style=1); -moz-opacity: 0.9 ; font-size: 14px; "
						+ "font-family: Verdana; text-align: center; color: #FDD017;");
//		cd.setBackgroundColour("-1");

		PieChart pie = new PieChart();
		pie.setAlpha(1f);
		pie.setNoLabels(true);
		pie.setTooltip("#label#<br>#val# Toaster<br>#percent#");
		pie.setAnimate(false);
		pie.setGradientFill(true);
		pie.setColours(COLOR_1, COLOR_2, COLOR_3, COLOR_4, COLOR_5, COLOR_6,
				COLOR_7, COLOR_8, COLOR_9, COLOR_10);

		// Slice 1: my own market share
		int myMarketShare = toaster.getMarketShare();
		if (myMarketShare > 0)
			pie.addSlices(new PieChart.Slice(myMarketShare, Controller
					.getInstance().getUser().getUsername()));

		// Slice 2: rest market share
		int restMarketShare = 0;
		ArrayList<Group> groupList = game.getGroupList();
		for (Group group : groupList) {
			ArrayList<Toaster> list = group.getCompany().getToasterList();
			if (!group.getUsername().equals(this.group.getUsername()))
				if (toaster.getType().ordinal() < list.size())
					restMarketShare += list.get(toaster.getType().ordinal())
							.getMarketShare();
		}
		if (restMarketShare > 0)
			pie.addSlices(new PieChart.Slice(restMarketShare, "Rest"));

		// Slice 3: not saturated
		int sumMarketshare = toaster.getType().getMarketVolume();
		int ungesaettigterTeil = 0;
		// wenn es noch ungesättigte Teile am Markt gibt
		// stelle den ungesättigten Teil dar
		if (sumMarketshare > restMarketShare + myMarketShare) {
			ungesaettigterTeil = sumMarketshare - restMarketShare
					- myMarketShare;
			if (ungesaettigterTeil > 0)
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
				"font-size: 14px; font-family: Verdana; text-align: center; color: #FDD017;");
//		cd.setBackgroundColour("-1");

		XAxis xa = new XAxis();
		List<String> labels = new ArrayList<String>();
		List<Number> bchartValues = new ArrayList<Number>();

		ArrayList<Number> capitalList = group.getCompany()
				.getCapitalRankingInternList();
		int setMax = 0;
		// int setMin = 0;
		for (int i = 0; i < capitalList.size(); i++) {
			Number key = capitalList.get(i);
			String value = game.getGroupList().get(i).getUsername();
			labels.add(value);
			bchartValues.add(key);
			if (key.intValue() > setMax)
				setMax = key.intValue();
			// if (key.intValue() < setMin)
			// setMin = key.intValue();
		}
		xa.setLabels(labels);
//		xa.getLabels().setColour("#FDD017");
		xa.setMax(capitalList.size() - 1);
		xa.setTickHeight(10);
		cd.setXAxis(xa);

		YAxis ya = new YAxis();
		ya.setMax(20000 + setMax);
		ya.setMin(0);
		int steps = (ya.getMax().intValue() + Math.abs(ya.getMin().intValue())) / 5;
		ya.setSteps(steps);
//		ya.getLabels().setColour("#FDD017");
		cd.setYAxis(ya);

		BarChart bchart = new BarChart(BarStyle.GLASS);
		bchart.setColour("#FDD017");
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
				"font-size: 14px; font-family: Verdana; text-align: center; color: #FDD017;");
//		cd.setBackgroundColour("-1");

		XAxis xa = new XAxis();
		List<String> labels = new ArrayList<String>();
		List<Number> bchartValues = new ArrayList<Number>();

		ArrayList<Number> profitList = group.getCompany()
				.getProfitRankingList();
		int setMax = 0;
		// int setMin = 0;
		for (int i = 0; i < profitList.size(); i++) {

			Number key = profitList.get(i);
			String value = game.getGroupList().get(i).getUsername();
			labels.add(value);
			bchartValues.add(key);
			if (key.intValue() > setMax)
				setMax = key.intValue();
			// if (key.intValue() < setMin)
			// setMin = key.intValue();
		}
		xa.setLabels(labels);
//		xa.getLabels().setColour("#FDD017");
		xa.setMax(profitList.size() - 1);
		cd.setXAxis(xa);

		YAxis ya = new YAxis();
		ya.setMin(0);
		setMax = NumberUtil.roundIntUp(setMax, 1000);
		ya.setMax(20000 + setMax);
		// setMin = NumberUtil.roundIntUp(setMin, 1000);
		// ya.setRange(setMin, setMax);
		// int steps = (ya.getMax().intValue() +
		// Math.abs(ya.getMin().intValue())) / 5;
		// ya.setSteps(steps);
		ya.setSteps(ya.getMax());
		cd.setYAxis(ya);

		BarChart bchart = new BarChart(BarStyle.GLASS);
		bchart.setColour("#FDD017");
		bchart.setTooltip("#val# &#8364;");
		bchart.addValues(bchartValues);
		cd.addElements(bchart);

		chart.setSize("300", "250");
		chart.setJsonData(cd.toString());

		return chart;
	}
	
	private double getVariableCost4Company(Group group)
	{
		double variableCost = 0.0;
		for (Toaster toaster : group.getCompany().getToasterList())
		{
			Type type = toaster.getType();
			variableCost += (toaster.getProduction()* type.getVariableCosts());
		}
		return variableCost;
	}
	
	private double getFixCost4Company(Group group)
	{
		double fixCost = 0.0;
		for (Toaster toaster : group.getCompany().getToasterList())
		{
			Type type = toaster.getType();
			fixCost += type.getFixCosts();
		}
		return fixCost;
	}
	
	private double getStepCost4Company(Group group)
	{
		double stepCost = 0.0;
		for (Toaster toaster : group.getCompany().getToasterList())
		{
			Type type = toaster.getType();
			stepCost += (Math.ceil((double) toaster.getProduction() / type.getCapacity()) * type.getStepCosts());
		}
		return stepCost;
	}
	
	private double getIndex4Company(Group group)
	{
		double index = 0.0;
		for (Toaster toaster : group.getCompany().getToasterList())
		{
			index += toaster.getIndex();
		}
		return index;
	}
}
