package controller;

import constant.StreamData;
import java.awt.Color;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ObjectModel;
import client.Client;
import model.DrawPoint;

/**
 *
 * @author whiwf
 */
public class SenderClient extends Thread {

    private DatagramSocket client;
    private InetAddress host;
    private int port;

    public SenderClient(DatagramSocket client, InetAddress host, int port) {
        this.client = client;
        this.host = host;
        this.port = port;
    }

    // send object
    public void sendObjectPacket(ObjectModel obj) {
        ObjectOutputStream oout = null;
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            oout = new ObjectOutputStream(bout);
            oout.writeObject(obj);

            byte[] buff = bout.toByteArray();

            DatagramPacket dp = new DatagramPacket(buff, 0, buff.length, host, port);
            client.send(dp);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    // send string
    public void sendPacket(String msg) {
        try {
            client.send(createPacket(msg));
        } catch (IOException ex) {
            Logger.getLogger(SenderClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private DatagramPacket createPacket(String val) {
        byte[] buff = val.getBytes();
        return new DatagramPacket(buff, 0, buff.length, host, port);
    }

    //========================= login =============================
    public void sendLoginMessage(String username, String password) {
//        sendPacket(StreamData.Type.LOGIN.name() + ";" + username + ";" + password);
//        System.out.println("> msg login: " + StreamData.Type.LOGIN.name() + ";" + username + ";" + password);
        String msg = StreamData.Type.LOGIN.name() + ";" + username.trim() + ";" + password.trim();
        ObjectModel obj = new ObjectModel(msg, null);
        sendObjectPacket(obj);
    }

    //========================= chat ==============================
    public void sendChatMessage(String msg) {
        sendPacket(StreamData.Type.CHAT_ROOM.name() + ";" + msg);
//        System.out.println("> msg send: " + StreamData.Type.CHAT_ROOM.name() + ";" + msg);
    }

    //========================= create room =======================
    public void sendCreateRoomMessage() {
        String msg = StreamData.Type.CREATE_ROOM.name();
        ObjectModel obj = new ObjectModel(msg, Client.account);
        sendObjectPacket(obj);
    }
    //start
    public void sendStartRoomMessage(){
        String msg = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.START.name() + ";" + Client.room.getId();
        ObjectModel obj = new ObjectModel(msg, null);
        sendObjectPacket(obj);
    }

    // join
    public boolean sendJoinRoomMessage(String roomID) {
        try {
            String msg = StreamData.Type.JOIN_ROOM.name() + ";" + roomID;
            ObjectModel obj = new ObjectModel(msg, Client.account);
            sendObjectPacket(obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //========================= ingame ============================
    public void drawPoint(String painter, DrawPoint drawPoint) {
        String msg = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.DRAW_POSITION.name() + ";" + Client.room.getId() + ";" + painter;
        ObjectModel obj = new ObjectModel(msg, drawPoint);
        sendObjectPacket(obj);
    }
}
