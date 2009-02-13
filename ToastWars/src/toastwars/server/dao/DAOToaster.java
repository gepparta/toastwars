package toastwars.server.dao;
/*
 * Author: Tobias Merx
 * */
import java.sql.Connection;
import java.sql.ResultSet; //import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;

public class DAOToaster
{
	ArrayList<Toaster> toasterList = new ArrayList<Toaster>();

	public void updateToaster(Toaster toaster, int companyID, Connection con)
	{
		try
		{
			int currentRound = Game.getInstance().getCurrentRound();
			double price = toaster.getPrice();
			double marketing = toaster.getMarketing();
			double tv = toaster.getTvInvestment();
			double newspaper = toaster.getNewspaperInvestment();
			double radio = toaster.getRadioInvestment();
			double tvKum = toaster.getTvInvestmentKum();
			double newspaperKum = toaster.getNewspaperInvestmentKum();
			double radioKum = toaster.getRadioInvestmentKum();
			double research = toaster.getResearch();
			double quality = toaster.getQualityInvestment();
			double design = toaster.getDesignInvestment();
			double ecology = toaster.getEcologyInvestment();
			double qualityKum = toaster.getQualityInvestmentKum();
			double designKum = toaster.getDesignInvestmentKum();
			double ecologyKum = toaster.getEcologyInvestmentKum();
			double index = toaster.getIndex();
			double turnover = toaster.getTurnover();
			double cost = toaster.getCost();
			double profit = toaster.getProfit();
			int marketShare = toaster.getMarketShare();
			String type = toaster.getType().name();
			int production = toaster.getProduction();

			Statement stmt = con.createStatement();
			String query = "UPDATE Toaster SET Toaster.price = " + price + ", Toaster.marketing = " + marketing + ", Toaster.tv = " + tv + ", Toaster.tvKum = " + tvKum
					+ ", Toaster.newspaper = " + newspaper + ", Toaster.newspaperKum = " + newspaperKum + ", Toaster.radio = " + radio + ", Toaster.radioKum = " + radioKum
					+ ", Toaster.research = " + research + ", Toaster.quality = " + quality + ", Toaster.qualityKum = " + qualityKum + ", Toaster.design = " + design
					+ ", Toaster.designKum = " + designKum + ", Toaster.ecology = " + ecology + ", Toaster.ecologyKum = " + ecologyKum + ", Toaster.index = " + index
					+ ", Toaster.turnover = " + turnover + ", Toaster.cost = " + cost + ", Toaster.profit = " + profit + ", Toaster.marketShare = " + marketShare
					+ ", Toaster.production = " + production + " WHERE (Toaster.round=" + currentRound + " AND Toaster.companyID=" + companyID + " AND Toaster.type='" + type
					+ "');";
			stmt.execute(query);
			stmt.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// test
	public void saveToaster(Toaster toaster, int companyID, Connection con) throws SQLException
	{
		try {
			int currentRound = Game.getInstance().getCurrentRound();
			double price = toaster.getPrice();
			double marketing = toaster.getMarketing();
			double tv = toaster.getTvInvestment();
			double newspaper = toaster.getNewspaperInvestment();
			double radio = toaster.getRadioInvestment();
			double tvKum = toaster.getTvInvestmentKum();
			double newspaperKum = toaster.getNewspaperInvestmentKum();
			double radioKum = toaster.getRadioInvestmentKum();
			double research = toaster.getResearch();
			double quality = toaster.getQualityInvestment();
			double design = toaster.getDesignInvestment();
			double ecology = toaster.getEcologyInvestment();
			double qualityKum = toaster.getQualityInvestmentKum();
			double designKum = toaster.getDesignInvestmentKum();
			double ecologyKum = toaster.getEcologyInvestmentKum();
			double index = toaster.getIndex();
			double turnover = toaster.getTurnover();
			double cost = toaster.getCost();
			double profit = toaster.getProfit();
			int marketShare = toaster.getMarketShare();
			String type = toaster.getType().name();
			int production = toaster.getProduction();
			Statement stmt = con.createStatement();
			String query = "INSERT INTO Toaster VALUES (" + currentRound + ","
					+ companyID + ",'" + type + "'," + price + "," + marketing
					+ "," + tv + "," + tvKum + "," + newspaper + ","
					+ newspaperKum + "," + radio + "," + radioKum + ","
					+ research + "," + quality + "," + qualityKum + ","
					+ design + "," + designKum + "," + ecology + ","
					+ ecologyKum + "," + index + "," + turnover + "," + cost
					+ "," + profit + "," + marketShare + "," + production
					+ ");";
			stmt.execute(query);
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public ArrayList<Toaster> getActualToasterFromCompany(int companyID, Connection con)
	{

		try
		{
			toasterList.clear();
			// Abfrage definieren
			int currentRound = Game.getInstance().getCurrentRound();
			String query = "SELECT * FROM Toaster WHERE companyID = " + companyID + " AND Round = " + currentRound + ";";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);

			// Zeileninhalt ermitteln
			while (rst.next())
			{
				Toaster toaster = new Toaster(rst.getDouble("price"), rst.getDouble("index"), rst.getDouble("turnover"), rst.getDouble("cost"), rst.getDouble("profit"), rst
						.getInt("marketShare"), Type.valueOf(rst.getString("type")), rst.getDouble("marketing"), rst.getDouble("tv"), rst.getDouble("newspaper"), rst
						.getDouble("radio"), rst.getDouble("tvKum"), rst.getDouble("newspaperKum"), rst.getDouble("radioKum"), rst.getDouble("research"), rst.getDouble("quality"),
						rst.getDouble("design"), rst.getDouble("ecology"), rst.getDouble("qualityKum"), rst.getDouble("designKum"), rst.getDouble("ecologyKum"), rst.getInt("production"));
				toasterList.add(toaster);
			}
			rst.close();
			stmt.close();
			return toasterList;

		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Toaster> getToasterFromCompanyByRound(int companyID, Connection con, Integer round)
	{
		ArrayList<Toaster> toasterListByRound = new ArrayList<Toaster>();
		try
		{
			toasterListByRound.clear();
			// Abfrage definieren
			String query = "SELECT * FROM Toaster WHERE companyID = " + companyID + " AND Round = " + round + ";";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);

			// Zeileninhalt ermitteln
			while (rst.next())
			{
				Toaster toaster = new Toaster(rst.getDouble("price"), rst.getDouble("index"), rst.getDouble("turnover"), rst.getDouble("cost"), rst.getDouble("profit"), rst
						.getInt("marketShare"), Type.valueOf(rst.getString("type")), rst.getDouble("marketing"), rst.getDouble("tv"), rst.getDouble("newspaper"), rst
						.getDouble("radio"), rst.getDouble("tvKum"), rst.getDouble("newspaperKum"), rst.getDouble("radioKum"), rst.getDouble("research"), rst.getDouble("quality"),
						rst.getDouble("design"), rst.getDouble("ecology"), rst.getDouble("qualityKum"), rst.getDouble("designKum"), rst.getDouble("ecologyKum"), rst.getInt("production"));
				toasterListByRound.add(toaster);
			}
			rst.close();
			stmt.close();
			return toasterListByRound;

		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public static boolean isThisToasterNew(Toaster toaster,  int companyID, Connection con){
		
		try {
			int round = Game.getInstance().getCurrentRound()- 1;
			String type = toaster.getType().name();
			String query = "SELECT * FROM Toaster WHERE companyID = "
					+ companyID + " AND Round = " + round + " AND type = '"+ type + "';";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);
			if (rst.next()==false) {
				rst.close();
				stmt.close();
				return true;
			}
			else {
				rst.close();
				stmt.close();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
}
