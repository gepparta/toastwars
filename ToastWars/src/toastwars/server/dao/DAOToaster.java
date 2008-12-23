package toastwars.server.dao;

import java.sql.ResultSet; //import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;

public class DAOToaster {
	ArrayList<Toaster> toasterList = new ArrayList<Toaster>();

	public ArrayList<Toaster> getAllToaster(Statement stmt) {

		try {
			// Abfrage definieren
			String query = "SELECT * FROM Toaster;";
			ResultSet rst = stmt.executeQuery(query);
			// Zeileninhalt ermitteln
			while (rst.next()) {
				Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),
						rst.getDouble(5), rst.getDouble(6), rst.getDouble(7),
						rst.getDouble(8), rst.getDouble(9), rst.getDouble(10),
						rst.getInt(11), Type.valueOf(rst.getString(12)));
				toasterList.add(toaster);
			}

			System.out.println(toasterList.toString());
			rst.close();
			stmt.close();
			return toasterList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Toaster> getToasterFromCompany(int companyID,
			Statement stmt) {

		try {
			// Abfrage definieren
			String query = "SELECT * FROM Toaster WHERE companyID = "
					+ companyID + ";";
			ResultSet rst = stmt.executeQuery(query);
			// ResultSetMetaData md = rst.getMetaData();
			// int columns = md.getColumnCount();
			// Zeileninhalt ermitteln
			while (rst.next()) {
				Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),
						rst.getDouble(5), rst.getDouble(6), rst.getDouble(7),
						rst.getDouble(8), rst.getDouble(9), rst.getDouble(10),
						rst.getInt(11), Type.valueOf(rst.getString(12)));
				toasterList.add(toaster);
			}

			System.out.println(toasterList.toString());
			rst.close();
			stmt.close();
			return toasterList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<Toaster> getAllActualToaster(Statement stmt) {

		try {
			// Abfrage definieren

			int currentRound = Game.getInstance().getCurrentRound() - 1;
			String query = "SELECT * FROM Toaster WHERE Round = "
					+ currentRound + ";";
			ResultSet rst = stmt.executeQuery(query);
			// Zeileninhalt ermitteln
			while (rst.next()) {
				Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),
						rst.getDouble(5), rst.getDouble(6), rst.getDouble(7),
						rst.getDouble(8), rst.getDouble(9), rst.getDouble(10),
						rst.getInt(11), Type.valueOf(rst.getString(12)));
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

	public ArrayList<Toaster> getActualToasterFromCompany(int companyID,
			DBConnection con) {

		try {
			toasterList.clear();
			// Abfrage definieren
			int currentRound = 1;
			// Game.getInstance().getCurrentRound() -1;
			String query = "SELECT * FROM Toaster WHERE companyID = "
					+ companyID + " AND Round = " + currentRound + ";";
			Statement stmt = con.getStatement();
			ResultSet rst = stmt.executeQuery(query);
			// Zeileninhalt ermitteln
			while (rst.next()) {
				Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),
						rst.getDouble(5), rst.getDouble(6), rst.getDouble(7),
						rst.getDouble(8), rst.getDouble(9), rst.getDouble(10),
						rst.getInt(11), Type.valueOf(rst.getString(12)));
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
