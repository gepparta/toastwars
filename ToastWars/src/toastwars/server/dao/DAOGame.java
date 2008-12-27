package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;
import toastwars.server.datamodel.user.UserFactory;

public class DAOGame {

	public ArrayList<Group> getAllUsers() {
		try {
			DBConnection con = new DBConnection();
			con.connectToDB();
			DAOUser user = new DAOUser();
			ArrayList<Group> userList = user.getAllUsers(con);
			con.closeConnectionToDB();
			return userList;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Integer getCurrentRound() {
		try {
			String query = "SELECT Game.[CurrentRound]FROM Game;";
			DBConnection con = new DBConnection();
			con.connectToDB();
			Statement stmt = con.getStatement();
			ResultSet rst = stmt.executeQuery(query);
			rst.next();
			Integer currentRound = rst.getInt(1);
			rst.close();
			stmt.close();
			con.closeConnectionToDB();
			return currentRound;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Integer getUserAmount() {
		try {
			String query = "SELECT Game.[NumberOfUsers]FROM Game;";
			DBConnection con = new DBConnection();
			con.connectToDB();
			Statement stmt = con.getStatement();
			ResultSet rst = stmt.executeQuery(query);
			rst.next();
			Integer UserAmount = rst.getInt(1);
			rst.close();
			stmt.close();
			con.closeConnectionToDB();
			return UserAmount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void createInitialData(Integer userAmount) {
		try {
			DBConnection con = new DBConnection();
			con.connectToDB();
			Statement stmt = con.getStatement();
			String query = "INSERT INTO Game VALUES (0," + userAmount
					+ ", 'Instruction');";
			stmt.execute(query);
			for (int i = 1; i <= userAmount; i++) {
				query = "INSERT INTO User VALUES ('user" + i + "','password"
						+ i + "'," + i + ",'CREATED');";
				stmt.execute(query);
				query = "INSERT INTO Company VALUES (0," + i + ",'test',1000,1000,1000,1000,1000);";
				stmt.execute(query);
				query = "INSERT INTO Toaster VALUES (0," + i + "," + i + ",1000,1000,1000," +
						"1000,1000,1000,1000,1000,'TYPE1');";
				stmt.execute(query);
			}
			stmt.close();
			con.closeConnectionToDB();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DAOGame user = new DAOGame();
		// ArrayList<Group> userList = user.getAllUsers();
		// Group test= userList.get(0);
		// System.out.println(test.getUsername());
		// Company test1=test.getCompany();
		// System.out.println(test1.getCompanyID());
		// ArrayList<Toaster> test6 = test1.getToasterList();
		// System.out.println(test6.get(0).getToasterID());
		// System.out.println(test6.get(1).getToasterID());
		//
		//		
		// Group test2=userList.get(1);
		// System.out.println(test2.getUsername());
		// Company test3=test2.getCompany();
		// System.out.println(test3.getCompanyID());
		// ArrayList<Toaster> test7 = test3.getToasterList();
		// System.out.println(test7.get(0).getToasterID());
		// System.out.println(test7.get(1).getToasterID());
		// System.out.println(user.getCurrentRound());
		// System.out.println(user.getUserAmount());
		user.createInitialData(4);
	}
}
