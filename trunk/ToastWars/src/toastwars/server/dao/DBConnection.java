package toastwars.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection	instance;

	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DBConnection getInstance() {
		if (instance == null)
			instance = new DBConnection();
		return instance;
	}

	public static Connection connectToDB() {
		try {
			Connection con = DriverManager
					.getConnection(
						"jdbc:mysql://localhost/ToastWars?user=root&password=toastwars");
			return con;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;
	}

	public static void closeConnectionToDB(Connection con) {
		try {
			if (!(con.isClosed()))
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
