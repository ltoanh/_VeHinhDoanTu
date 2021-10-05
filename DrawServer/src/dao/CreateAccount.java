/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class CreateAccount {
    String JDBC_DRIVER =  "com.mysql.cj.jdbc.Driver"; //xóa cj đi nếu phiên bản mysql-connector-java chưa có driver này nhé
    private String name;
    private String username;
    private String password;

    public CreateAccount(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
    
    
    public void insertInformation() {
        DAO dao = new DAO();
        dao.setupConnection();
        try {
            Statement stmt;
            Class.forName(JDBC_DRIVER);
            stmt = dao.getConn().createStatement();
            String sql = "INSERT INTO account (name, username, password) VALUES ('" + name + "','" + username + "','"+ password+ "')";
            stmt.executeUpdate(sql);
            
            System.out.println("Insert data success");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
