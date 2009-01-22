package toastwars.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Stock;

public class DAOCompany {

	public boolean updateCompany(Company company, Connection con) {
		try {
			int currentRound = Game.getInstance().getCurrentRound();
			int companyID = company.getCompanyID();
			double turnover = company.getTurnover();
			double cost = company.getCost();
			double profit = company.getProfit();
			double capital = company.getCapital();
			int marketShare = company.getMarketShare();
			boolean extraReport = company.isMarketResearchReportON();
			Statement stmt = con.createStatement();
			String query = "UPDATE Company SET turnover = " + turnover
					+ ", cost = " + cost + ", profit = "
					+ profit + ", capital = " + capital
					+ ", marketShare = " + marketShare
					+ ", extraReport = " + extraReport
					+" WHERE (((round)=" + currentRound
					+ ") AND ((companyID)=" + companyID + "));";
			stmt.execute(query);
			ArrayList<Toaster> toasterList = company.getToasterList();
			int size = toasterList.size();
			DAOToaster daoToaster = new DAOToaster();
			for (int i = 0; i < size; i++) {
				Toaster toaster = toasterList.get(i);
				daoToaster.updateToaster(toaster, companyID, con);
			}
			Stock stock = company.getStock();
			DAOStock daoStock = new DAOStock();
			daoStock.updateStock(stock, companyID, con);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// test
	public boolean saveCompany(Company company, Connection con) {
		try {
			int currentRound = Game.getInstance().getCurrentRound();
			int companyID = company.getCompanyID();
			double turnover = company.getTurnover();
			double cost = company.getCost();
			double profit = company.getProfit();
			double capital = company.getCapital();
			int marketShare = company.getMarketShare();
			boolean extraReport = company.isMarketResearchReportON();
			Statement stmt = con.createStatement();
			String query = "INSERT INTO Company VALUES (" + currentRound + ","
					+ companyID + "," + turnover + "," + cost + "," + profit
					+ "," + capital + "," + marketShare + ","+ extraReport+");";
			stmt.execute(query);
			ArrayList<Toaster> toasterList = company.getToasterList();
			int size = toasterList.size();
			DAOToaster daoToaster = new DAOToaster();
			for (int i = 0; i < size; i++) {
				Toaster toaster = toasterList.get(i);
				daoToaster.saveToaster(toaster, companyID, con);
			}
			Stock stock = company.getStock();
			DAOStock daoStock = new DAOStock();
			daoStock.saveStock(stock, companyID, con);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Company getCurrentCompany(Connection con, Integer companyID) {

		try {
			// Abfrage definieren
			int currentRound = Game.getInstance().getCurrentRound();
			String query = "SELECT * FROM Company WHERE companyID = "
					+ companyID + " AND Round = " + currentRound + ";";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);
			boolean extraReport;
			// Zeileninhalt ermitteln
			while (rst.next()) {
				DAOToaster toaster = new DAOToaster();
				DAOStock stock = new DAOStock();
				companyID = rst.getInt(2);
				Company company = new Company(companyID, rst.getDouble(3), rst
						.getDouble(4), rst.getDouble(5), rst.getDouble(6), rst
						.getInt(7),stock.getActualStockFromCompany(companyID, con), toaster.getActualToasterFromCompany(
						companyID, con));
				extraReport = rst.getBoolean(8);
				company.setMarketResearchReportON(extraReport);
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
	public Company getCompanyByRound(Connection con, Integer companyID, Integer round) {

		try {
			// Abfrage definieren
			String query = "SELECT * FROM Company WHERE companyID = "
					+ companyID + " AND Round = " + round + ";";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);
			boolean extraReport;
			// Zeileninhalt ermitteln
			while (rst.next()) {
				DAOToaster toaster = new DAOToaster();
				DAOStock stock = new DAOStock();
				companyID = rst.getInt(2);
				Company company = new Company(companyID, rst.getDouble(3), rst
						.getDouble(4), rst.getDouble(5), rst.getDouble(6), rst
						.getInt(7), stock.getStockFromCompanyByRound(companyID, con, round) 
						,toaster.getToasterFromCompanyByRound(
						companyID, con, round));
				extraReport = rst.getBoolean(8);
				company.setMarketResearchReportON(extraReport);
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
