package toastwars.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	public static void main(String[] args) {
		DBConnection con = new DBConnection();
		Connection currentDB = con.connectToDB();
		Statement stmt = con.getStatement(currentDB);
		DAOUser user = new DAOUser();
		user.createUser("user1", "user1", "001", stmt);
		user.deleteUsers(stmt);
	}

	public DBConnection() {

	}

	public Connection connectToDB() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager
					.getConnection(
							"jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=D:/ToastWars.mdb",
							"", "");
			System.out.println("connection succesful");
			return con;
		} catch (Exception e) {
			return null;
		}

	}

	public void closeConnectionToDB(Connection con) {
		try {
			if (con.isClosed() == false)
				con.close();
			System.out.println("Database closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Statement getStatement(Connection con) {
		try {
			Statement stmt = con.createStatement();
			System.out.println(stmt.toString());
			return stmt;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
