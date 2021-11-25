package server;

import com.mysql.cj.xdevapi.Client;
import constant.StreamData;
import dao.DAO;
import game.LogicGame;
import helpers.CountdownHelpers;
import helpers.RoomHelpers;
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
                    case LOGIN: {
                        handleLogin(msg);
                        break;
                    }
                    case SIGNUP:
                        handleSignUp(msg);
                        break;
                    //============= room ===========
                    // show room id
                    case SHOW_ROOMID:
                        handleShowRoomID();
                        break;
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
    //Sign Up
    private void handleSignUp(String msg) {
        String[] data = msg.trim().split(";");
        dao.insertInformation(data[1], data[2], data[3], data[4]);
        //    Account acc = new Account(data[2], data[1], data[4]);
    }

    private void handleLogin(String msg) {
        String[] data = msg.trim().split(";");
        Account acc = dao.checkAccount(data[1], data[2]);
        // send result
        ObjectModel obj = new ObjectModel(StreamData.Type.LOGIN.name(), acc);
        senderServer.sendObjectData(obj, server, receiveServer.clientIP, receiveServer.clientPort);
        if (acc != null) {
            handleShowRoomID();
        }
    }

    //======================== show room ID =========================
    //display room list at homepage
    private void handleShowRoomID() {
        String msg = StreamData.Type.SHOW_ROOMID.name();
        ArrayList<Player> listPlayers = new ArrayList<>();
        for (Room room : listRoom) {
            if (!room.isIsStart()) {
                listPlayers = room.getListPlayer();
                msg += ";" + Integer.toString(room.getId()) + "," + Integer.toString(listPlayers.size());
            }
        }
        senderServer.sendObjectData(new ObjectModel(msg, null), server, receiveServer.clientIP, receiveServer.clientPort);
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
        // them player vao phong
        Room curRoom = helpers.RoomHelpers.checkRoomByID(roomID);
        if (curRoom != null) {
            if (!curRoom.isIsStart()) {
                Player newPlayer = new Player(receiveServer.clientIP, receiveServer.clientPort, receivedAcc, 0);
                ArrayList<Player> lsPlayers = curRoom.getListPlayer();
                lsPlayers.add(newPlayer);
                curRoom.setListPlayer(lsPlayers);

                // send to all client in room
                ObjectModel obj = new ObjectModel(StreamData.Type.JOIN_ROOM.name(), curRoom);

                for (Player player : lsPlayers) {
                    senderServer.sendObjectData(obj, server, player.getHost(), player.getPort());
                }

                System.out.println("> send: " + obj.toString());
            } else {
                senderServer.sendObjectData(new ObjectModel(StreamData.Type.JOIN_ROOM.name(), null), server, receiveServer.clientIP, receiveServer.clientPort);
                handleShowRoomID();
            }
        } else {
            // nguoi dung nhap ma phong khong ton tai
            senderServer.sendObjectData(new ObjectModel(StreamData.Type.JOIN_ROOM.name(), null), server, receiveServer.clientIP, receiveServer.clientPort);
        }

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
            case GUESS_WORD:
                handleSendGuessWordResult(Integer.parseInt(msgGameEvent[2]), (Account) receivedObj.getT(), msgGameEvent[3]);
                break;
            case LEAVE_ROOM:
                handlePlayerLeaveRoom(Integer.parseInt(msgGameEvent[2]), (Account) receivedObj.getT());
                break;
        }
    }

    // start game
    private void handleSendStartGameMessage(String msg) {
        int roomID = Integer.parseInt(msg.split(";")[2]);
        Room curRoom = helpers.RoomHelpers.checkRoomByID(roomID);
        curRoom.setIsStart(true);

        new LogicGame(server, curRoom, 3).start();
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

    //guess word
    private void handleSendGuessWordResult(int roomID, Account clientAccount, String guessWord) {
        Room curRoom = helpers.RoomHelpers.checkRoomByID(roomID);
        ArrayList<Player> lsPlayers = curRoom.getListPlayer();
        //get player account
        int playerIndex = RoomHelpers.findPlayerIndexByAccount(lsPlayers, clientAccount);

        boolean isCorrect = true;

        String msgResult = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.GUESS_RESULT.name() + ";";

        if (guessWord.equals(curRoom.getWord())) {
            msgResult += "true";
            // + 50 diem doan dung
            int curScore = lsPlayers.get(playerIndex).getScore();
            lsPlayers.get(playerIndex).setScore(curScore + 50);
        } else {
            msgResult += "false";
            isCorrect = false;
        }
        ObjectModel obj = new ObjectModel(msgResult, null);
        senderServer.sendObjectData(obj, server, receiveServer.clientIP, receiveServer.clientPort);

        // send to all player
        String msgGame = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.SHOW_GUESS_RESULT.name() + ";";
        if (isCorrect) {
            // send player is correct
            msgGame += "true;" + clientAccount.getUsername() + " đã đoán đúng";
        } else {
            // send word player guess
            msgGame += "false;" + clientAccount.getUsername() + " đã đoán: " + guessWord;
        }

        ObjectModel objResult = new ObjectModel(msgGame, curRoom);
        for (Player player : lsPlayers) {
            senderServer.sendObjectData(objResult, server, player.getHost(), player.getPort());
        }
    }

    // leave room
    private void handlePlayerLeaveRoom(int roomID, Account account) {
        Room curRoom = helpers.RoomHelpers.checkRoomByID(roomID);
        ArrayList<Player> lsPlayers = curRoom.getListPlayer();
        // remove player
        int idxPlayer = RoomHelpers.findPlayerIndexByAccount(lsPlayers, account);
        lsPlayers.remove(idxPlayer);
        curRoom.setListPlayer(lsPlayers);

        // send message to all player in room
        String msgGame = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.LEAVE_ROOM.name() + ";" + account.getUsername() + " đã rời khỏi phòng";
        ObjectModel obj = new ObjectModel(msgGame, curRoom);
        for (Player player : lsPlayers) {
            senderServer.sendObjectData(obj, server, player.getHost(), player.getPort());
        }
        // check if player in room = 0
        if (lsPlayers.size() == 0) {
            int idxRoom = RoomHelpers.getRoomIndexByRoomID(roomID);
            listRoom.remove(idxRoom);
        }
    }

    //============================= chat =======================================
    private void handleSendChatMessage(String msg) {
        String[] data = msg.split(";");
        int roomID = Integer.parseInt(data[1]);
        Room curRoom = helpers.RoomHelpers.checkRoomByID(roomID);
        // send result
        String message = StreamData.Type.CHAT_ROOM.name() + ";" + data[2];
        ObjectModel obj = new ObjectModel(message, null);
        ArrayList<Player> lsPlayers = curRoom.getListPlayer();
        for (Player player : lsPlayers) {
            senderServer.sendObjectData(obj, server, player.getHost(), player.getPort());
        }
    }

}
