package toastwars.client.ui.group;

/*
 * Author: Waldemar Geppart
 * */

import java.util.ArrayList;
import java.util.List;
import toastwars.client.Controller;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;

import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
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

public class ExtraReportPanel extends TabPanel {

	private static ExtraReportPanel				reportPanel;
	private ArrayList<ArrayList<List<String>>>	report;
	private Game								game;

	private static final String					COLOR_1		= "#ff0000";
	private static final String					COLOR_2		= "#00ff00";
	private static final String					COLOR_3		= "#0000ff";
	private static final String					COLOR_4		= "#ff9900";
	private static final String					COLOR_5		= "#ff0099";
	private static final String					COLOR_6		= "#99ff00";
	private static final String					COLOR_7		= "#9900ff";
	private static final String					COLOR_8		= "#009900";
	private static final String					COLOR_9		= "#99ffff";
	private static final String					COLOR_10	= "#ffff99";

	public static ExtraReportPanel getInstance() {
		if (reportPanel == null)
			reportPanel = new ExtraReportPanel();
		return reportPanel;
	}

	private ExtraReportPanel() {
		game = Controller.getInstance().getGame();
		Group myGroup = (Group) Controller.getInstance().getUser();

		for (Group g : game.getGroupList()) {
			if (g.getUsername().equals(myGroup.getUsername())) {
				report = g.getCompany().getReportListe();
				break;
			}
		}

		setTitle("Marktforschungsbericht");
		setTabPosition(Position.BOTTOM);
		addListener(new TabChangeListener());

		add(createTab(Type.TYPE1));
		add(createTab(Type.TYPE2));
		add(createTab(Type.TYPE3));
	}

	private Panel createTab(Type type) {
		Panel main = new Panel();
		main.setBorder(false);
		main.setTitle(type.getDescription());
		main.setLayout(new VerticalLayout(10));
		main.setPaddings(15);
		main.setAutoScroll(true);
		main.setStyle("text-align: center;");

		Panel horizPanel = new Panel();
		horizPanel.setLayout(new HorizontalLayout(15));
		horizPanel.setStyle("text-align: center;");

		horizPanel.add(createMarketsharePieChart(type));

		List<String> priceList = report.get(type.ordinal()).get(6);
		if (!priceList.isEmpty())
			horizPanel.add(createPriceBarChart(type));

		main.add(horizPanel);
		main.add(createGridPanel(type));

		return main;
	}

	private GridPanel createGridPanel(Type type) {
		GridPanel grid = new GridPanel();
		grid.setStyle("text-align: left;");
		grid.setFrame(true);
		grid.setStripeRows(true);
		grid.setHeight(70 + 22 * report.get(type.ordinal()).get(0).size());
		grid.setWidth(700);
		grid.setTitle("Platzierungen in der Runde "
				+ (game.getCurrentRound() - 1));

		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("rank"), new StringFieldDef("magazine"),
				new StringFieldDef("radio"), new StringFieldDef("tv"),
				new StringFieldDef("quality"), new StringFieldDef("design"),
				new StringFieldDef("ecology") });

		ArrayReader reader = new ArrayReader(recordDef);
		Store store = new Store(new MemoryProxy(getRanking(type)), reader);
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

	private Object[][] getRanking(Type type) {
		Object[][] data = new Object[report.get(type.ordinal()).get(0).size()][7];

		for (int i = 0; i < data.length; i++) {
			data[i][0] = i + 1;
			for (int j = 0; j < report.get(type.ordinal()).size() - 1; j++) {
				data[i][j + 1] = report.get(type.ordinal()).get(j).get(i);
			}
		}

		return data;
	}

	private Widget createPriceBarChart(Type type) {
		ChartWidget chart = new ChartWidget();
		ChartData cd = new ChartData("Preise in der Runde "
				+ (game.getCurrentRound() - 1),
				"font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");

		List<String> priceList = report.get(type.ordinal()).get(6);

		XAxis xa = new XAxis();
		List<String> labels = new ArrayList<String>();
		List<Number> bchartValues = new ArrayList<Number>();
		int setMax = 0;
		for (int i = 0; i < priceList.size(); i += 2) {
			// changed by alex
			Number key = Double.parseDouble(priceList.get(i));
			String label = priceList.get(i + 1);

			labels.add(label);
			bchartValues.add(key);

			if (key.intValue() > setMax)
				setMax = key.intValue();
		}
		xa.setLabels(labels);
		if (priceList.size() > 2)
			xa.setMax(priceList.size() / 2 - 1);
		else
			xa.setMax(priceList.size() - 1);
		cd.setXAxis(xa);

		YAxis ya = new YAxis();
		ya.setSteps((type.getMaxPrice() - type.getMinPrice()) / 5);
		ya.setMax(type.getMaxPrice());
		ya.setMin(type.getMinPrice());
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

	private ChartWidget createMarketsharePieChart(Type type) {
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

		int sum = 0;

		for (Group group : game.getGroupList()) {
			ArrayList<Toaster> toasterList = group.getCompany()
					.getToasterList();
			if (toasterList.size() <= type.ordinal())
				continue;
			Number value = toasterList.get(type.ordinal()).getMarketShare();
			String label = group.getUsername();
			if (value.doubleValue() != 0) {
				pie.addSlices(new PieChart.Slice(value, label));
				sum += value.intValue();
			}
		}

		int rest = type.getMarketVolume() - sum;
		if (rest > 0)
			pie.addSlices(new PieChart.Slice(rest, "Unges&#228;ttigter Teil"));

		cd.addElements(pie);

		chart.setSize("300", "250");
		chart.setJsonData(cd.toString());
		return chart;
	}
}
