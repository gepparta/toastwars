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

public class DAOUser {

	private ArrayList<IUser> userList = new ArrayList<IUser>();

	// test
	public void saveUser(Group group, DBConnection con) {
		DAOCompany daoCompany = new DAOCompany();
		Company company = group.getCompany();
		String username = group.getUsername();
		changeStatus(username, "COMPLETED");
		daoCompany.saveCompany(company, con);
	}

	public void saveUser(String name, String password, Integer CompanyID) {
		DBConnection con = new DBConnection();
		con.connectToDB();
		Statement stmt = con.getStatement();
		String sql = "INSERT INTO User (UserName, Password, CompanyID, Status)VALUES ('"
				+ name
				+ "','"
				+ password
				+ "','"
				+ CompanyID
				+ "', 'started');";
		try {
			stmt.execute(sql);
			stmt.close();
			con.closeConnectionToDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteUsers() {
		DBConnection con = new DBConnection();
		con.connectToDB();
		Statement stmt = con.getStatement();
		String sql = "DELETE * FROM User;";
		try {
			stmt.execute(sql);
			stmt.close();
			con.closeConnectionToDB();
			userList.clear();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<IUser> getAllUsers(DBConnection con) {
		try {
			userList.clear();
			// Abfrage definieren
			String query = "SELECT * FROM User;";
			Statement stmt = con.getStatement();
			ResultSet rst = stmt.executeQuery(query);
			DAOCompany test = new DAOCompany();
			// Zeileninhalt ermitteln
			while (rst.next()) {
				Group group = (Group) UserFactory.createUser("Group", rst
						.getString(1), rst.getString(2));
				int companyID = rst.getInt(3);
				group.setCompany(test.getCurrentCompany(con, companyID));
				Status stat = Status.valueOf(rst.getString(4));
				group.setStatus(stat);
				userList.add(group);
			}
			rst.close();
			stmt.close();
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void changeStatus(String username, String status) {

		try {
			DBConnection con = new DBConnection();
			con.connectToDB();
			Statement stmt = con.getStatement();
			String sql = "UPDATE [User] SET [User].Status = '" + status
					+ "' WHERE (((User.UserName)='" + username + "'));";

			stmt.execute(sql);
			stmt.close();
			con.closeConnectionToDB();
			userList.clear();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public IUser findUser(String name, String pass) throws Exception
	{
		DBConnection con = new DBConnection();
		con.connectToDB();
		if (userList.size() == 0)
		{
			userList = this.getAllUsers(con);
			con.closeConnectionToDB();		
		} 
		
		for(int i=0;i<=userList.size();i++)
		{
			if(userList.get(i).getUsername().equals(name))
				if(userList.get(i).getPassword().equals(pass))
			return 	userList.get(i);	
		}
		throw new Exception("No User found. Check spelling of name and password");
	}
}
