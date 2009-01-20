package toastwars.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
	private static DBConnection instance;

	private DBConnection()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public static DBConnection getInstance()
	{
		if (instance == null)
			instance = new DBConnection();
		return instance;
	}

	public Connection connectToDB()
	{
		try
		{
			Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=D:/ToastWars.mdb", "", "");
			return con;
		} catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		return null;
	}

	public void closeConnectionToDB(Connection con)
	{
		try
		{
			if (!(con.isClosed()))
				con.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
