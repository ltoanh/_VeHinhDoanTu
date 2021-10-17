package model;

import java.util.ArrayList;

/**
 *
 * @author whiwf
 */
public class Room implements java.io.Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    private int id;
    private ArrayList<Player> listPlayer;
    private ArrayList<Integer> lsPainterID;

    public int getId() {
        return id;
    }

    public void setLsPainterID(ArrayList<Integer> lsPainterID) {
        this.lsPainterID = lsPainterID;
    }

    public ArrayList<Integer> getLsPainterID() {
        return lsPainterID;
    }

    public ArrayList<Player> getListPlayer() {
        return listPlayer;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", listPlayer=" + listPlayer + ", lsPainterID=" + lsPainterID + '}';
    }

}
