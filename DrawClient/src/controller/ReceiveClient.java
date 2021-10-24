package controller;

import client.Client;
import constant.StreamData;
import java.awt.Color;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    //receive string
    private String receiveData(DatagramSocket client) throws IOException {
        byte[] buff = new byte[1024];
        DatagramPacket din = new DatagramPacket(buff, buff.length);

        client.receive(din);
        return new String(din.getData());
    }
    */

    //============================ sign =============================
    // login
    private void handleReceivedAccountLogin(Account acc) {
        if (acc == null) {
            JOptionPane.showMessageDialog(null, "Nguoi dung khong ton tai", "Error", JOptionPane.ERROR_MESSAGE);
            Client.login.setUnloading();
        } else {
            Client.account = acc;
            Client.closeScene(Client.SceneName.LOGIN);
            Client.openScene(Client.SceneName.HOMEPAGE);
        }
    }

    // =========================== game =============================
    // create room
    private void handleReceivedCreatedRoom(Room receivedRoom) {
        Client.room = receivedRoom;

        Client.closeScene(Client.SceneName.HOMEPAGE);
        Client.openScene(Client.SceneName.LOBBY);

        Client.lobby.displayRoomID(Client.room.getId() + "");
        Client.lobby.displayStartButton();
        Client.lobby.addPlayerToList(Client.account.getName() + "(" + Client.account.getUsername() + ")");
    }

    //join room
    private void handlePlayerJoinRoom(Room receivedRoom) {
        Client.room = receivedRoom;

        Client.lobby.displayRoomID(Client.room.getId() + "");
        Client.lobby.clearPlayersList();
        
        for (Player player : Client.room.getListPlayer()) {
            Account acc = player.getAccount();
            Client.lobby.addPlayerToList(acc.getName() + "(" + acc.getUsername() + ")");
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
                handReceiveWord(data[2]);
                break;
        }
    }
    
    // hien thi pane paint tool / guess
    private void displayIngamePanel(){
        String uPainter1 = Client.room.getLsPainterUsername().get(0);
        String uPainter2 = Client.room.getLsPainterUsername().get(1);
        String curUsername = Client.account.getUsername();
        if(curUsername.equals(uPainter1) || curUsername.equals(uPainter2)){
            Client.ingame.displayPaintTool();
        } else {
            Client.ingame.displayGuessPane();
        }
    }

    //start
    private void handleStartGame(Room receivedRoom){
        Client.room = receivedRoom;
        
        Client.closeScene(Client.SceneName.LOBBY);
        Client.openScene(Client.SceneName.INGAME);
        Client.ingame.displayLsPlayer(receivedRoom.getListPlayer());
        
        displayIngamePanel();
    }
    
    // draw point
    private void handleDrawPoint(String painter, DrawPoint drawPoint){
        if(StreamData.Type.PAINT1.name().equals(painter)){
            Client.ingame.getPaintPane1().addPointDraw(drawPoint);
        } else {
            Client.ingame.getPaintPane2().addPointDraw(drawPoint);
        }
    }
    
    //countdown time
    private void handleReceivedCountdown(String msg){
        String[] data = msg.split(";");
        Client.ingame.displayCountdownTime(data[2], data[3]);
    }
    
    //result client guess
    private void handleReceivedGuessResult(boolean result){
        // cap nhat guess pane
        GuessPane guessPane = Client.ingame.getGuessPane();
        if(result){
            guessPane.closeGuessPane();
        } else {
            guessPane.descGuessTurn();
            if(guessPane.getGuessTurn() == 0){
                guessPane.closeGuessPane();
            }
        }
    }
    //show player guess
    private void handleReceivedPlayerGuess(boolean result, String guessWord, Room curRoom){
        Client.room = curRoom;
        
        Client.ingame.showPlayerGuessResult(guessWord);
        Client.ingame.displayLsPlayer(curRoom.getListPlayer());
    }
    
    //change turn
    private void handleReceivedChangeTurn(Room receivedRoom){
        Client.room = receivedRoom;
        
        Client.ingame.displayLsPlayer(receivedRoom.getListPlayer());
        displayIngamePanel();
    }
    //display word
    private void handReceiveWord(String word){
        Client.ingame.displayWord(word);
    }
    //============================ chat ========================================
    private void handleChatMsg(String receivedMsg) {
//        Client.ingame.addChatMessage(receivedMsg.split(";")[1]);
    }

}
