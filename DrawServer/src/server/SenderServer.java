package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author whiwf
 */
public class SenderServer extends Thread{
    
    private DatagramSocket server;
    private int port;

    public SenderServer(DatagramSocket server, int port) {
        this.server = server;
        this.port = port;
    }
    
    public void sendData(String msg, DatagramSocket server, InetAddress clientIP, int clientPort) throws IOException{
        byte[] buff = new byte[1024];
        buff = msg.getBytes();
        
        DatagramPacket dp = new DatagramPacket(buff, 0, buff.length, clientIP, clientPort);
        server.send(dp);
    }
}