package toastwars.server.dao;

import java.sql.SQLException;
import java.sql.Statement;

public class DAOUser {
	public void createUser(String name, String password, String CompanyID,
			Statement stmt) {
		String sql = "INSERT INTO User (UserName, Password, CompanyID, Status)VALUES ('"
				+ name
				+ "','"
				+ password
				+ "','"
				+ CompanyID
				+ "', 'started');";
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteUsers(Statement stmt) {
		String sql = "DELETE FROM User WHERE ID <> 1;";
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
