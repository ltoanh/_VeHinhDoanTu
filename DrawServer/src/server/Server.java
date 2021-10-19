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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.DrawPoint;
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
                receivedObj = receiveServer.receiveObjectData(server);

                String msg = receivedObj.getType();
                System.out.println("> received: " + msg);
                StreamData.Type type = StreamData.getTypeFromReceivedData(msg);

                switch (type) {
                    case LOGIN:
                        handleLogin(msg);
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
    private void handleCreateRoom() {
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
    private void handlePlayerJoinRoom(String msg, Account receivedAcc) {
        int roomID = Integer.parseInt(msg.split(";")[1]);
        Player newPlayer = new Player(receiveServer.clientIP, receiveServer.clientPort, receivedAcc, 0);

        // them player vao phong
        Room curRoom = helpers.RoomHelpers.checkRoomByID(roomID); // chua check exception
        ArrayList<Player> lsPlayers = curRoom.getListPlayer();
        lsPlayers.add(newPlayer);
        curRoom.setListPlayer(lsPlayers);

        // send to all client in room
        ObjectModel obj = new ObjectModel(StreamData.Type.JOIN_ROOM.name(), curRoom);

        for (Player player : lsPlayers) {
            senderServer.sendObjectData(obj, server, player.getHost(), player.getPort());
        }

        System.out.println("> send: " + obj.toString());
    }

    //===========================game event=====================================
    private void handleSendGameEvent(String msg) {
        String[] msgGameEvent = msg.split(";");

        StreamData.Type type = StreamData.getType(msgGameEvent[1]);

        switch (type) {
            case START:
                handleSendStartGameMessage(msg);
                break;
            case DRAW_POSITION:
                handleSendDrawPoint(msgGameEvent[2], msgGameEvent[3], (DrawPoint) receivedObj.getT());
                break;
        }
    }

    // start game
    private void handleSendStartGameMessage(String msg) {
        int roomID = Integer.parseInt(msg.split(";")[2]);
        Room curRoom = helpers.RoomHelpers.checkRoomByID(roomID);

        // chon 2 nguoi ve
        ArrayList<String> lsPainterID = helpers.RoomHelpers.chooseLsPlayerToDraw(curRoom.getListPlayer());
        curRoom.setLsPainterUsername(lsPainterID);

        // send to all player
        ArrayList<Player> lsPlayers = curRoom.getListPlayer();
        String msgStart = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.START;
        ObjectModel obj = new ObjectModel(msgStart, curRoom);
        for (Player player : lsPlayers) {
            senderServer.sendObjectData(obj, server, player.getHost(), player.getPort());
        }
    }

    // draw point
    private void handleSendDrawPoint(String roomIDStr, String painter, DrawPoint drawPoint) {
        int roomID = Integer.parseInt(roomIDStr);
        Room curRoom = helpers.RoomHelpers.checkRoomByID(roomID);

        // send point to all client except painter
        String msgDrawPoint = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.DRAW_POSITION.name() + ";" + painter;
        ObjectModel obj = new ObjectModel(msgDrawPoint, drawPoint);

        ArrayList<Player> lsPlayers = curRoom.getListPlayer();
        for (Player player : lsPlayers) {
            if (player.getHost().toString().equals(receiveServer.clientIP.toString()) && player.getPort() == receiveServer.clientPort) {
                continue;
            }
            senderServer.sendObjectData(obj, server, player.getHost(), player.getPort());
        }
    }

    // change turn
    private void changeTurnPlayerDraw(int roomID) {
        // tim kiem phong
        Room curRoom = helpers.RoomHelpers.checkRoomByID(roomID);
        
        // get 2 painter
        ArrayList<String> lsPainterID = helpers.RoomHelpers.chooseLsPlayerToDraw(curRoom.getListPlayer());
        curRoom.setLsPainterUsername(lsPainterID);
        
        // send coundown to all player in room
    }

    //============================= chat =======================================
    private void handleSendChatMessage(String msg) {

    }
}
