package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;

public class DAOCompany {

	public boolean updateCompany(Company company, DBConnection con) {
		try {
			int currentRound = Game.getInstance().getCurrentRound();
			int companyID = company.getCompanyID();
			double turnover = company.getTurnover();
			double cost = company.getCost();
			double profit = company.getProfit();
			double capital = company.getCapital();
			int marketShare = company.getMarketShare();
			Statement stmt = con.getStatement();
			String query = "UPDATE Company SET Company.turnover = " + turnover
					+ ", Company.cost = " + cost + ", Company.profit = "
					+ profit + ", Company.capital = " + capital
					+ ", Company.marketShare = " + marketShare
					+ " WHERE (((Company.round)=" + currentRound
					+ ") AND ((Company.companyID)=" + companyID + "));";
			stmt.execute(query);
			ArrayList<Toaster> toasterList = company.getToasterList();
			int size = toasterList.size();
			DAOToaster daoToaster = new DAOToaster();
			for (int i = 0; i < size; i++) {
				Toaster toaster = toasterList.get(i);
				daoToaster.updateToaster(toaster, companyID, con);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// test
	public boolean saveCompany(Company company, DBConnection con) {
		try {
			int currentRound = Game.getInstance().getCurrentRound();
			int companyID = company.getCompanyID();
			double turnover = company.getTurnover();
			double cost = company.getCost();
			double profit = company.getProfit();
			double capital = company.getCapital();
			int marketShare = company.getMarketShare();
			Statement stmt = con.getStatement();
			String query = "INSERT INTO Company VALUES (" + currentRound + ","
					+ companyID + "," + turnover + "," + cost + "," + profit
					+ "," + capital + "," + marketShare + ");";
			stmt.execute(query);
			ArrayList<Toaster> toasterList = company.getToasterList();
			int size = toasterList.size();
			DAOToaster daoToaster = new DAOToaster();
			for (int i = 0; i < size; i++) {
				Toaster toaster = toasterList.get(i);
				daoToaster.saveToaster(toaster, companyID, con);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Company getCurrentCompany(DBConnection con, Integer companyID) {

		try {
			// Abfrage definieren
			int currentRound = Game.getInstance().getCurrentRound();
			String query = "SELECT * FROM Company WHERE companyID = "
					+ companyID + " AND Round = " + currentRound + ";";
			Statement stmt = con.getStatement();
			ResultSet rst = stmt.executeQuery(query);
			// Zeileninhalt ermitteln
			while (rst.next()) {
				DAOToaster toaster = new DAOToaster();
				companyID = rst.getInt(2);
				Company company = new Company(companyID, rst.getDouble(3), rst
						.getDouble(4), rst.getDouble(5), rst.getDouble(6), rst
						.getInt(7), toaster.getActualToasterFromCompany(
						companyID, con));

				rst.close();
				stmt.close();
				return company;
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
