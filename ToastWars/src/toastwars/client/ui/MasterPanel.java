package toastwars.client.ui;

import java.util.ArrayList;

import toastwars.client.Controller;
import toastwars.server.datamodel.user.Group;

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
import com.gwtext.client.widgets.layout.VerticalLayout;

public class MasterPanel extends Panel {

	private static MasterPanel	masterPanel;
	private Button				startGameBtn;
	private Button				simulateBtn;

	public static MasterPanel getInstance() {
		if (masterPanel == null)
			masterPanel = new MasterPanel();
		return masterPanel;
	}

	private MasterPanel() {
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

		startGameBtn = new Button("Spiel starten", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				new StartGameWindow();
			}
		});
		control.addButton(startGameBtn);

		control.addButton(new Button("Spiel beenden",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						// Controller.getInstance().endGame();
						endGame();
					}
				}));

		add(control);
	}

	private void createGrid() {

		GridPanel grid = new GridPanel();
		grid.setFrame(true);
		grid.setStripeRows(true);
		grid.setHeight(270);
		grid.setWidth(1160);
		grid.setTitle("Spielstand");

		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("group"), new StringFieldDef("price"),
				new StringFieldDef("magazine"), new StringFieldDef("radio"),
				new StringFieldDef("tv"), new StringFieldDef("quality"),
				new StringFieldDef("design"), new StringFieldDef("ecology"),
				new StringFieldDef("capital"), new StringFieldDef("report") });

		ArrayReader reader = new ArrayReader(recordDef);
		Store store = new Store(new MemoryProxy(getGameData()), reader);
		store.load();
		grid.setStore(store);

		ColumnConfig[] columns = new ColumnConfig[] {
				new ColumnConfig("Gruppe", "group", 100, true),
				new ColumnConfig("Preis", "price", 100, true),
				new ColumnConfig("Zeitung", "magazine", 100, true),
				new ColumnConfig("Radio", "radio", 100, true),
				new ColumnConfig("TV", "tv", 100, true),
				new ColumnConfig("Qualit&auml;t", "quality", 100, true),
				new ColumnConfig("Design", "design", 100, true),
				new ColumnConfig("&Ouml;kologie", "ecology", 100, true),
				new ColumnConfig("Kapital", "capital", 100, true),
				new ColumnConfig("Marktforschungsbericht", "report", 100, true) };

		ColumnModel columnModel = new ColumnModel(columns);
		grid.setColumnModel(columnModel);

		add(grid);
	}

	private Object[][] getGameData() {
		
		
		return new Object[][] { new Object[] { "3m Co", new Double(71.72),
				new Double(0.02), new Double(0.03), "9/1 12:00am", "MMM",
				"Manufacturing", true } };
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
	}

	public void startGame(ArrayList<Group> groupList) {
		startGameBtn.setDisabled(true);
	}

	public void endGame() {
		startGameBtn.setDisabled(false);
	}
}
