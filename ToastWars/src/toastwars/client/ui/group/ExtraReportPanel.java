package toastwars.client.ui.group;

import java.util.ArrayList;
import java.util.List;
import toastwars.client.Controller;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.user.Group;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.PieChart;
import com.rednels.ofcgwt.client.model.elements.BarChart.BarStyle;

public class ExtraReportPanel extends Panel {

	private static ExtraReportPanel	reportPanel;
	private ArrayList<ArrayList<List<String>>>	report;
	private Game					game;

	private static final String		COLOR_1		= "#ff0000";
	private static final String		COLOR_2		= "#00ff00";
	private static final String		COLOR_3		= "#0000ff";
	private static final String		COLOR_4		= "#ff9900";
	private static final String		COLOR_5		= "#ff0099";
	private static final String		COLOR_6		= "#99ff00";
	private static final String		COLOR_7		= "#9900ff";
	private static final String		COLOR_8		= "#009900";
	private static final String		COLOR_9		= "#99ffff";
	private static final String		COLOR_10	= "#ffff99";

	public static ExtraReportPanel getInstance() {
		if (reportPanel == null)
			reportPanel = new ExtraReportPanel();
		return reportPanel;
	}

	private ExtraReportPanel() {
		game = Controller.getInstance().getGame();
		report = ((Group) Controller.getInstance().getUser()).getCompany()
				.getReportListe();

		setTitle("Marktforschungsbericht");
		setPaddings(15);
		setLayout(new VerticalLayout(15));
		setAutoScroll(true);
		setStyle("text-align: center;");

		Panel horizPanel = new Panel();
		horizPanel.setLayout(new HorizontalLayout(15));
		horizPanel.setStyle("text-align: center;");

		horizPanel.add(createMarketsharePieChart());
		horizPanel.add(createPriceBarChart());

		add(horizPanel);
		add(createGridPanel());
	}

	private GridPanel createGridPanel() {
		GridPanel grid = new GridPanel();
		grid.setStyle("text-align: left;");
		grid.setFrame(true);
		grid.setStripeRows(true);
		grid.setHeight(70 + 22 * report.get(1).size());
		grid.setWidth(700);
		grid.setTitle("Platzierungen in der Runde "
				+ (game.getCurrentRound() - 1));

		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("rank"), new StringFieldDef("magazine"),
				new StringFieldDef("radio"), new StringFieldDef("tv"),
				new StringFieldDef("quality"), new StringFieldDef("design"),
				new StringFieldDef("ecology") });

		ArrayReader reader = new ArrayReader(recordDef);
		Store store = new Store(new MemoryProxy(getRanking()), reader);
		store.load();
		grid.setStore(store);

		ColumnConfig[] columns = new ColumnConfig[] {
				new ColumnConfig("Platz", "rank", 70),
				new ColumnConfig("Zeitung", "magazine", 100),
				new ColumnConfig("Radio", "radio", 100),
				new ColumnConfig("TV", "tv", 100),
				new ColumnConfig("Qualit&auml;t", "quality", 100),
				new ColumnConfig("Design", "design", 100),
				new ColumnConfig("&Ouml;kologie", "ecology", 100) };

		ColumnModel columnModel = new ColumnModel(columns);
		grid.setColumnModel(columnModel);
		return grid;
	}

	private Object[][] getRanking() {
		Object[][] data = new Object[report.get(1).size()][7];

		for (int i = 0; i < data.length; i++) {
			data[i][0] = i + 1;
			for (int j = 0; j < report.size(); j++) {
				data[i][j + 1] = report.get(j).get(i);
			}
		}

		return data;
	}

	private ChartWidget createPriceBarChart() {
		ChartWidget chart = new ChartWidget();
		ChartData cd = new ChartData("Preise in der Runde "
				+ (game.getCurrentRound() - 1),
				"font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");

		ArrayList<Group> groupList = game.getGroupList();

		XAxis xa = new XAxis();
		List<String> labels = new ArrayList<String>();
		List<Number> bchartValues = new ArrayList<Number>();
		int setMax = 0;
		for (Group group : groupList) {
			String label = group.getUsername();
			Number key = group.getCompany().getToasterList().get(0).getPrice();

			labels.add(label);
			bchartValues.add(key);

			if (key.intValue() > setMax)
				setMax = key.intValue();
		}
		xa.setLabels(labels);
		xa.setMax(groupList.size() - 1);
		cd.setXAxis(xa);

		YAxis ya = new YAxis();
		ya.setSteps(4);
		ya.setMax(10 + setMax);
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

	private ChartWidget createMarketsharePieChart() {
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
		pie.setColours(COLOR_1, COLOR_2, COLOR_3, COLOR_4, COLOR_5, COLOR_6,
				COLOR_7, COLOR_8, COLOR_9, COLOR_10);

		for (Group group : game.getGroupList()) {
			Number value = group.getCompany().getToasterList().get(0)
					.getMarketShare();
			String label = group.getUsername();
			pie.addSlices(new PieChart.Slice(value, label));
		}

		cd.addElements(pie);

		chart.setSize("300", "250");
		chart.setJsonData(cd.toString());
		return chart;
	}
}
