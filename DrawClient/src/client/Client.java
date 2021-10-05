package client;

import controller.ClientCtr;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.scene.Ingame;
import view.scene.Lobby;
import view.scene.LobbySetting;
import view.scene.Signup;


/**
 *
 * @author whiwf
 */
public class Client {

    public enum SceneName{
        LOBBY,
        LOBBY_SETTING,
        INGAME,
        SIGNUP,
        
    }
    
    //=================== controller ==================
    public static ClientCtr clientCtr;
    
    //=================== scene =======================
    public static Lobby lobby;
    public static LobbySetting lobbySetting;
    public static Ingame ingame;
    public static Signup signup;
            
    public Client(){
        try {
            
            initScene();
            openScene(SceneName.SIGNUP);
            
            clientCtr = new ClientCtr(InetAddress.getByName("localhost"), 5000);
            clientCtr.execute();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initScene(){
        signup = new Signup();
        lobby = new Lobby();
        lobbySetting = new LobbySetting();
        ingame = new Ingame();
    }
    
    public static void openScene(SceneName sceneName){
        switch(sceneName){
            case LOBBY:
//                lobby = new Lobby();
                lobby.setVisible(true);
                break;
            case LOBBY_SETTING:
//                lobbySetting = new LobbySetting();
                lobbySetting.setVisible(true);
                break;
            case INGAME:
//                ingame = new Ingame();
                ingame.setVisible(true);
                break;
            case SIGNUP:
                signup.setVisible(true);
                break;
            default:
                break;
        }
    }
    
    public static void closeScene(SceneName sceneName){
        switch(sceneName){
            case LOBBY:
                lobby.dispose();
                break;
            case LOBBY_SETTING:
                lobbySetting.dispose();
                break;
            case INGAME:
                ingame.dispose();
                break;
            case SIGNUP:
                signup.dispose();
                break;
            default:
                break;
        }
    }
    
    public static void closeAllScene(){
        lobby.dispose();
        lobbySetting.dispose();
        ingame.dispose();
        signup.dispose();
    }
    
    public static void main(String[] args) throws UnknownHostException, SocketException {
        new Client();
    }
}
