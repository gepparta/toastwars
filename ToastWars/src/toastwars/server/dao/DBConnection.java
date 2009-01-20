package toastwars.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static boolean	firstCall	= true;
	private Connection		con;

	public DBConnection() {
	}

	public void connectToDB() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager
					.getConnection(
							"jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=C:/db/ToastWars.mdb",
							"", "");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void closeConnectionToDB() {
		try {
			if (!(con.isClosed()))
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Statement getStatement() {
		try {
			return con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
