package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;

public class DAOGame
{

	public static ArrayList<IUser> getAllUsers()
	{
		ArrayList<IUser> userList = new ArrayList<IUser>();
		try
		{
			DBConnection con = new DBConnection();
			con.connectToDB();
			userList = DAOUser.getAllUsers(con);
			con.closeConnectionToDB();
			return userList;
		} catch (RuntimeException e)
		{
			e.printStackTrace();
		}
		return userList;
	}

	public static void saveAllUsers(ArrayList<IUser> userList)
	{
		try
		{
			DBConnection con = new DBConnection();
			con.connectToDB();
			int size = userList.size();
			for (int i = 0; i < size; i++)
			{
				Group group = (Group) userList.get(i);
				DAOUser.saveUser(group, con);
			}
			changeCurrentRound(con);
			con.closeConnectionToDB();
		} catch (RuntimeException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Integer getCurrentRound()
	{
		try
		{
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

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static Integer getUserAmount()
	{
		try
		{
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
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void changeCurrentRound(DBConnection con)
	{
		int currentRound = 1;
		// Game.getInstance().getCurrentRound();
		Statement stmt = con.getStatement();
		String sql = "UPDATE [Game] SET [Game].CurrentRound = '" + currentRound + "'";
		try
		{
			stmt.execute(sql);
			stmt.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createInitialData(Integer userAmount)
	{
		try
		{
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
			query = "INSERT INTO Game VALUES (0," + userAmount + ", 'Instruction');";
			stmt.execute(query);
			for (int i = 1; i <= userAmount; i++)
			{
				query = "INSERT INTO User VALUES ('user" + i + "','password" + i + "'," + i + ",'STARTED');";
				stmt.execute(query);
				// turnover, cost, profit, capital, marketShare
				query = "INSERT INTO Company VALUES (0," + i + ", 0, 0, 0, 100000.00, "+10000/userAmount+");";
				stmt.execute(query);
				// price, marketing, tvInvestment, newsPaperInvestment, radioInvestment, research, quality, design, efficiency, index, turnover, cost, profit, marketShare, type
				query = "INSERT INTO Toaster VALUES (0," + i + "," + i + ",10,3.00,20000," + "5000, 10000, 3.00,5000, 5000, 5000, 9.00, 100000.00, 43000.00, 1000.00, 10000,'TYPE1');";

				stmt.execute(query);
			}
			stmt.close();
			con.closeConnectionToDB();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean resetGame()
	{
		try
		{
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
			return true;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isGameStarted()
	{
		try
		{
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
			if (currentRound > 0)
			{
				return true;
			} else
			{
				return false;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	// public static void main(String[] args) {
	//
	// resetGame();
	// createInitialData(5);
	// ArrayList<Group> userList = getAllUsers();
	// saveAllUsers(userList);
	// }
}
