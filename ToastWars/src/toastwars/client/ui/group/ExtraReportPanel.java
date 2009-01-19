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

public class ExtraReportPanel extends Panel
{

	private static ExtraReportPanel reportPanel;
	private ArrayList<List<String>> report;

	public static ExtraReportPanel getInstance()
	{
		if (reportPanel == null)
			reportPanel = new ExtraReportPanel();
		return reportPanel;
	}

	private ExtraReportPanel()
	{
		Game game = Controller.getInstance().getGame();
		report = ((Group) Controller.getInstance().getUser()).getCompany().getReportListe();

		setTitle("Marktforschungsbericht");
		setPaddings(15);

		GridPanel grid = new GridPanel();
		grid.setFrame(true);
		grid.setStripeRows(true);
		grid.setHeight(280);
		grid.setWidth(800);
		grid.setTitle("Platzierungen in der Runde " + (game.getCurrentRound() - 1));

		RecordDef recordDef = new RecordDef(new FieldDef[]
		{ new StringFieldDef("rank"), new StringFieldDef("magazine"), new StringFieldDef("radio"), new StringFieldDef("tv"), new StringFieldDef("quality"),
				new StringFieldDef("design"), new StringFieldDef("ecology") });

		ArrayReader reader = new ArrayReader(recordDef);
		Store store = new Store(new MemoryProxy(getGameData()), reader);
		store.load();
		grid.setStore(store);

		ColumnConfig[] columns = new ColumnConfig[]
		{ new ColumnConfig("Platz", "rank", 70, true), new ColumnConfig("Zeitung", "magazine", 100, true), new ColumnConfig("Radio", "radio", 100, true),
				new ColumnConfig("TV", "tv", 100, true), new ColumnConfig("Qualit&auml;t", "quality", 100, true), new ColumnConfig("Design", "design", 100, true),
				new ColumnConfig("&Ouml;kologie", "ecology", 100, true) };

		ColumnModel columnModel = new ColumnModel(columns);
		grid.setColumnModel(columnModel);

		add(grid);
	}

	private Object[][] getGameData()
	{
		Object[][] data = new Object[report.get(1).size()][7];

		for (int i = 0; i < data.length; i++)
		{
			data[i][0] = i + 1;
			for (int j = 1; j < report.size(); j++)
			{
				data[i][j] = report.get(j).get(i);
			}
		}

		return data;
	}
}
