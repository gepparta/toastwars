package toastwars.server.dao;

import java.sql.Connection;
import java.sql.ResultSet; //import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;

public class DAOToaster {
	ArrayList<Toaster>	toasterList	= new ArrayList<Toaster>();

	public void updateToaster(Toaster toaster, int companyID, Connection con) {
		try {
			int currentRound = Game.getInstance().getCurrentRound();
			int toasterID = toaster.getToasterID();
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
			double efficiency = toaster.getEfficiencyInvestment();
			double qualityKum = toaster.getQualityInvestmentKum();
			double designKum = toaster.getDesignInvestmentKum();
			double efficiencyKum = toaster.getEcologyInvestmentKum();
			double index = toaster.getIndex();
			double turnover = toaster.getTurnover();
			double cost = toaster.getCost();
			double profit = toaster.getProfit();
			int marketShare = toaster.getMarketShare();
			String type = toaster.getType().name();
			int production = toaster.getProduction();

			Statement stmt = con.createStatement();
			String query = "UPDATE Toaster SET Toaster.price = " + price
					+ ", Toaster.marketing = " + marketing + ", Toaster.tv = "
					+ tv + ", Toaster.tvKum = " + tvKum
					+ ", Toaster.newspaper = " + newspaper
					+ ", Toaster.newspaperKum = " + newspaperKum
					+ ", Toaster.radio = " + radio + ", Toaster.radioKum = "
					+ radioKum + ", Toaster.research = " + research
					+ ", Toaster.quality = " + quality
					+ ", Toaster.qualityKum = " + qualityKum
					+ ", Toaster.design = " + design + ", Toaster.designKum = "
					+ designKum + ", Toaster.efficiency = " + efficiency
					+ ", Toaster.efficiencyKum = " + efficiencyKum
					+ ", Toaster.[index] = " + index + ", Toaster.turnover = "
					+ turnover + ", Toaster.cost = " + cost
					+ ", Toaster.profit = " + profit
					+ ", Toaster.marketShare = " + marketShare
					+ ", Toaster.type = '" + type
					+ "', Toaster.production = " + production
					+ " WHERE (((Toaster.round)=" + currentRound
					+ ") AND ((Toaster.toasterID)=" + toasterID
					+ ") AND ((Toaster.companyID)=" + companyID + "));";
			stmt.execute(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// test
	public boolean saveToaster(Toaster toaster, int companyID, Connection con) {
		try {
			int currentRound = Game.getInstance().getCurrentRound();
			int toasterID = toaster.getToasterID();
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
			double efficiency = toaster.getEfficiencyInvestment();
			double qualityKum = toaster.getQualityInvestmentKum();
			double designKum = toaster.getDesignInvestmentKum();
			double efficiencyKum = toaster.getEcologyInvestmentKum();
			double index = toaster.getIndex();
			double turnover = toaster.getTurnover();
			double cost = toaster.getCost();
			double profit = toaster.getProfit();
			int marketShare = toaster.getMarketShare();
			String type = toaster.getType().name();
			int production = toaster.getProduction();

			Statement stmt = con.createStatement();
			String query = "INSERT INTO Toaster VALUES (" + currentRound + ","
					+ toasterID + "," + companyID + "," + price + ","
					+ marketing + "," + tv + "," + tvKum + "," + newspaper
					+ "," + newspaperKum + "," + radio + "," + radioKum + ","
					+ research + "," + quality + "," + qualityKum + ","
					+ design + "," + designKum + "," + efficiency + ","
					+ efficiencyKum + "," + index + "," + turnover + "," + cost
					+ "," + profit + "," + marketShare + ",'" + type +
					"'," + production +");";
			return stmt.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Toaster> getActualToasterFromCompany(int companyID,
			Connection con) {

		try {
			toasterList.clear();
			// Abfrage definieren
			int currentRound = Game.getInstance().getCurrentRound();
			String query = "SELECT * FROM Toaster WHERE companyID = "
					+ companyID + " AND Round = " + currentRound + ";";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);

			// this.toasterID = toasterID;
			// this.price = price;
			// this.index = index;
			// // this.turnover = turnover;
			// this.cost = cost;
			// this.profit = profit;
			// this.marketShare = marketShare;
			// this.type = type;
			// this.marketing = marketing;
			// this.tvInvestment = tvInvestment;
			// this.newspaperInvestment = newspaperInvestment;
			// this.radioInvestment = radioInvestment;
			// this.tvInvestmentKum = tvInvestmentKum;
			// this.newspaperInvestmentKum = newspaperInvestmentKum;
			// this.radioInvestmentKum = radioInvestmentKum;
			// this.research = research;
			// this.qualityInvestment = qualityInvestment;
			// this.designInvestment = designInvestment;
			// this.ecologyInvestment = ecologyInvestment;
			// this.qualityInvestmentKum = qualityInvestmentKum;
			// this.designInvestmentKum = designInvestmentKum;
			// this.ecologyInvestmentKum = ecologyInvestmentKum;
			// Zeileninhalt ermitteln
			while (rst.next()) {
				Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),
						rst.getDouble(19), rst.getDouble(20),
						rst.getDouble(21), rst.getDouble(22), rst.getInt(23),
						Type.valueOf(rst.getString(24)), rst.getDouble(5), rst
								.getDouble(6), rst.getDouble(8), rst
								.getDouble(10), rst.getDouble(7), rst
								.getDouble(9), rst.getDouble(11), rst
								.getDouble(12), rst.getDouble(13), rst
								.getDouble(15), rst.getDouble(17), rst
								.getDouble(14), rst.getDouble(16), rst
								.getDouble(18));
				toaster.setProduction(rst.getInt(25));
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

	public ArrayList<Toaster> getToasterFromCompanyByRound(int companyID,
			Connection con, Integer round) {

		try {
			toasterList.clear();
			// Abfrage definieren
			String query = "SELECT * FROM Toaster WHERE companyID = "
					+ companyID + " AND Round = " + round + ";";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);

			// this.toasterID = toasterID;
			// this.price = price;
			// this.index = index;
			// // this.turnover = turnover;
			// this.cost = cost;
			// this.profit = profit;
			// this.marketShare = marketShare;
			// this.type = type;
			// this.marketing = marketing;
			// this.tvInvestment = tvInvestment;
			// this.newspaperInvestment = newspaperInvestment;
			// this.radioInvestment = radioInvestment;
			// this.tvInvestmentKum = tvInvestmentKum;
			// this.newspaperInvestmentKum = newspaperInvestmentKum;
			// this.radioInvestmentKum = radioInvestmentKum;
			// this.research = research;
			// this.qualityInvestment = qualityInvestment;
			// this.designInvestment = designInvestment;
			// this.ecologyInvestment = ecologyInvestment;
			// this.qualityInvestmentKum = qualityInvestmentKum;
			// this.designInvestmentKum = designInvestmentKum;
			// this.ecologyInvestmentKum = ecologyInvestmentKum;
			// Zeileninhalt ermitteln
			while (rst.next()) {
				Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),
						rst.getDouble(19), rst.getDouble(20),
						rst.getDouble(21), rst.getDouble(22), rst.getInt(23),
						Type.valueOf(rst.getString(24)), rst.getDouble(5), rst
								.getDouble(6), rst.getDouble(8), rst
								.getDouble(10), rst.getDouble(7), rst
								.getDouble(9), rst.getDouble(11), rst
								.getDouble(12), rst.getDouble(13), rst
								.getDouble(15), rst.getDouble(17), rst
								.getDouble(14), rst.getDouble(16), rst
								.getDouble(18));
				toaster.setProduction(rst.getInt(25));
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
