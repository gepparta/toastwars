package toastwars.server.dao;

import java.sql.Connection;
import java.sql.ResultSet; //import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DAOScenario
{

	public static String getScenarioByRound(int round, Connection con)
	{

		try
		{
			// Abfrage definieren
			String query = "SELECT Scenario.Description FROM Scenario WHERE  Round = " + round + ";";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);

			// Zeileninhalt ermitteln
			while (rst.next())
			{
				String description = rst.getString(1);
				rst.close();
				stmt.close();
				System.out.println(description);
				return description;
			}
			return null;

		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}