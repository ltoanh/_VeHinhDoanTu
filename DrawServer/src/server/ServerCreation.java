package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author whiwf
 */
public class ServerCreation {

    public static ServerSocket serverSocket;
    
    private static int PORTNUMBER = 8888;
    
    public ServerCreation() {
        try {
            serverSocket = new ServerSocket(PORTNUMBER);
            System.out.println("> server created at port " + PORTNUMBER);
            
            while(true){
                Socket conn = serverSocket.accept();
                
                
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        new ServerCreation();
    }
    
}
