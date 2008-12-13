package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;

public class DAOToaster {
	ArrayList<Toaster> toasterList = new ArrayList<Toaster>();

	public ArrayList getAllToaster() {

			try {
				// Abfrage definieren
				String query = "SELECT * FROM Toaster;";
				DBConnection con = new DBConnection();
				con.connectToDB();
				Statement stmt = con.getStatement();
				ResultSet rst = stmt.executeQuery(query);
				ResultSetMetaData md = rst.getMetaData();
				int columns = md.getColumnCount();
				// Zeileninhalt ermitteln
				while (rst.next()) {
					Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),rst.getDouble(5), rst.getDouble(6), rst.getDouble(7), rst.getDouble(8),rst.getDouble(9), rst.getDouble(10), rst.getInt(11), Type.valueOf(rst.getString(12)));
					toasterList.add(toaster);
					}

				System.out.println(toasterList.toString());
				rst.close();
				stmt.close();
				con.closeConnectionToDB();
				return toasterList;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
	public ArrayList getToasterFromCompany(int companyID) {

			try {
				// Abfrage definieren
				String query = "SELECT * FROM Toaster WHERE companyID = "+ companyID + ";";
				DBConnection con = new DBConnection();
				con.connectToDB();
				Statement stmt = con.getStatement();
				ResultSet rst = stmt.executeQuery(query);
				ResultSetMetaData md = rst.getMetaData();
				int columns = md.getColumnCount();
				// Zeileninhalt ermitteln
				while (rst.next()) {
					Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),rst.getDouble(5), rst.getDouble(6), rst.getDouble(7), rst.getDouble(8),rst.getDouble(9), rst.getDouble(10), rst.getInt(11), Type.valueOf(rst.getString(12)));
					toasterList.add(toaster);
					}

				System.out.println(toasterList.toString());
				rst.close();
				stmt.close();
				con.closeConnectionToDB();
				return toasterList;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

	}

	public ArrayList getAllActualToaster() {

		try {
			// Abfrage definieren
			
			int currentRound = Game.getInstance().getCurrentRound() -1;
			String query = "SELECT * FROM Toaster WHERE Round = "+ currentRound +";";
			DBConnection con = new DBConnection();
			con.connectToDB();
			Statement stmt = con.getStatement();
			ResultSet rst = stmt.executeQuery(query);
			ResultSetMetaData md = rst.getMetaData();
			int columns = md.getColumnCount();
			// Zeileninhalt ermitteln
			while (rst.next()) {
				Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),rst.getDouble(5), rst.getDouble(6), rst.getDouble(7), rst.getDouble(8),rst.getDouble(9), rst.getDouble(10), rst.getInt(11), Type.valueOf(rst.getString(12)));
				toasterList.add(toaster);
				}

			System.out.println(toasterList.toString());
			rst.close();
			stmt.close();
			con.closeConnectionToDB();
			return toasterList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
}
public ArrayList getActualToasterFromCompany(int companyID) {

		try {
			// Abfrage definieren
			int currentRound = 1;
				//Game.getInstance().getCurrentRound() -1;
			String query = "SELECT * FROM Toaster WHERE companyID = "+ companyID + " AND Round = "+ currentRound +";";
			DBConnection con = new DBConnection();
			con.connectToDB();
			Statement stmt = con.getStatement();
			ResultSet rst = stmt.executeQuery(query);
			ResultSetMetaData md = rst.getMetaData();
			int columns = md.getColumnCount();
			// Zeileninhalt ermitteln
			while (rst.next()) {
				Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),rst.getDouble(5), rst.getDouble(6), rst.getDouble(7), rst.getDouble(8),rst.getDouble(9), rst.getDouble(10), rst.getInt(11), Type.valueOf(rst.getString(12)));
				toasterList.add(toaster);
				}

//			System.out.println(toasterList.toString());
			rst.close();
			stmt.close();
			con.closeConnectionToDB();
			return toasterList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

}
//public static void main(String[] args) {
//	DAOToaster toaster = new DAOToaster();
//	toaster.getToasterFromCompany(1);
//}
}
