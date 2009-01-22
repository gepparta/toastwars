package toastwars.server.dao;

import java.sql.Connection;
import java.sql.ResultSet; //import java.sql.ResultSetMetaData;
import java.sql.Statement;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Stock;

public class DAOStock
{

	public void updateStock(Stock stock, int companyID, Connection con)
	{
		try
		{
			int currentRound = Game.getInstance().getCurrentRound();
			int stockTT1 = stock.getStockTT1();
			int stockTT2 = stock.getStockTT2();
			int stockTT3 = stock.getStockTT3();
			double totalStockCosts = stock.getTotalStockCosts();

			Statement stmt = con.createStatement();
			String query = "UPDATE Stock SET stockTT1 = " + stockTT1 + ", stockTT2 = " + stockTT2 + ", stockTT3 = " + stockTT3 + ", totalCosts = "
					+ totalStockCosts + " WHERE (((round)=" + currentRound + ") AND ((companyID)=" + companyID + "));";
			stmt.execute(query);
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// test
	public void saveStock(Stock stock, int companyID, Connection con)
	{
		try
		{
			int currentRound = Game.getInstance().getCurrentRound();
			int stockTT1 = stock.getStockTT1();
			int stockTT2 = stock.getStockTT2();
			int stockTT3 = stock.getStockTT3();
			double totalStockCosts = stock.getTotalStockCosts();

			Statement stmt = con.createStatement();
			String query = "INSERT INTO Stock VALUES (" + companyID + "," + currentRound + "," + stockTT1 + "," + stockTT2 + "," + stockTT3 + "," + totalStockCosts + ");";
			stmt.execute(query);
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Stock getActualStockFromCompany(int companyID, Connection con)
	{

		try
		{
			// Abfrage definieren
			int currentRound = Game.getInstance().getCurrentRound();
			String query = "SELECT * FROM Stock WHERE companyID = " + companyID + " AND Round = " + currentRound + ";";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);

			// Zeileninhalt ermitteln
			while (rst.next())
			{
				Stock stock = new Stock(rst.getInt(3), rst.getInt(4), rst.getInt(5), rst.getDouble(6));
				rst.close();
				stmt.close();
				return stock;
			}
			return null;

		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public Stock getStockFromCompanyByRound(int companyID, Connection con, Integer round)
	{

		try
		{
			// Abfrage definieren
			String query = "SELECT * FROM Stock WHERE companyID = " + companyID + " AND Round = " + round + ";";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);

			// Zeileninhalt ermitteln
			while (rst.next())
			{
				Stock stock = new Stock(rst.getInt(3), rst.getInt(4), rst.getInt(5), rst.getDouble(6));
				rst.close();
				stmt.close();
				return stock;
			}
			return null;

		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}