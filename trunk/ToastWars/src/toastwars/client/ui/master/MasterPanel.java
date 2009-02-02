package toastwars.client.ui.master;

import java.util.ArrayList;
import toastwars.client.Controller;
import toastwars.client.comet.CometController;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.Status;
import toastwars.util.NumberUtil;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.SortDir;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.GroupingStore;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.SortState;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.GroupingView;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class MasterPanel extends Panel {

	private static MasterPanel	masterPanel;
	private Button				startGameBtn;
	private Button				endGame;
	private Button				simulateBtn;
	private GroupingStore		store;
	private GridPanel			grid;

	private Game				game;
	private ArrayList<Group>	groupList;
	private String				round	= "";

	public static MasterPanel getInstance() {
		if (masterPanel == null)
			masterPanel = new MasterPanel();
		return masterPanel;
	}

	private MasterPanel() {

		game = ((Master) Controller.getInstance().getUser()).getCurrentGame();
		if (game != null) {
			groupList = game.getGroupList();
			round = " in der Runde " + game.getCurrentRound();
		}

		setTitle("Spiel&uuml;bersicht");
		setPaddings(0, 15, 0, 0);
		setLayout(new VerticalLayout(10));

		createControlFieldSet();
		createGrid();
		createSimulateButton();
		
		CometController.startPushService();
	}

	private void createControlFieldSet() {
		FieldSet control = new FieldSet("Spielsteuerung");
		control.setWidth(1160);
		control.setButtonAlign(Position.CENTER);

		Panel panel = new Panel();
		panel.setBorder(false);
		panel.setLayout(new HorizontalLayout(10));
		panel.setStyle("text-align: center;");

		startGameBtn = new Button("Spiel starten", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				new StartGameWindow();
			}
		});
		panel.add(startGameBtn);

		endGame = new Button("Spiel beenden", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				Controller.getInstance().endGame();
			}
		});
		panel.add(endGame);

		if (game == null) {
			endGame.setDisabled(true);
			startGameBtn.setDisabled(false);
		} else {
			endGame.setDisabled(false);
			startGameBtn.setDisabled(true);
		}

		control.add(panel);
		add(control);
	}

	private void createGrid() {

		grid = new GridPanel();
		grid.setFrame(true);
		grid.setStripeRows(true);
		grid.setHeight(280);
		grid.setWidth(1160);
		grid.setTitle("Spielstand" + round);
		grid.setEnableHdMenu(false);
		grid.setEnableDragDrop(false);
		grid.setEnableColumnMove(false);

		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("group"), new StringFieldDef("price"),
				new StringFieldDef("amount"), new StringFieldDef("stock"),
				new StringFieldDef("magazine"), new StringFieldDef("radio"),
				new StringFieldDef("tv"), new StringFieldDef("quality"),
				new StringFieldDef("design"), new StringFieldDef("ecology"),
				new StringFieldDef("capital"), new StringFieldDef("report"),
				new StringFieldDef("status"), new StringFieldDef("type") });

		ArrayReader reader = new ArrayReader(recordDef);

		store = new GroupingStore(new MemoryProxy(getGameData()), reader);
		store.setGroupField("type");
		store.setSortInfo(new SortState("group", SortDir.ASC));
		store.load();
		grid.setStore(store);

		GroupingView gridView = new GroupingView();
		gridView.setForceFit(false);
		gridView.setGroupTextTpl("{text} ({[values.rs.length]} "
				+ "{[values.rs.length > 1 ? \"Gruppen\" : \"Gruppe\"]})");
		gridView.setHideGroupedColumn(true);
		gridView.setEnableGroupingMenu(false);
		grid.setView(gridView);

		ColumnConfig[] columns = createColumnConfig();

		ColumnModel columnModel = new ColumnModel(columns);
		grid.setColumnModel(columnModel);

		add(grid);
	}

	private ColumnConfig[] createColumnConfig() {
		ColumnConfig[] columns = new ColumnConfig[] {
				new ColumnConfig("Gruppe", "group", 140),
				new ColumnConfig("Preis", "price", 60),
				new ColumnConfig("Menge", "amount", 50),
				new ColumnConfig("Lager", "stock", 50),
				new ColumnConfig("Zeitung", "magazine", 80),
				new ColumnConfig("Radio", "radio", 80),
				new ColumnConfig("TV", "tv", 80),
				new ColumnConfig("Qualit&auml;t", "quality", 80),
				new ColumnConfig("Design", "design", 80),
				new ColumnConfig("&Ouml;kologie", "ecology", 80),
				new ColumnConfig("Kapital", "capital", 80),
				new ColumnConfig("Bericht", "report", 50),
				new ColumnConfig("Status", "status", 170),
				new ColumnConfig("Typ", "type", 70) };
		return columns;
	}

	private Object[][] getGameData() {
		if (groupList == null || groupList.size() <= 0)
			return new Object[][] { null };

		ArrayList<Object[]> dataList = new ArrayList<Object[]>();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < groupList.size(); j++) {
				Company comp = groupList.get(j).getCompany();

				if (comp.getToasterList().size() <= i)
					continue;

				Object[] data = new Object[14];
				data[0] = groupList.get(j).getUsername();

				Toaster toaster = comp.getToasterList().get(i);
				data[1] = NumberUtil.formatToEuro(toaster.getPrice());
				data[2] = toaster.getProduction();
				data[3] = comp.getStock().getStock(toaster.getType());
				data[4] = NumberUtil.formatToEuro(toaster
						.getNewspaperInvestment());
				data[5] = NumberUtil.formatToEuro(toaster.getRadioInvestment());
				data[6] = NumberUtil.formatToEuro(toaster.getTvInvestment());
				data[7] = NumberUtil.formatToEuro(toaster
						.getQualityInvestment());
				data[8] = NumberUtil
						.formatToEuro(toaster.getDesignInvestment());
				data[9] = NumberUtil.formatToEuro(toaster
						.getEcologyInvestment());

				data[10] = NumberUtil.formatToEuro(comp.getCapital());

				if (comp.isMarketResearchReportON())
					data[11] = "ja";
				else
					data[11] = "nein";
				data[12] = groupList.get(j).getStatus().getDescription();

				data[13] = "" + (i + 1) + " "
						+ toaster.getType().getDescription();
				dataList.add(data);
			}
		}

		Object[][] dataArray = new Object[dataList.size()][14];
		for (int i = 0; i < dataArray.length; i++) {
			dataArray[i] = dataList.get(i);
		}

		return dataArray;
	}

	private void createSimulateButton() {
		simulateBtn = new Button("Simulation starten",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						Controller.getInstance().simulate();
					}
				});
		addButton(simulateBtn);
		setButtonAlign(Position.CENTER);

		if (!areAllGroupsReady())
			simulateBtn.setDisabled(true);
	}

	private boolean areAllGroupsReady() {
		if (groupList == null)
			return false;
		for (Group group : groupList) {
			if (group.getStatus() != Status.COMPLETED && group.getStatus() != Status.INACTIVE)
				return false;
		}
		return true;
	}

	public void startGame(Game game) {
		startGameBtn.setDisabled(true);
		endGame.setDisabled(false);
		refreshGrid(game);
	}

	public void endGame() {
		startGameBtn.setDisabled(false);
		simulateBtn.setDisabled(true);
		endGame.setDisabled(true);
		game = null;
		groupList = null;

		store.setDataProxy(new MemoryProxy(getGameData()));
		store.load();
		grid.getView().refresh();
		grid.setTitle("Spielstand");
	}
	
	public void simulate(Game game) {
		simulateBtn.setDisabled(true);
		refreshGrid(game);
	}
	
	public void refreshGrid(Game game)
	{
		this.game = game;
		groupList = game.getGroupList();
		store.setDataProxy(new MemoryProxy(getGameData()));
		store.load();
		grid.getView().refresh();
		grid.setTitle("Spielstand in der Runde " + game.getCurrentRound());
	}
}
