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
    private String pass = "";

    public DAO() {
        setupConnection();
    }

    private void setupConnection() {
        try {
            String url = "jdbc:mysql://" + server + "/" + db + "?useUnicode=true&characterEncoding=UTF-8";
            
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.err.println("Loi ket noi DB: " + ex.getMessage());
        }
    }
    
    
}
