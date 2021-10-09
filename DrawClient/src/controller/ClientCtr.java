package controller;

import constant.StreamData;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author whiwf
 */
public class ClientCtr {

    private InetAddress host;
    private int port;

    private DatagramSocket client;
    public static ReceiveClient receiveClient;
    public static SenderClient senderClient;
    
    public ClientCtr(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public String connect(String host, int port) {
        try {
            
            ClientCtr clientCtr = new ClientCtr(InetAddress.getByName(host), port);
            clientCtr.execute();
            
            return "success";
        } catch (UnknownHostException ex) {
            return "failed: " + ex.getMessage();
        } catch (SocketException ex) {
            return "failed: " + ex.getMessage();
        }

    }

    public void execute() throws SocketException {
        client = new DatagramSocket();
        System.out.println("client connecting...");
        
        receiveClient = new ReceiveClient(client);
        receiveClient.start();
        
        senderClient = new SenderClient(client, host, port);
        senderClient.sendPacket(StreamData.Type.JOIN_ROOM.name() + ";player1");
        senderClient.start();
    }
}