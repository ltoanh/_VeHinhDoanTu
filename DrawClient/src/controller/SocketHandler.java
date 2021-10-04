/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.RunClient;
import client.StreamData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import shared.security.AES;
import shared.security.RSA;

/**
 *
 * @author Admin
 */
public class SocketHandler {
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;

    String loginUsername = null;
    Thread listener = null;
    AES aes;
    public String connect(String addr, int port) {
        try {
            // getting ip 
            InetAddress ip = InetAddress.getByName(addr);

            // establish the connection with server port 
            s = new Socket();
            s.connect(new InetSocketAddress(ip, port), 4000);
            System.out.println("Connected to " + ip + ":" + port + ", localport:" + s.getLocalPort());

            // obtaining input and output streams
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());

            // close old listener
            if (listener != null && listener.isAlive()) {
                listener.interrupt();
            }

            // listen to server
            //listener = new Thread(this::listen);
            listener.start();

            // security
            //initSecurityAES();

            // connect success
            return "success";

        } catch (IOException e) {

            // connect failed
            return "failed;" + e.getMessage();
        }
    }
    
    private void listen() throws IOException {
        boolean running = true;

        while (running) {
            try {
                // receive the data from server
                String received = dis.readUTF();

                // decrypt data if needed

                System.out.println("RECEIVED: " + received);

                // process received data
                StreamData.Type type = StreamData.getTypeFromData(received);

                switch (type) {
                    case LOGIN:
                        onReceiveLogin(received);
                        break;

                    case SIGNUP:
                        onReceiveSignup(received);
                }
            }catch (IOException ex) {
                Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
                running = false;
            }
        }
        
        try {
            // closing resources
            s.close();
            dis.close();
            dos.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        // alert if connect interup
        JOptionPane.showMessageDialog(null, "Mất kết nối tới server", "Lỗi", JOptionPane.ERROR_MESSAGE);
        RunClient.closeAllScene();
        RunClient.openScene(RunClient.SceneName.CONNECTSERVER);
    }

    private void onReceiveLogin(String received) {
        String[] splitted = received.split(";");
        String status = splitted[1];

        if (status.equals("failed")) {
            // hiển thị lỗi
            String failedMsg = splitted[2];
            JOptionPane.showMessageDialog(RunClient.loginScene, failedMsg, "Lỗi", JOptionPane.ERROR_MESSAGE);

        } else if (status.equals("success")) {
            // lưu email login
            this.loginUsername = splitted[2];

            // chuyển scene
            RunClient.closeScene(RunClient.SceneName.LOGIN);
        }
    }

    private void onReceiveSignup(String received) {
        
        String[] splitted = received.split(";");
        String status = splitted[1];
        // check status
        if (status.equals("failed")) {
            String failedMsg = splitted[2];
            JOptionPane.showMessageDialog(RunClient.signupScene, failedMsg, "Lỗi", JOptionPane.ERROR_MESSAGE);

        } else if (status.equals("success")) {
            JOptionPane.showMessageDialog(RunClient.signupScene, "Đăng ký thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            RunClient.closeScene(RunClient.SceneName.SIGNUP);
            RunClient.openScene(RunClient.SceneName.LOGIN);
        
        }
    }
    public void login(String username, String password) {
        // hasing password
        String passwordHash = password;

        // prepare data
        String data = StreamData.Type.LOGIN.name() + ";" + username + ";" + passwordHash;

        // send data
        sendData(data);
    }

    public void signup(String username, String password, String name, String avatar) {
        // prepare data
        String data = StreamData.Type.SIGNUP.name() + ";"
                + username + ";"
                + password + ";"
                + avatar + ";"
                + name + ";";
                

        // send data
        sendData(data);
    }
    
    public void sendData(String data) {
         try {

            String encrypted = aes.encrypt(data);
            dos.writeUTF(encrypted);

        } catch (IOException ex) {
            Logger.getLogger(SocketHandler.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
