package controller;

import client.Client;
import com.sun.org.apache.xalan.internal.lib.ExsltStrings;
import constant.StreamData;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import model.Account;
import model.DrawPoint;
import model.ObjectModel;
import model.Player;
import model.Room;
import view.scene.GuessPane;

/**
 *
 * @author whiwf
 */
public class ReceiveClient extends Thread {

    private DatagramSocket client;

    private ObjectModel objReceived;
    
    private boolean isStart;//share mh

    ReceiveClient(DatagramSocket client) {
        this.client = client;
    }

    public void run() {
        boolean running = true;

        while (running) {
            objReceived = receiveObjectData(client);

            String msg = objReceived.getType();
            System.out.println("> received : " + msg + ":" + objReceived.getT());
            StreamData.Type type = StreamData.getTypeFromReceivedData(msg);

            switch (type) {
                case LOGIN:
                    handleReceivedAccountLogin((Account) objReceived.getT());
                    break;
                case SHOW_ROOMID:
                    handleReceiveRoomID(msg);
                    break;
                // GAME
                case LOBBY_ROOM:
                    handleReceivedCreatedRoom((Room) objReceived.getT());
                    break;
                case JOIN_ROOM:
                    handlePlayerJoinRoom((Room) objReceived.getT());
                    break;
                case GAME_EVENT:
                    handleReceivedGameEvent(msg);
                    break;
                case CHAT_ROOM:
                    handleChatMsg(msg);
                    break;
            }

        }

        client.close();
    }

