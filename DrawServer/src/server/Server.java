package server;

import com.mysql.cj.xdevapi.Client;
import constant.StreamData;
import dao.DAO;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.ObjectModel;
import model.Player;
import model.Room;

/**
 *
 * @author whiwf
 */
public class Server {

    private int port;
    //dao
    private DAO dao;

    // server
    private DatagramSocket server;
    public static SenderServer senderServer;
    public static ReceiveServer receiveServer;
    
    private ObjectModel receivedObj;

    public static ArrayList<Room> listRoom;

    public Server(int port) {
        this.port = port;
    }

    private void execute() {
        try {
            // mysql
            dao = new DAO();

            // server
            server = new DatagramSocket(port);
            System.out.println("server created");

            senderServer = new SenderServer();
            receiveServer = new ReceiveServer();
            receiveServer.start();
            senderServer.start();

            listRoom = new ArrayList<>();

            while (true) {
//                String msg = receiveServer.receiveData(server);
//                System.out.println("> received: " + msg);
                receivedObj = receiveServer.receiveObjectData(server);
                String msg = receivedObj.getType();
                System.out.println("> received: " + msg);
                StreamData.Type type = StreamData.getTypeFromReceivedData(msg);

                switch (type) {
                    case LOGIN:
                        handleLogin(msg);
                        break;
                    case SIGNUP:
                        handleSignUp(msg);
                        break;
                    //============= room ===========
                    // create room
                    case CREATE_ROOM:
                        handleCreateRoom();
                        break;
                    // join room    
                    case JOIN_ROOM:
                        handlePlayerJoinRoom(msg, (Account) receivedObj.getT());
                        break;
                    
                    //============ game =============
                    case CHAT_ROOM:
                        handleSendChatMessage(msg);
                        break;
                    case GAME_EVENT:
                        handleSendGameEvent(msg);
                        break;
                }

            }

        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new Server(5000).execute();
    }

    //========================= sign =====================================
    //Sign Up
    private void handleSignUp(String msg){
        String[] data = msg.trim().split(";");
        dao.insertInformation(data[1], data[2], data[3], data[4]);
    //    Account acc = new Account(data[2], data[1], data[4]);
    }
    //login
    private void handleLogin(String msg) {
        String[] data = msg.trim().split(";");
        Account acc = dao.checkAccount(data[1], data[2]);
        // send result
        ObjectModel obj = new ObjectModel(StreamData.Type.LOGIN.name(), acc);
        senderServer.sendObjectData(obj, server, receiveServer.clientIP, receiveServer.clientPort);
    }

    //========================= game =====================================
    //create room
    private void handleCreateRoom(){
        Player player = new Player(receiveServer.clientIP, receiveServer.clientPort, (Account) receivedObj.getT(), 0);
        ArrayList<Player> listPlayer = new ArrayList<>();
        listPlayer.add(player);
        
        // them phong
        Room room = new Room(listPlayer);
        listRoom.add(room);
        
        ObjectModel obj = new ObjectModel(StreamData.Type.LOBBY_ROOM.name(), room);
        
        senderServer.sendObjectData(obj, server, receiveServer.clientIP, receiveServer.clientPort);
        System.out.println("> send: " + obj.toString());
    }
    
    // join room
    private void handlePlayerJoinRoom(String msg, Account receivedRoom) {
        int roomID = Integer.parseInt(msg.split(";")[1]);
        Player newPlayer = new Player(receiveServer.clientIP, receiveServer.clientPort, receivedRoom, 0);
        
        // them player vao phong
        Room curRoom = helpers.RoomHelpers.checkRoomByID(roomID); // chua check exception
        ArrayList<Player> lsPlayers = curRoom.getListPlayer();
        lsPlayers.add(newPlayer);
        curRoom.setListPlayer(lsPlayers);
        
        // send to all client in room
        ObjectModel obj = new ObjectModel(StreamData.Type.JOIN_ROOM.name(), curRoom);
        
        for(Player player : lsPlayers){
            senderServer.sendObjectData(obj, server, player.getHost(), player.getPort());
        }
        
        System.out.println("> send: " + obj.toString());
    }

    // game event
    private void handleSendGameEvent(String msg) {
//        for (DatagramPacket item : listSK) {
//            if (!(item.getAddress().equals(receiveServer.clientIP) && item.getPort() == receiveServer.clientPort)) {
//                try {
//                    senderServer.sendData(msg, server, item.getAddress(), item.getPort());
//                } catch (IOException ex) {
//                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
    }

    //============================= chat =======================================
    private void handleSendChatMessage(String msg) {
//        for (DatagramPacket item : listSK) {
//            if (!(item.getAddress().equals(receiveServer.clientIP) && item.getPort() == receiveServer.clientPort)) {
//                try {
//                    senderServer.sendData(msg, server, item.getAddress(), item.getPort());
//                } catch (IOException ex) {
//                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
    }
}
