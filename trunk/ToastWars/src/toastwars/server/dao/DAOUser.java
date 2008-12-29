package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;
import toastwars.server.datamodel.user.UserFactory;

public class DAOUser {

	private ArrayList<Group> userList = new ArrayList<Group>();

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
	public ArrayList<Group> getAllUsers(DBConnection con) {
			try {
				userList.clear();
				// Abfrage definieren
				String query = "SELECT * FROM User;";
				Statement stmt = con.getStatement();
				ResultSet rst = stmt.executeQuery(query);
				DAOCompany test = new DAOCompany();
				ArrayList<Company> companyList = test.getAllCurrentCompanies(con);
				Integer size =companyList.size();
				// Zeileninhalt ermitteln
				Integer counter= 0;
				while (rst.next()) {
					Group group = (Group)UserFactory.createUser("Group",rst.getString(1), rst.getString(2));
					group.setCompany(companyList.get(counter));
					Status stat = Status.valueOf(rst.getString(4));
					group.setStatus(stat);
					userList.add(group);
					counter++;
				}
				rst.close();
				stmt.close();
				return userList;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
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
		//user.changeStatus("test","neuerStatus");

	}
}
