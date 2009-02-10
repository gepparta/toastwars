package toastwars.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOScenario {

	public static String getScenarioByRound(int round, Connection con) {
		try {
			// Abfrage definieren
			String query = "SELECT Scenario.Description FROM Scenario WHERE  Round = "
					+ round + ";";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);
			// Zeileninhalt ermitteln
			while (rst.next()) {
				String description = rst.getString(1);
				rst.close();
				stmt.close();
				return description;
			}
			rst.close();
			stmt.close();
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateScenario() {
		try {
			Connection con = DBConnection.getInstance().connectToDB();
			Statement stmt = con.createStatement();
			String query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);
			query = "UPDATE Scenario SET Scenario.Description = '' WHERE (Scenario.Round=);";
			stmt.execute(query);

			stmt.close();
			DBConnection.getInstance().closeConnectionToDB(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}