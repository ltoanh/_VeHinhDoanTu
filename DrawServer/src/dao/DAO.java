package dao;

import constant.StreamData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author whiwf
 */
public class DAO {
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private Connection conn = null;
    
    private String server = "localhost:3306";
    private String db = "scribble";
    private String user = "root";
    private String pass = "";

    public DAO() {
        setupConnection();
    }

    public Connection getConn() {
        return conn;
    }
    
    private void setupConnection() {
        try {
            String url = "jdbc:mysql://" + server + "/" + db + "?useUnicode=true&characterEncoding=UTF-8";
            
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.err.println("Loi ket noi DB: " + ex.getMessage());
        }
    }
    
    public Account checkAccount(String username, String password){
        try {
            Class.forName(JDBC_DRIVER);
            
            Statement stm = conn.createStatement();
            String sql = "select * from account where username='"+username+"' and password='"+password+"'";
            ResultSet rs = stm.executeQuery(sql);
            
//            System.out.println(sql);
            if(rs.next()){
                String name = rs.getString("name");
                String avatar = rs.getString("avatar");
                
                Account acc = new Account(username, name, avatar);
                return acc;
            }
             else {
                return null;
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public void insertInformation(String name, String username, String password, String avatar){
        try {
            Statement stmt;
            Class.forName(JDBC_DRIVER);
            stmt = conn.createStatement();
            String sql = "INSERT INTO account (name, username, password, avatar) VALUES ('" + name + "','" + username + "','"+ password+ "','" + avatar+"')";
            stmt.executeUpdate(sql);
            
            System.out.println("Insert data success");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
