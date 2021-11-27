package model;

import java.net.InetAddress;

/**
 *
 * @author whiwf
 */
public class Player implements java.io.Serializable{

    private static final long serialVersionUID = 6529685098267757690L;
    
    private int id;
    private InetAddress host;
    private int port;
    private Account account;
    private int score;

    public InetAddress getHost() {
        return host;
    }
    
    public int getScore() {
        return score;
    }
    
    public Account getAccount() {
        return account;
    }

    public InetAddress getHost() {
        return host;
    }
    
    
    @Override
    public String toString() {
        return "Player{" + "host=" + host + ", port=" + port + ", account=" + account + ", score=" + score + '}';
    }
}
