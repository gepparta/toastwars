package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;

public class DAOGame {

	public static ArrayList<IUser> getAllUsers() {
		try {
			DBConnection con = new DBConnection();
			con.connectToDB();
			DAOUser user = new DAOUser();
			ArrayList<IUser> userList = user.getAllUsers(con);
			con.closeConnectionToDB();
			return userList;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static void saveAllUsers(ArrayList<Group> userList) {
		try {
			DBConnection con = new DBConnection();
			con.connectToDB();
			int size = userList.size();
			DAOUser user = new DAOUser();
			for (int i = 0; i < size; i++) {
				Group group = userList.get(i);
				user.saveUser(group, con);
			}
			changeCurrentRound(con);
			con.closeConnectionToDB();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Integer getCurrentRound() {
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

	public static Integer getUserAmount() {
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
	public static void changeCurrentRound(DBConnection con){
		int currentRound = 1;
//		Game.getInstance().getCurrentRound();
		con.connectToDB();
		Statement stmt = con.getStatement();
		String sql = "UPDATE [Game] SET [Game].CurrentRound = '" + currentRound + "'";
		try {
			stmt.execute(sql);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void createInitialData(Integer userAmount) {
		try {
			DBConnection con = new DBConnection();
			con.connectToDB();
			Statement stmt = con.getStatement();
			String query = "INSERT INTO Game VALUES (0," + userAmount
					+ ", 'Instruction');";
			stmt.execute(query);
			for (int i = 1; i <= userAmount; i++) {
				query = "INSERT INTO User VALUES ('user" + i + "','password"
						+ i + "'," + i + ",'STARTED');";
				stmt.execute(query);
				query = "INSERT INTO Company VALUES (0," + i + ",1000,1000,1000,1000,1000);";
				stmt.execute(query);
				query = "INSERT INTO Toaster VALUES (0," + i + "," + i + ",1000,1000,1000," +
						"1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,'TYPE1');";
				stmt.execute(query);
			}
			stmt.close();
			con.closeConnectionToDB();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void resetGame() {
		try {
			DBConnection con = new DBConnection();
			con.connectToDB();
			Statement stmt = con.getStatement();
			String query = "DELETE * FROM Game;";
			stmt.execute(query);
			query = "DELETE * FROM User;";
			stmt.execute(query);
			query = "DELETE * FROM Company;";
			stmt.execute(query);
			query = "DELETE * FROM Toaster;";
			stmt.execute(query);
			stmt.close();
			con.closeConnectionToDB();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//
//		resetGame();
//		createInitialData(5); 
//		ArrayList<Group> userList = getAllUsers();
//		saveAllUsers(userList);
//	}
}
