package server;

import constant.StreamData;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author whiwf
 */
public class Server {

    private int port;

    public static SenderServer senderServer;
    public static ReceiveServer receiveServer;

    public static ArrayList<DatagramPacket> listSK;

    public Server(int port) {
        this.port = port;
    }

    private void execute() {
        try {
            DatagramSocket server = new DatagramSocket(port);
            System.out.println("server created");

            senderServer = new SenderServer(server, port);
            receiveServer = new ReceiveServer();
            receiveServer.start();

            listSK = new ArrayList<>();

            while (true) {
                String msg = receiveServer.receiveData(server);
                System.out.println("> received: " + msg);
                for (DatagramPacket item : listSK) {
                    if (!(item.getAddress().equals(receiveServer.clientIP) && item.getPort() == receiveServer.clientPort)) {
                        senderServer.sendData(msg, server, item.getAddress(), item.getPort());
                    }
                }
            }

        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new Server(5000).execute();
    }

    //========================= game event =====================================
    //draw point
}
