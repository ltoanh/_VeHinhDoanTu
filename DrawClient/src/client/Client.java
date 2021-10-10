package client;

import controller.ClientCtr;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import view.scene.Homepage;
import view.scene.Ingame;
import view.scene.Lobby;
import view.scene.LobbySetting;
import view.scene.Login;
import view.scene.Signup;

/**
 *
 * @author whiwf
 */
public class Client {

    public enum SceneName {
        LOGIN,
        SIGNUP,
        HOMEPAGE,
        LOBBY,
        LOBBY_SETTING,
        INGAME,
    }

    //=================== controller ==================
    public static ClientCtr clientCtr;

    //=================== scene =======================
    public static Login login;
    public static Signup signup;

    public static Homepage homepage;

    public static Lobby lobby;
    public static LobbySetting lobbySetting;
    public static Ingame ingame;

    //=================== model ========================
    public static Account account;

    public Client() {
        try {

            initScene();
            openScene(SceneName.LOGIN);

            clientCtr = new ClientCtr(InetAddress.getByName("localhost"), 5000);
            clientCtr.execute();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }

    public void initScene() {
        login = new Login();
        signup = new Signup();

        lobby = new Lobby();
        lobbySetting = new LobbySetting();
        ingame = new Ingame();
    }

    public static void openScene(SceneName sceneName) {
        switch (sceneName) {
            case LOGIN:
                login.setVisible(true);
                break;
            case SIGNUP:
                signup.setVisible(true);
                break;

            case HOMEPAGE:
                homepage = new Homepage();
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
            case SIGNUP:
                signup.dispose();
                break;

            case HOMEPAGE:
                homepage.dispose();
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
        login.dispose();
        signup.dispose();

        homepage.dispose();

        lobby.dispose();
        lobbySetting.dispose();
        ingame.dispose();
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        new Client();
    }
}
