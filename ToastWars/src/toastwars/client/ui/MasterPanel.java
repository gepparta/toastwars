package toastwars.client.ui;

import java.util.ArrayList;

import toastwars.client.Controller;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.Status;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class MasterPanel extends Panel {

	private static MasterPanel	masterPanel;
	private Button				startGameBtn;
	private Button				endGame;
	private Button				simulateBtn;
	private Store				store;
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

		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("group"), new StringFieldDef("price"),
				new StringFieldDef("magazine"), new StringFieldDef("radio"),
				new StringFieldDef("tv"), new StringFieldDef("quality"),
				new StringFieldDef("design"), new StringFieldDef("ecology"),
				new StringFieldDef("capital"), new StringFieldDef("report"),
				new StringFieldDef("status") });

		ArrayReader reader = new ArrayReader(recordDef);
		store = new Store(new MemoryProxy(getGameData()), reader);
		store.load();
		grid.setStore(store);

		ColumnConfig[] columns = new ColumnConfig[] {
				new ColumnConfig("Gruppe", "group", 70, true),
				new ColumnConfig("Preis", "price", 50, true),
				new ColumnConfig("Zeitung", "magazine", 100, true),
				new ColumnConfig("Radio", "radio", 100, true),
				new ColumnConfig("TV", "tv", 100, true),
				new ColumnConfig("Qualit&auml;t", "quality", 100, true),
				new ColumnConfig("Design", "design", 100, true),
				new ColumnConfig("&Ouml;kologie", "ecology", 100, true),
				new ColumnConfig("Kapital", "capital", 100, true),
				new ColumnConfig("Marktforschungsbericht", "report", 150, true),
				new ColumnConfig("Status", "status", 170, true) };

		ColumnModel columnModel = new ColumnModel(columns);
		grid.setColumnModel(columnModel);

		add(grid);
	}

	private Object[][] getGameData() {
		if (groupList == null || groupList.size() <= 0)
			return new Object[][] { new Object[] {} };

		Object[][] data = new Object[groupList.size()][11];

		for (int i = 0; i < data.length; i++) {
			Group group = groupList.get(i);
			data[i][0] = group.getUsername();

			Toaster toaster = group.getCompany().getToasterList().get(0);
			data[i][1] = toaster.getPrice();
			data[i][2] = toaster.getNewspaperInvestment();
			data[i][3] = toaster.getRadioInvestment();
			data[i][4] = toaster.getTvInvestment();
			data[i][5] = toaster.getQualityInvestment();
			data[i][6] = toaster.getDesignInvestment();
			data[i][7] = toaster.getEfficiencyInvestment();

			data[i][8] = group.getCompany().getCapital();
			data[i][9] = group.getCompany().isMarketResearchReportON();
			data[i][10] = group.getStatus().getDescription();
		}

		return data;
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
		grid.setTitle("Spielstand in der Runde " + game.getCurrentRound());
	}

	public void endGame() {
		startGameBtn.setDisabled(false);
		endGame.setDisabled(true);
		game = null;
		groupList = null;

		store.setDataProxy(new MemoryProxy(getGameData()));
		store.load();
		grid.setTitle("Spielstand");
	}

	public void simulate(Game game) {
		this.game = game;
		groupList = game.getGroupList();
		store.setDataProxy(new MemoryProxy(getGameData()));
		store.load();
		grid.setTitle("Spielstand in der Runde " + game.getCurrentRound());
		simulateBtn.setDisabled(true);
	}
}
