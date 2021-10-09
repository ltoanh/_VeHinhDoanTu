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
import view.scene.ConnectServer;
import view.scene.Homepage;
import view.scene.Login;
import view.scene.Signup;

/**
 *
 * @author whiwf
 */

//test Merge nhé
public class Client {

    public enum SceneName {
        CONNECTSERVER,
        LOGIN,
        HOMEPAGE,
        SIGNUP,
        LOBBY,
        LOBBY_SETTING,
        INGAME,
    }

    //=================== controller ==================
    public static ClientCtr clientCtr;

    //=================== scene =======================
    public static Lobby lobby;
    public static LobbySetting lobbySetting;
    public static Ingame ingame;
    public static ConnectServer connectServerScene;
    public static Login login;
    public static Homepage homepage;

    public Client() {
        try {

            initScene();
            //openScene(SceneName.INGAME);
            openScene(SceneName.LOGIN);
            //openScene(SceneName.HOMEPAGE);

            clientCtr = new ClientCtr(InetAddress.getByName("localhost"), 5000);
            clientCtr.execute();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initScene() {
        lobby = new Lobby();
        lobbySetting = new LobbySetting();
        ingame = new Ingame();
        login = new Login();
        homepage=new Homepage();
    }

    public static void openScene(SceneName sceneName) {
        switch (sceneName) {
            case CONNECTSERVER:
                connectServerScene.dispose();
                break;
            case LOGIN:
                login.setVisible(true);
                break;
            case HOMEPAGE:
                homepage.setVisible(true);
                break;
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
            default:
                break;
        }
    }

    public static void closeScene(SceneName sceneName) {
        switch (sceneName) {
            case LOGIN:
                login.dispose();
                break;
            case LOBBY:
                lobby.dispose();
                break;
            case LOBBY_SETTING:
                lobbySetting.dispose();
                break;
            case INGAME:
                ingame.dispose();
                break;
            default:
                break;
        }
    }

    public static void closeAllScene() {
        lobby.dispose();
        lobbySetting.dispose();
        ingame.dispose();
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        new Client();
    }
}