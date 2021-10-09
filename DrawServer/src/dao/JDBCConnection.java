/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.scene.ServerView;

/**
 *
 * @author Admin
 */
public class JDBCConnection {
    String jdbc =  "com.mysql.cj.jdbc.Driver";
    private String username, password;

    public JDBCConnection(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean ConnectDB()  {
        DAO dao = new DAO();
        dao.setupConnection();
        boolean result = false;
        try {
            PreparedStatement pst = null;
            Class.forName(jdbc);
            String sql = "SELECT * FROM user WHERE USERNAME =  ? AND PASSWORD = ?";
            pst = dao.getConn().prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}