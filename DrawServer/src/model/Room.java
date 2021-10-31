package model;

import game.LogicGame;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author whiwf
 */
public class Room implements java.io.Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    
    private static int roomID = 1000;
    private int id;
    private ArrayList<Player> listPlayer;
    private ArrayList<String> lsPainterUsername;
    
    private String word;

    public Room(ArrayList<Player> listPlayer) {
        this.roomID++;
        this.id = this.roomID;
        this.listPlayer = listPlayer;
    }

    public void setListPlayer(ArrayList<Player> listPlayer) {
        this.listPlayer = listPlayer;
    }

    public void setLsPainterUsername(ArrayList<String> lsPainterUsername) {
        this.lsPainterUsername = lsPainterUsername;
    }
    
    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    
    public ArrayList<Player> getListPlayer() {
        return listPlayer;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", listPlayer=" + listPlayer + ", lsPainterUsername=" + lsPainterUsername + '}';
    }

}
