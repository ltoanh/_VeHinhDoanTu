package model;

import java.net.InetAddress;

/**
 *
 * @author whiwf
 */
public class Player implements java.io.Serializable{

    private static final long serialVersionUID = 6529685098267757690L;
    
    private InetAddress host;
    private int port;
    private Account account;
    private boolean isHostPlayer;
    private int score;
    
    public int getScore() {
        return score;
    }

    public Account getAccount() {
        return account;
    }

    public boolean isHostPlayer() {
        return isHostPlayer;
    }
    
    @Override
    public String toString() {
        return "Player{" + "host=" + host + ", port=" + port + ", account=" + account + ", score=" + score + '}';
    }
}
