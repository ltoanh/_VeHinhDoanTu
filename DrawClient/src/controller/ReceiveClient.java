package controller;

import client.Client;
import constant.StreamData;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author whiwf
 */
public class ReceiveClient extends Thread {

    private DatagramSocket client;

    ReceiveClient(DatagramSocket client) {
        this.client = client;
    }

    public void run() {
        boolean running = true;

        while (running) {
            try {
                String receivedMsg = receiveData(client);

                System.out.println("> received msg: " + receivedMsg);
                // xu ly loai du lieu nhan dc
                StreamData.Type type = StreamData.getTypeFromReceivedData(receivedMsg);
                switch (type) {
                    case LOGIN:
                        handleReceivedLogin(receivedMsg);
                        break;
                    case CHAT_ROOM:
                        handleChatMsg(receivedMsg);
                        break;
                    case JOIN_ROOM:
                        handlePlayerJoinRoom(receivedMsg);
                    case GAME_EVENT:
                        handleReceivedGameEvent(receivedMsg);
                        break;
                    case UNKNOW_TYPE:
                        break;
                }

            } catch (IOException ex) {
                Logger.getLogger(ReceiveClient.class.getName()).log(Level.SEVERE, null, ex);
                running = false;
            }
        }

        client.close();
    }

    private String receiveData(DatagramSocket client) throws IOException {
        byte[] buff = new byte[1024];
        DatagramPacket din = new DatagramPacket(buff, buff.length);

        client.receive(din);
        return new String(din.getData());
    }

    // =========================== game =============================
    //join room
    private void handlePlayerJoinRoom(String receivedMsg) {
        Client.ingame.addPlayerJoinRoom(receivedMsg);
    }

    //============================ in game ===========================
    private void handleReceivedGameEvent(String receivedMsg) {
        //GAME_EVENT;type;data1;....
        String[] data = receivedMsg.split(";");
        StreamData.Type gameEventType = StreamData.getType(data[1]);
        switch (gameEventType) {
            case DRAW_POSITION:
                int tool = Integer.parseInt(data[2]);
                int x1 = Integer.parseInt(data[3]);
                int y1 = Integer.parseInt(data[4]);
                int x2 = Integer.parseInt(data[5]);
                int y2 = Integer.parseInt(data[6]);
                Color color = Color.BLACK;
                try {
                    color = new Color(Integer.parseInt(data[7]));
                } catch (NumberFormatException e) {

                }

                Client.ingame.paintPane.addPointDraw(tool, x1, y1, x2, y2, color);
                break;
        }
    }

    //============================ chat ========================================
    private void handleChatMsg(String receivedMsg) {
        Client.ingame.addChatMessage(receivedMsg.split(";")[1]);
    }

    private void handleReceivedLogin(String receivedMsg) {
        String[] splitted = receivedMsg.split(";");
        String status = splitted[1];

        if (status.equals("Failed")) {

            String failedMsg = splitted[2];
            JOptionPane.showMessageDialog(Client.login, failedMsg, "Thất Bại", JOptionPane.ERROR_MESSAGE);

        } else if (status.equals("Success")) {

            Client.closeScene(Client.SceneName.LOGIN);
            Client.openScene(Client.SceneName.HOMEPAGE);

        }
    }

    public void login(String username, String password) {

        String data = StreamData.Type.LOGIN.name() + ";" + username + ";" + password;
        Client.clientCtr.senderClient.sendPacket(data);
    }

}