    //receive object
    private ObjectModel receiveObjectData(DatagramSocket client) {

        try {
            byte[] buff = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buff, buff.length);

            client.receive(dp);

            ByteArrayInputStream bin = new ByteArrayInputStream(dp.getData());
            ObjectInputStream oin = new ObjectInputStream(bin);

            return (ObjectModel) oin.readObject();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * //receive string private String receiveData(DatagramSocket client) throws
     * IOException { byte[] buff = new byte[1024]; DatagramPacket din = new
     * DatagramPacket(buff, buff.length);
     *
     * client.receive(din); return new String(din.getData()); }
     */
    //============================ sign =============================
    // login
    private void handleReceivedAccountLogin(Account acc) {
        if (acc == null) {
            JOptionPane.showMessageDialog(null, "Người dùng không tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Client.account = acc;
            Client.closeScene(Client.SceneName.LOGIN);
            Client.login.clearInput();
            Client.openScene(Client.SceneName.HOMEPAGE);
        }
        Client.login.setUnloading();
    }

    //===========================show room id=============================
    private void handleReceiveRoomID(String msg) {
        String[] roomData = new String[1024];
        roomData = msg.split(";");
        Client.homepage.ClearTable();
        for (int i = 1; i < roomData.length; i++) {
            String[] data = new String[3];
            data = roomData[i].split(",");
            Client.homepage.ShowRoomID(data[0], data[1]);
        }
    }

    // =========================== game =============================
    // create room
    private void handleReceivedCreatedRoom(Room receivedRoom) {
        Client.room = receivedRoom;

        Client.closeScene(Client.SceneName.HOMEPAGE);
        Client.openScene(Client.SceneName.LOBBY);

        Client.lobby.displayRoomInf(Client.room);
        Client.lobby.displayStartButton();
        Client.lobby.addPlayerToList(Client.account.getName() + "(" + Client.account.getUsername() + ")");
    }

    //join room
    private void handlePlayerJoinRoom(Room receivedRoom) {
        if (receivedRoom != null) {
            Client.room = receivedRoom;
            Client.closeScene(Client.SceneName.HOMEPAGE);
            Client.openScene(Client.SceneName.LOBBY);
            Client.lobby.displayRoomInf(Client.room);
            Client.lobby.clearPlayersList();

            for (Player player : Client.room.getListPlayer()) {
                Account acc = player.getAccount();
                Client.lobby.addPlayerToList(acc.getName() + "(" + acc.getUsername() + ")");
            }
        } else {
            Client.homepage.showError();
        }
    }

    //============================ in game ===========================
    private void handleReceivedGameEvent(String receivedMsg) {
        //GAME_EVENT;type;data1;....
        String[] data = receivedMsg.split(";");
        StreamData.Type gameEventType = StreamData.getType(data[1]);
        switch (gameEventType) {
            case START:
                // open ingame scene
                handleStartGame((Room) objReceived.getT());
                break;
            case DRAW_POSITION:
                handleDrawPoint(data[2], (DrawPoint) objReceived.getT());
                break;
            case COUNTDOWN:
                handleReceivedCountdown(receivedMsg);
                break;
            case GUESS_RESULT:
                handleReceivedGuessResult(Boolean.valueOf(data[2]));
                break;
            case SHOW_GUESS_RESULT:
                handleReceivedPlayerGuess(Boolean.valueOf(data[2]), data[3], (Room) objReceived.getT());
                break;
            case CHANGE_TURN:
                handleReceivedChangeTurn((Room) objReceived.getT());
                break;
            case RECEIVE_WORD:
                handReceiveWord((Room) objReceived.getT());
                break;
            case TURN_RESULT:
                handleRecivedTurnResult((Room) objReceived.getT());
                break;
            case LEAVE_ROOM:
                handleReceivePlayerLeaveRoom(data[2], (Room) objReceived.getT());
                break;
            case SHARE_SCREEN:
                handleShareScreen((Player) objReceived.getT());
                break;
        }
    }

    private void handleShareScreen(Player player){
        isStart = true;
        new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (isStart) {

                                Socket soc = new Socket(player.getHost(),1007);
                                BufferedImage img = ImageIO.read(soc.getInputStream());
                                Client.ingame.getPaintPane1().display(img);
                                soc.close();

                                try (Socket soc = new Socket(player.getHost(),1007)) {
                                    BufferedImage img = ImageIO.read(soc.getInputStream());
                                    Client.ingame.getPaintPane1().display(img);
                                }

                                try {
                                    Thread.sleep(10);
                                } catch (Exception e) {
                                }
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }).start();
//        try {
//            Thread.sleep(30000);
//            t1.interrupt();
//        } catch (InterruptedException ex) {
//            // do nothing
//        }
    }
    // hien thi pane paint tool / guess
    private void displayIngamePanel() {
        String uPainter1 = Client.room.getLsPainterUsername().get(0);
        String uPainter2 = Client.room.getLsPainterUsername().get(1);
        String curUsername = Client.account.getUsername();
        if (curUsername.equals(uPainter1) || curUsername.equals(uPainter2)) {
            Client.ingame.displayPaintTool();
        } else {
            Client.ingame.displayGuessPane();
        }
    }

    //start
    private void handleStartGame(Room receivedRoom) {
        Client.room = receivedRoom;

        Client.closeScene(Client.SceneName.LOBBY);
        Client.openScene(Client.SceneName.INGAME);
        Client.ingame.displayLsPlayer(receivedRoom.getListPlayer());
        
        Client.ingame.displayCurrentPlayerInf();
        displayIngamePanel();
    }

    // draw point
    private void handleDrawPoint(String painter, DrawPoint drawPoint) {
        if (StreamData.Type.PAINT1.name().equals(painter)) {
            Client.ingame.getPaintPane1().addPointDraw(drawPoint);
        } else {
            Client.ingame.getPaintPane2().addPointDraw(drawPoint);
        }
    }

    //display word
    private void handReceiveWord(Room curRoom) {
        Client.room = curRoom;
        Client.ingame.displayWord(curRoom.getWord());
    }

    //countdown time
    private void handleReceivedCountdown(String msg) {
        String[] data = msg.split(";");
        Client.ingame.displayCountdownTime(data[2], data[3]);
        //dung share
        if(data[3].equals("0")){
            isStart = false;
        }
    }

    //result client guess
    private void handleReceivedGuessResult(boolean result) {
        // cap nhat guess pane
        GuessPane guessPane = Client.ingame.getGuessPane();
        if (result) {
            guessPane.closeGuessPane();
        } else {
            guessPane.descGuessTurn();
            if (guessPane.getGuessTurn() == 0) {
                guessPane.closeGuessPane();
            }
        }
    }

    //show player guess
    private void handleReceivedPlayerGuess(boolean result, String guessWord, Room curRoom) {
        Client.room = curRoom;

        Client.ingame.showPlayerGuessResult(guessWord);
        Client.ingame.displayLsPlayer(curRoom.getListPlayer());
    }

    // show turn result
    private void handleRecivedTurnResult(Room curRoom) {
        Client.room = curRoom;
        Client.ingame.showResultTurnDialog(curRoom.getListPlayer());
    }

    //change turn
    private void handleReceivedChangeTurn(Room receivedRoom) {
        Client.room = receivedRoom;
        // dong result dialog
        Client.ingame.closeResultTurnDialog();

        // hien thi nguoi choi hien tai
        Client.ingame.displayCurrentPainterPane();

        // hien thi lai ds nguoi choi (theo ket qua)
        Client.ingame.displayLsPlayer(receivedRoom.getListPlayer());
        displayIngamePanel();
        Client.ingame.changeTurn(receivedRoom.getListPlayer());

        // cap nhat guess pane
        GuessPane guessPane = Client.ingame.getGuessPane();
        guessPane.resetGuessPane();

    }

    //leave room
    private void handleReceivePlayerLeaveRoom(String msg, Room curRoom) {
        if (Client.ingame != null) {
            // hien thi thong bao player thoat phong
            Client.ingame.showMessagePlayerLeaveRoom(msg);
        } else{
            // hien thi lai ds nguoi choi o lobby
            Client.room = curRoom;
            Client.lobby.clearPlayersList();
            for (Player player : curRoom.getListPlayer()) {
                Account acc = player.getAccount();
                Client.lobby.addPlayerToList(acc.getName() + "(" + acc.getUsername() + ")");
            }
            Client.lobby.displayRoomInf(curRoom);
        }
    }

    //============================ chat ========================================
    private void handleChatMsg(String receivedMsg) {
        String[] data = receivedMsg.split(";");
        Client.ingame.displayMesg(data[1]);
    }

}
