/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dao1.JDBCConnection;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Server {

    public static void main(String[] args) throws SocketException, IOException, SQLException {
        ServerSocket server = new ServerSocket(1234);
        System.out.println("server sẵn sàng");
        Socket client = server.accept();
        Scanner inFromClient = new Scanner(client.getInputStream());
        PrintStream outToClient = new PrintStream(client.getOutputStream());
        String username = inFromClient.nextLine();
        String password = inFromClient.nextLine();
        PreparedStatement pst = null;
        Connection conn = null;
        try {
            conn = JDBCConnection.getJDBCConnection();
            String sql = "SELECT * FROM user WHERE USERNAME =  ? AND PASSWORD = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Login Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Sai Username hoặc Password!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
