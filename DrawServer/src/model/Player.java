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

    //create room

    public Player(InetAddress host, int port, Account account, boolean isHost, int score) {
        this.host = host;
        this.port = port;
        this.account = account;
        this.isHostPlayer = isHost;
        this.score = score;
    }
    
    public Player(InetAddress host, int port, Account account, int score) {
        this.host = host;
        this.port = port;
        this.account = account;
        this.isHostPlayer = false;
        this.score = score;
    }

    public Account getAccount() {
        return account;
    }

    public InetAddress getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getScore() {
        return score;
    }
    
    
}
