package toastwars.server.dao;
/*
 * Author: Tobias Merx
 * */
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
}