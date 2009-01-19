package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.user.IUser;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;
import toastwars.server.datamodel.user.UserFactory;

public class DAOUser
{

	private static ArrayList<Group> userList = new ArrayList<Group>();

	public static boolean updateUser(Group group,DBConnection con)
	{
		DAOCompany daoCompany = new DAOCompany();
		Company company = group.getCompany();
		String username = group.getUsername();
		boolean b1 = changeStatus(username, group.getStatus().name(),con);
		boolean b2 = daoCompany.updateCompany(company, con);
		fillUserList(con);
		if (b1 == true && b2 == true)
			return true;
		else
			return false;
	}

	// test

	public static boolean saveUser(Group group,DBConnection con)
	{

		DAOCompany daoCompany = new DAOCompany();
		Company company = group.getCompany();
		String username = group.getUsername();
		boolean b1 = changeStatus(username, group.getStatus().name(),con);
		boolean b2 = daoCompany.saveCompany(company, con);
		fillUserList(con);
		if (b1 == true && b2 == true)
			return true;
		else
			return false;
	}

	public void saveUser(String name, String password, Integer CompanyID,DBConnection con)
	{
		Statement stmt = con.getStatement();
		String sql = "INSERT INTO User (UserName, Password, CompanyID, Status)VALUES ('" + name + "','" + password + "','" + CompanyID
				+ "', 'started');";
		try
		{
			stmt.execute(sql);
			stmt.close();
			fillUserList(con);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteUsers(DBConnection con)
	{
		Statement stmt = con.getStatement();
		String sql = "DELETE * FROM User;";
		try
		{
			stmt.execute(sql);
			stmt.close();
			userList.clear();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<Group> getAllUsers(DBConnection con)
	{
		try
		{
			userList.clear();
			// Abfrage definieren
			String query = "SELECT * FROM User;";
			Statement stmt = con.getStatement();
			ResultSet rst = stmt.executeQuery(query);
			DAOCompany test = new DAOCompany();
			// Zeileninhalt ermitteln
			while (rst.next())
			{
				Group group = (Group) UserFactory.createUser("Group", rst.getString(1), rst.getString(2));
				int companyID = rst.getInt(3);
				group.setCompany(test.getCurrentCompany(con, companyID));
				Status stat = Status.valueOf(rst.getString(4));
				group.setStatus(stat);
				userList.add(group);
			}
			rst.close();
			stmt.close();
			return userList;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Group> getAllUsersByRound(DBConnection con, Integer round)
	{
		try
		{
			userList.clear();
			// Abfrage definieren
			String query = "SELECT * FROM User;";
			Statement stmt = con.getStatement();
			ResultSet rst = stmt.executeQuery(query);
			DAOCompany test = new DAOCompany();
			// Zeileninhalt ermitteln
			while (rst.next())
			{
				Group group = (Group) UserFactory.createUser("Group", rst.getString(1), rst.getString(2));
				int companyID = rst.getInt(3);
				group.setCompany(test.getCompanyByRound(con, companyID, round));
				Status stat = Status.valueOf(rst.getString(4));
				group.setStatus(stat);
				userList.add(group);
			}
			rst.close();
			stmt.close();
			return userList;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static void fillUserList(DBConnection con)
	{
		try
		{
			userList.clear();
			// Abfrage definieren
			String query = "SELECT * FROM User;";
			Statement stmt = con.getStatement();
			ResultSet rst = stmt.executeQuery(query);
			DAOCompany test = new DAOCompany();
			// Zeileninhalt ermitteln
			while (rst.next())
			{
				Group group = (Group) UserFactory.createUser("Group", rst.getString(1), rst.getString(2));
				int companyID = rst.getInt(3);
				group.setCompany(test.getCurrentCompany(con, companyID));
				Status stat = Status.valueOf(rst.getString(4));
				group.setStatus(stat);
				userList.add(group);
			}
			rst.close();
			stmt.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static boolean changeStatus(String username, String status,DBConnection con)
	{

		try
		{
			Statement stmt = con.getStatement();
			String sql = "UPDATE [User] SET [User].Status = '" + status + "' WHERE (((User.UserName)='" + username + "'));";

			stmt.execute(sql);
			stmt.close();
//			userList.clear();
			return true;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static IUser findUser(String name, String pass,DBConnection con) throws Exception
	{
		if (userList.size() == 0)
		{
			fillUserList(con);
		}

		for (int i = 0; i <= userList.size(); i++)
		{
			if (userList.get(i).getUsername().equals(name))
				if (userList.get(i).getPassword().equals(pass))
					return userList.get(i);
		}
		throw new Exception("No User found. Check name and password");
	}
}
