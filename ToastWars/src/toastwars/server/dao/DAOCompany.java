package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import toastwars.server.datamodel.core.Company;

public class DAOCompany {
	ArrayList<Company> companyList = new ArrayList<Company>();
	
	public ArrayList<Company> getAllCurrentCompanies(DBConnection con) {

			try {
				companyList.clear();
				// Abfrage definieren
				int currentRound = 1;
//					Game.getInstance().getCurrentRound() -1;
				String query = "SELECT * FROM Company WHERE Round = "+ currentRound +";";
				Statement stmt = con.getStatement();
				ResultSet rst = stmt.executeQuery(query);
				// Zeileninhalt ermitteln
				while (rst.next()) {
					DAOToaster toaster = new DAOToaster();
					int companyID = rst.getInt(2);
					Company company = new Company(companyID,rst.getDouble(3),
							rst.getDouble(4), rst.getDouble(5), rst.getDouble(6), rst.getInt(7),
							toaster.getActualToasterFromCompany(companyID, con));
					companyList.add(company);
					}
				rst.close();
				stmt.close();
				return companyList;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
}
