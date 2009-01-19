package toastwars.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection
{

	private Connection con;
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

	public void connectToDB()
	{
		try
		{
			this.con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=D:/ToastWars.mdb", "", "");
		} catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}

	}

	public void closeConnectionToDB()
	{
		try
		{
			if (!(this.con.isClosed()))
				this.con.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Statement getStatement()
	{
		try
		{
			Statement stmt = this.con.createStatement();
//			System.out.println(stmt.toString());
			return stmt;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
