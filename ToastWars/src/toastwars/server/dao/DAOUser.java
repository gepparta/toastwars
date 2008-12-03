package toastwars.server.dao;

public class DAOUser {
public void createUser(String name, String password){
String sql = "INSERT INTO tbl_user (name, password) VALUES ("+name+", "+password+");";
}
public void deleteUsers(){
String sql= "DELETE FROM tbl_users WHERE ID <> 1;";
}
}