package toastwars.server.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;

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
	public ArrayList getAllUsers() {
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();
        try{
            //Abfrage definieren
            String query = "SELECT * FROM User;";
    		DBConnection con = new DBConnection();
    		con.connectToDB();
    		Statement stmt = con.getStatement();
            ResultSet rst = stmt.executeQuery(query);
            ResultSetMetaData md = rst.getMetaData();
            int columns = md.getColumnCount();
            //  Zeileninhalt ermitteln
            while (rst.next()) {
                ArrayList <String>row = new ArrayList(columns);
                for (int i = 1; i <= columns; i++) {
                    row.add( rst.getString(i) );
                }
                //System.out.println(row.toString());
                
                Group obj = new Group(row.get(0), row.get(1));
                Company comp = new Company();
                obj.setCompany(comp);
                Status stat = new Status(row.get(3));
                obj.setStatus(stat);
                data.add( obj);
            }
            System.out.println(data.toString());
            rst.close();
            stmt.close();
			con.closeConnectionToDB();
			return data;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
