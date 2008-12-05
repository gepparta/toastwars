package toastwars.server.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUser {
	public void createUser(String name, String password, String CompanyID) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
