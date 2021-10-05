/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import controller.SocketHandler;

import view.scene.ConnectServer;
import view.scene.Login;
import view.scene.Signup;
/**
 *
 * @author Admin
 */
public class RunClient {

    public enum SceneName {
        CONNECTSERVER,
        LOGIN,
        SIGNUP,
    }

    // scenes
    public static ConnectServer connectServerScene;
    public static Login loginScene;
    public static Signup signupScene;
   

    // controller 
    public static SocketHandler socketHandler;

    public RunClient() {
        socketHandler = new SocketHandler();
        initScene();
        openScene(SceneName.CONNECTSERVER);
    }

    public void initScene() {
        connectServerScene = new ConnectServer();
        loginScene = new Login();
        signupScene = new Signup();
        
    }

    public static void openScene(SceneName sceneName) {
        if (null != sceneName) {
            switch (sceneName) {
                case CONNECTSERVER:
                    // tạo lại scene để tạo lại state mặc định
                    // nếu chỉ setVisible(true) thì cũng open được scene cũ, nhưng state thì không phải mặc định
                    connectServerScene = new ConnectServer();
                    connectServerScene.setVisible(true);
                    break;
                case LOGIN:
                    loginScene = new Login();
                    loginScene.setVisible(true);
                    break;
                case SIGNUP:
                    signupScene = new Signup();
                    signupScene.setVisible(true);
                    break;
                
                default:
                    break;
            }
        }
    }

    public static void closeScene(SceneName sceneName) {
        if (null != sceneName) {
            switch (sceneName) {
                case CONNECTSERVER:
                    connectServerScene.dispose();
                    break;
                case LOGIN:
                    loginScene.dispose();
                    break;
                case SIGNUP:
                    signupScene.dispose();
                    break;
                
                default:
                    break;
            }
        }
    }

    public static void closeAllScene() {
        connectServerScene.dispose();
        loginScene.dispose();
        signupScene.dispose();
        
    }

    public static void main(String[] args) {
        LookAndFeel.setNimbusLookAndFeel();
        new RunClient();
    }
    
}
