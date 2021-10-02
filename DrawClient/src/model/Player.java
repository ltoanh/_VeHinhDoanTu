package model;

/**
 *
 * @author whiwf
 */
public class Player {
    private String name;
    private String avatar;
    private int totalScore = 0;

    public Player() {
    }

    public Player(String name, String avatar, int totalScore) {
        this.name = name;
        this.avatar = avatar;
        this.totalScore = totalScore;
    }
    
    
}
