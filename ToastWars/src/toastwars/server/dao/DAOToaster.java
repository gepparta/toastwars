package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;
import toastwars.server.datamodel.user.UserFactory;

public class DAOToaster
{
	ArrayList<Toaster> toasterList = new ArrayList<Toaster>();

	public ArrayList getAllToaster()
	{

		if (toasterList.isEmpty())
		{
			try
			{
				// Abfrage definieren
				String query = "SELECT * FROM Toaster;";
				DBConnection con = new DBConnection();
				con.connectToDB();
				Statement stmt = con.getStatement();
				ResultSet rst = stmt.executeQuery(query);
				ResultSetMetaData md = rst.getMetaData();
				int columns = md.getColumnCount();
				// Zeileninhalt ermitteln
				while (rst.next())
				{
					// ArrayList row = new ArrayList(columns);
					// for (int i = 1; i <= columns; i++)
					// {
					// row.add(rst.getObject(i));
					// System.out.print(i + ": " + md.getColumnName(i) + ": ");
					// System.out.println(row.get(i - 1).toString());
					// }
					// System.out.println(row.toString());
					Toaster toaster = new Toaster(rst.getInt(2), rst.getDouble(4),
							rst.getDouble(5), rst.getDouble(6), rst.getDouble(7), rst.getDouble(8),
							rst.getDouble(9), rst.getDouble(10), rst.getInt(11), Type.values()[rst
									.getInt(12)]);
					System.out.println();
					System.out.println("test");
					// toasterList.add(toaster);

				}
				System.out.println(toasterList.toString());
				rst.close();
				stmt.close();
				con.closeConnectionToDB();
				return toasterList;
			} catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		} else
		{
			return toasterList;
		}
	}

	public static void main(String[] args)
	{
		DAOToaster toaster = new DAOToaster();
		// user.changeStatus("test","neuerStatus");
		toaster.getAllToaster();
	}

}
