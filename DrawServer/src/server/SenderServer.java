package server;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import model.ObjectModel;

/**
 *
 * @author whiwf
 */
public class SenderServer extends Thread{
    
//    private DatagramSocket server;
//    private int port;
//
//    public SenderServer(DatagramSocket server, int port) {
//        this.server = server;
//        this.port = port;
//    }
    public  SenderServer() {

    }
    
    // send object
    public void sendObjectData(ObjectModel obj, DatagramSocket server, InetAddress clientIP, int clientPort){
        ObjectOutputStream oout = null;
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            oout = new ObjectOutputStream(bout);
            oout.writeObject(obj);
            
            byte[] buff = bout.toByteArray();
            DatagramPacket dp = new DatagramPacket(buff, 0, buff.length, clientIP, clientPort);
            
            server.send(dp);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    };
    
    // send string
    public void sendData(String msg, DatagramSocket server, InetAddress clientIP, int clientPort) throws IOException{
        byte[] buff = new byte[1024];
        buff = msg.getBytes();
        
        DatagramPacket dp = new DatagramPacket(buff, 0, buff.length, clientIP, clientPort);
        server.send(dp);
    }
}
