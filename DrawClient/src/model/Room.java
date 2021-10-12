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

    public int getId() {
        return id;
    }

    public ArrayList<Player> getListPlayer() {
        return listPlayer;
    }

    @Override
    public String toString() {
        return "Room{" + "roomID=" + id + ", listPlayer=" + listPlayer + '}';
    }

}
