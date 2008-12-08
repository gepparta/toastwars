package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;
import toastwars.server.datamodel.user.UserFactory;

public class DAOUser {

	private ArrayList userList;

	public void createUser(String name, String password, Integer CompanyID) {
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
		String sql = "DELETE FROM User WHERE ID <> 1;";
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
	public ArrayList getAllUsers() {

		if (userList.isEmpty()) {
			try {
				// Abfrage definieren
				String query = "SELECT * FROM User;";
				DBConnection con = new DBConnection();
				con.connectToDB();
				Statement stmt = con.getStatement();
				ResultSet rst = stmt.executeQuery(query);
				ResultSetMetaData md = rst.getMetaData();
				int columns = md.getColumnCount();
				// Zeileninhalt ermitteln
				while (rst.next()) {
					ArrayList<String> row = new ArrayList(columns);
					for (int i = 1; i <= columns; i++) {
						row.add(rst.getString(i));
					}
					// System.out.println(row.toString());
					Group group = (Group)UserFactory.createUser("Group",row.get(0), row.get(1));
					Company comp = new Company();
					group.setCompany(comp);
					Status stat = new Status(row.get(3));
					group.setStatus(stat);
					userList.add(group);
					
				}
				System.out.println(userList.toString());
				rst.close();
				stmt.close();
				con.closeConnectionToDB();
				return userList;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return userList;
		}
	}
	public void changeStatus(String username, String status){
		DBConnection con = new DBConnection();
		con.connectToDB();
		Statement stmt = con.getStatement();
		String sql = "UPDATE [User] SET [User].Status = '" + status + "' WHERE (((User.UserName)='"+ username +"'));";
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
	public static void main(String[] args) {
		DAOUser user = new DAOUser();
		user.changeStatus("test","neuerStatus");
	}
}
