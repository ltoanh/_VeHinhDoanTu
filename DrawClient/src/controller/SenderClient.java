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

/**
 *
 * @author whiwf
 */
public class SenderClient extends Thread{

    private DatagramSocket client;
    private InetAddress host;
    private int port;

    public SenderClient(DatagramSocket client, InetAddress host, int port) {
        this.client = client;
        this.host = host;
        this.port = port;
    }
    
    // send object
    public <T> void sendObjectPacket(T obj){
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
    public void sendPacket(String msg){
        try {
            client.send(createPacket(msg));
        } catch (IOException ex) {
            Logger.getLogger(SenderClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private DatagramPacket createPacket(String val){
        byte[] buff = val.getBytes();
        return new DatagramPacket(buff, 0, buff.length, host, port);
    }
    
    //========================= chat ==============================
    public void sendChatMessage(String msg){
        sendPacket(StreamData.Type.CHAT_ROOM.name() + ";" + msg);
        System.out.println("> msg send: " + StreamData.Type.CHAT_ROOM.name() + ";" + msg);
    }
    
    //========================= ingame ============================
    public void sendGameEvent(String msg){
        sendPacket(StreamData.Type.GAME_EVENT.name() + ";" + msg);
        System.out.println("> " + StreamData.Type.GAME_EVENT.name() + ";" + msg);
    }
    
    public void drawPoint(int tool, int x1, int y1, int x2, int y2, Color color){
        String msg = StreamData.Type.DRAW_POSITION + ";" + tool + ";" + x1 + ";" + y1 + ";" + x2 + ";" + y2 + ";" + Integer.toString(color.getRGB());
        this.sendGameEvent(msg);
    }
}
