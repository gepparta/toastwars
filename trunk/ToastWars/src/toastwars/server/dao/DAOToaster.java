package toastwars.server.dao;

import java.sql.ResultSet; //import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;

public class DAOToaster {
	ArrayList<Toaster>	toasterList	= new ArrayList<Toaster>();

	public void updateToaster(Toaster toaster, int companyID, DBConnection con) {
		try {
			int currentRound = Game.getInstance().getCurrentRound();
			int toasterID = toaster.getToasterID();
			double price = toaster.getPrice();
			double marketing = toaster.getMarketing();
			double tv = toaster.getTvInvestment();
			double newspaper = toaster.getNewspaperInvestment();
			double radio = toaster.getRadioInvestment();
			double research = toaster.getResearch();
			double quality = toaster.getQualityInvestment();
			double design = toaster.getDesignInvestment();
			double efficiency = toaster.getEfficiencyInvestment();
			double index = toaster.getIndex();
			double turnover = toaster.getTurnover();
			double cost = toaster.getCost();
			double profit = toaster.getProfit();
			int marketShare = toaster.getMarketShare();
			String type = toaster.getType().name();

			Statement stmt = con.getStatement();
			String query = "UPDATE Toaster SET Toaster.price = " + price
					+ ", Toaster.marketing = " + marketing + ", Toaster.tv = "
					+ tv + ", Toaster.newspaper = " + newspaper
					+ ", Toaster.radio = " + radio + ", Toaster.research = "
					+ research + ", Toaster.quality = " + quality
					+ ", Toaster.design = " + design
					+ ", Toaster.efficiency = " + efficiency
					+ ", Toaster.[index] = " + index + ", Toaster.turnover = "
					+ turnover + ", Toaster.cost = " + cost
					+ ", Toaster.profit = " + profit
					+ ", Toaster.marketShare = " + marketShare
					+ ", Toaster.type = '" + type
					+ "' WHERE (((Toaster.round)=" + currentRound
					+ ") AND ((Toaster.toasterID)=" + toasterID
					+ ") AND ((Toaster.companyID)=" + companyID + "));";
			stmt.execute(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// test
	public void saveToaster(Toaster toaster, int companyID, DBConnection con) {
		try {
			int currentRound = Game.getInstance().getCurrentRound();
			int toasterID = toaster.getToasterID();
			double price = toaster.getPrice();
			double marketing = toaster.getMarketing();
			double tv = toaster.getTvInvestment();
			double newspaper = toaster.getNewspaperInvestment();
			double radio = toaster.getRadioInvestment();
			double research = toaster.getResearch();
			double quality = toaster.getQualityInvestment();
			double design = toaster.getDesignInvestment();
			double efficiency = toaster.getEfficiencyInvestment();
			double index = toaster.getIndex();
			double turnover = toaster.getTurnover();
			double cost = toaster.getCost();
			double profit = toaster.getProfit();
			int marketShare = toaster.getMarketShare();
			String type = toaster.getType().name();

			Statement stmt = con.getStatement();
			String query = "INSERT INTO Toaster VALUES (" + currentRound + ","
					+ toasterID + "," + companyID + "," + price + ","
					+ marketing + "," + tv + "," + newspaper + "," + radio
					+ "," + research + "," + quality + "," + design + ","
					+ efficiency + "," + index + "," + turnover + "," + cost
					+ "," + profit + "," + marketShare + ",'" + type + "');";
			stmt.execute(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public ArrayList<Toaster> getActualToasterFromCompany(int companyID,
			DBConnection con) {

		try {
			toasterList.clear();
			// Abfrage definieren
			int currentRound = Game.getInstance().getCurrentRound();
			String query = "SELECT * FROM Toaster WHERE companyID = "
					+ companyID + " AND Round = " + currentRound + ";";
			Statement stmt = con.getStatement();
			ResultSet rst = stmt.executeQuery(query);
			// Zeileninhalt ermitteln
			while (rst.next()) {
				Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),
						rst.getDouble(5), rst.getDouble(6), rst.getDouble(7),
						rst.getDouble(8), rst.getDouble(9), rst.getDouble(10),
						rst.getDouble(11), rst.getDouble(12),
						rst.getDouble(13), rst.getDouble(14),
						rst.getDouble(15), rst.getDouble(16), rst.getInt(17),
						Type.valueOf(rst.getString(18)));
				toasterList.add(toaster);
			}
			rst.close();
			stmt.close();
			return toasterList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
