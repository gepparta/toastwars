package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;

public class DAOCompany {
	ArrayList<Company> companyList = new ArrayList<Company>();
	private DAOToaster toaster = new DAOToaster();
	public ArrayList getAllCurrentCompanies() {

			try {
				// Abfrage definieren
				int currentRound = 1;
//					Game.getInstance().getCurrentRound() -1;
				String query = "SELECT * FROM Company WHERE Round = "+ currentRound +";";
				DBConnection con = new DBConnection();
				con.connectToDB();
				Statement stmt = con.getStatement();
				ResultSet rst = stmt.executeQuery(query);
				ResultSetMetaData md = rst.getMetaData();
				int columns = md.getColumnCount();
				// Zeileninhalt ermitteln
				while (rst.next()) {
					int companyID = rst.getInt(2);
					Company company = new Company(companyID, rst.getString(3),rst.getDouble(4),rst.getDouble(5), rst.getDouble(6), rst.getDouble(7), rst.getInt(8),toaster.getActualToasterFromCompany(companyID));
					companyList.add(company);
//					toaster.toString();
					}
				rst.close();
				stmt.close();
				con.closeConnectionToDB();
				return companyList;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
	
public static void main(String[] args) {
	DAOCompany company = new DAOCompany();
	ArrayList<Company> companyList = company.getAllCurrentCompanies();
	System.out.println(companyList.toString());
}
}
