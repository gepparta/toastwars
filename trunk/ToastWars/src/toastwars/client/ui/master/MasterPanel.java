package toastwars.client.ui.master;

import java.util.ArrayList;

import toastwars.client.Controller;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.Status;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.SortDir;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.FloatFieldDef;
import com.gwtext.client.data.GroupingStore;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.SortState;
import com.gwtext.client.data.Store;
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
				new StringFieldDef("group"), new FloatFieldDef("price"),
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
		gridView.setForceFit(true);
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
				new ColumnConfig("Gruppe", "group", 70),
				new ColumnConfig("Preis", "price", 50),
				new ColumnConfig("Zeitung", "magazine", 100),
				new ColumnConfig("Radio", "radio", 100),
				new ColumnConfig("TV", "tv", 100),
				new ColumnConfig("Qualit&auml;t", "quality", 100),
				new ColumnConfig("Design", "design", 100),
				new ColumnConfig("&Ouml;kologie", "ecology", 100),
				new ColumnConfig("Kapital", "capital", 100),
				new ColumnConfig("Marktforschungsbericht", "report", 150),
				new ColumnConfig("Status", "status", 100),
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
					break;

				Object[] data = new Object[12];
				data[0] = groupList.get(j).getUsername();

				Toaster toaster = comp.getToasterList().get(i);
				data[1] = toaster.getPrice();
				data[2] = toaster.getNewspaperInvestment();
				data[3] = toaster.getRadioInvestment();
				data[4] = toaster.getTvInvestment();
				data[5] = toaster.getQualityInvestment();
				data[6] = toaster.getDesignInvestment();
				data[7] = toaster.getEfficiencyInvestment();

				data[8] = comp.getCapital();
				data[9] = comp.isMarketResearchReportON();
				data[10] = groupList.get(j).getStatus().getDescription();

				data[11] = toaster.getType().getDescription();
				dataList.add(data);
			}
		}

		Object[][] dataArray = new Object[dataList.size()][12];
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
			if (group.getStatus() != Status.COMPLETED)
				return false;
		}
		return true;
	}

	public void startGame(Game game) {
		startGameBtn.setDisabled(true);
		endGame.setDisabled(false);
		this.game = game;
		groupList = game.getGroupList();
		store.setDataProxy(new MemoryProxy(getGameData()));
		store.load();
		grid.getView().refresh();
		grid.setTitle("Spielstand in der Runde " + game.getCurrentRound());
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
		this.game = game;
		groupList = game.getGroupList();
		store.setDataProxy(new MemoryProxy(getGameData()));
		store.load();
		grid.getView().refresh();
		grid.setTitle("Spielstand in der Runde " + game.getCurrentRound());
		simulateBtn.setDisabled(true);
	}
}