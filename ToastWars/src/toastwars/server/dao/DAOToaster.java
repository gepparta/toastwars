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

public class DAOToaster{
	ArrayList<Toaster> toasterList = new ArrayList<Toaster>();
	public ArrayList getAllUsers() {

		if (toasterList.isEmpty()) {
			try {
				// Abfrage definieren
				String query = "SELECT * FROM Toaster;";
				DBConnection con = new DBConnection();
				con.connectToDB();
				Statement stmt = con.getStatement();
				ResultSet rst = stmt.executeQuery(query);
				ResultSetMetaData md = rst.getMetaData();
				int columns = md.getColumnCount();
				// Zeileninhalt ermitteln
				while (rst.next()) {
					ArrayList row = new ArrayList(columns);
					for (int i = 1; i <= columns; i++) {
						row.add(rst.getObject(i));
					}
					// System.out.println(row.toString());
					Toaster toaster = new Toaster(rst.getInt(0),rst.getDouble(1),rst.getDouble(2),rst.getDouble(3),rst.getDouble(4),rst.getDouble(5),rst.getDouble(6),rst.getDouble(7),rst.getInt(8),Type.valueof(rst.getString(9)));
					Company comp = new Company();
					group.setCompany(comp);
					Status stat = Status.valueOf(row.get(3));
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
	
}
