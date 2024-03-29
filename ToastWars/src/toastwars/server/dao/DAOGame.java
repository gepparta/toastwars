package toastwars.server.dao;
/*
 * Author: Tobias Merx
 * */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;

public class DAOGame {

	public static ArrayList<Group> getAllUsers(Connection con) {
		ArrayList<Group> userList = new ArrayList<Group>();
		try {
			userList = DAOUser.getAllUsers(con);
			return userList;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public static ArrayList<Group> getAllUsersByRound(Connection con,
			Integer round) {
		ArrayList<Group> userList = new ArrayList<Group>();
		try {
			userList = DAOUser.getAllUsersByRound(con, round);
			return userList;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public static void saveAllUsers(ArrayList<Group> grouplist, Connection con) {
		try {
			int size = grouplist.size();
			for (int i = 0; i < size; i++) {
				Group group = grouplist.get(i);
				DAOUser.saveUser(group, con);
			}
			DAOUser.fillUserList(con);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public static void updateAllUsers(ArrayList<Group> grouplist, Connection con) {
		try {
			int size = grouplist.size();
			for (int i = 0; i < size; i++) {
				Group group = grouplist.get(i);
				DAOUser.archiveUser(group, con);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public static Integer getCurrentRound(Connection con) {
		try {
			String query = "SELECT Game.CurrentRound FROM Game;";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);
			rst.next();
			Integer currentRound = rst.getInt(1);
			rst.close();
			stmt.close();
			return currentRound;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Integer getUserAmount(Connection con) {
		try {
			String query = "SELECT Game.NumberOfUsers FROM Game;";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);
			rst.next();
			Integer UserAmount = rst.getInt(1);
			rst.close();
			stmt.close();
			return UserAmount;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void changeCurrentRound(Connection con) {

		int currentRound = Game.getInstance().getCurrentRound();
		try {
			Statement stmt = con.createStatement();
			String sql = "UPDATE Game SET CurrentRound = '" + currentRound
					+ "'";
			stmt.execute(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createInitialData(Integer userAmount, Connection con) {
		String[] pass = new String[] { "luke", "imperium", "vader", "darth",
				"r2d2", "bla", "bla", "bla", "bla", "bla" };
		try {
			Statement stmt = con.createStatement();
			String query = "DELETE FROM Game;";
			stmt.execute(query);
			query = "DELETE FROM User;";
			stmt.execute(query);
			query = "DELETE FROM Company;";
			stmt.execute(query);
			query = "DELETE FROM Toaster;";
			stmt.execute(query);
			query = "DELETE FROM Stock;";
			stmt.execute(query);
			query = "INSERT INTO Game VALUES (1," + userAmount
					+ ", 'Instruction');";
			stmt.execute(query);
			for (int i = 1; i <= userAmount; i++) {

				query = "INSERT INTO User VALUES ('Gruppe " + i + "','"
						+ pass[i - 1] + "'," + i + ",'STARTED');";
				stmt.execute(query);

				for (int j = 0; j <= 1; j++) {
					// turnover, cost, profit, capital, marketShare
					query = "INSERT INTO Company VALUES (" + j + "," + i
							+ ", 0, 0, 0, 100000.00, "
							+ Type.TYPE1.getMarketVolume() / userAmount
							+ ",FALSE);";
					stmt.execute(query);
					// price, marketing, tvInvestment, newsPaperInvestment,
					// radioInvestment, research, quality, design, efficiency,
					// index, turnover, cost, profit, marketShare, type
					query = "INSERT INTO Toaster VALUES ("
							+ j
							+ ","
							+ i
							+ ",'TYPE1', 10, 3.00, 0, 0, 0, 0, 0, 0, 3.00, 0, 0, 0, 0, 0, 0,"
							+ " 9.00, 0.00, 0.00, 0.00, "
							+ Type.TYPE1.getMarketVolume() / userAmount
							+ ",0);";

					stmt.execute(query);

					query = "INSERT INTO Stock VALUES (" + i + "," + j
							+ ", 0, 0, 0, 0.00);";
					stmt.execute(query);
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean resetGame(Connection con) {
		try {
			Statement stmt = con.createStatement();
			String query = "DELETE FROM Game;";
			stmt.execute(query);
			query = "DELETE FROM User;";
			stmt.execute(query);
			query = "DELETE FROM Company;";
			stmt.execute(query);
			query = "DELETE FROM Toaster;";
			stmt.execute(query);
			query = "DELETE FROM Stock;";
			stmt.execute(query);
			stmt.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isGameStarted(Connection con) {
		boolean isGameStarted = false;
		try {
			String query = "SELECT Game.CurrentRound FROM Game;";
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(query);
			if (rst.next())
				isGameStarted = true;
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return isGameStarted;
	}
}
