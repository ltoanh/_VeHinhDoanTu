package server;

import constant.StreamData;
import dao.CreateAccount;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author whiwf
 */
public class Server {

    private int port;

    private DatagramSocket server;
    public static SenderServer senderServer;
    public static ReceiveServer receiveServer;

    public static ArrayList<DatagramPacket> listSK;

    public Server(int port) {
        this.port = port;
    }

    private void execute() {
        try {
            server = new DatagramSocket(port);
            System.out.println("server created");

            senderServer = new SenderServer(server, port);
            receiveServer = new ReceiveServer();
            receiveServer.start();

            listSK = new ArrayList<>();

            while (true) {
                String msg = receiveServer.receiveData(server);
                System.out.println("> received: " + msg);

                StreamData.Type type = StreamData.getTypeFromReceivedData(msg);

                switch (type) {
                    case JOIN_ROOM:
                        handlePlayerJoinRoom(msg);
                        break;
                    case CHAT_ROOM:
                        handleSendChatMessage(msg);
                        break;
                    case GAME_EVENT:
                        handleSendGameEvent(msg);
                        break;
                    case SIGNUP:
                        handleReceiveInformation(msg);
                        break;
                }

            }

        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new Server(5000).execute();
    }

    
    //========================= game =====================================
    
    // join room
    private void handlePlayerJoinRoom(String msg) {
        for (DatagramPacket item : listSK) {
            if (!(item.getAddress().equals(receiveServer.clientIP) && item.getPort() == receiveServer.clientPort)) {
                try {
                    senderServer.sendData(msg, server, item.getAddress(), item.getPort());
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    // game event
    private void handleSendGameEvent(String msg) {
        for (DatagramPacket item : listSK) {
            if (!(item.getAddress().equals(receiveServer.clientIP) && item.getPort() == receiveServer.clientPort)) {
                try {
                    senderServer.sendData(msg, server, item.getAddress(), item.getPort());
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //============================= chat =======================================
    private void handleSendChatMessage(String msg) {
        for (DatagramPacket item : listSK) {
            if (!(item.getAddress().equals(receiveServer.clientIP) && item.getPort() == receiveServer.clientPort)) {
                try {
                    senderServer.sendData(msg, server, item.getAddress(), item.getPort());
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    //============================Dang ky=======================================
    private void handleReceiveInformation(String msg) 
    {
//        String 1 name, String 2 username, string 3 password;
        String[] infor = msg.split(";");
        CreateAccount create = new CreateAccount(infor[1], infor[2], infor[3]);
        create.insertInformation();
    }
}