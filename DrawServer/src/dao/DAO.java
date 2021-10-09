package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author whiwf
 */
public class DAO {
    private Connection conn = null;
    
    private String server = "localhost:3306";
    private String db = "scribble";
    private String user = "root";
    private String pass = "phuong@nh04";

    public DAO() {
        setupConnection();
    }
    
     public Connection getConn() {
        return conn;
    }

    public void setupConnection() {
        try {
            String url = "jdbc:mysql://" + server + "/" + db + "?useUnicode=true&characterEncoding=UTF-8";
            
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.err.println("Loi ket noi DB: " + ex.getMessage());
        }
    }
    
    
}
