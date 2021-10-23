package helpers;

import constant.StreamData;
import java.net.DatagramSocket;
import java.util.Timer;
import java.util.TimerTask;
import model.ObjectModel;
import model.Player;
import model.Room;
import static server.Server.senderServer;

/**
 *
 * @author whiwf
 */
public class CountdownHelpers extends Thread {

    private int time;
    private int turn;

    private DatagramSocket server;
    private Room room;
    private int roomID;

    public CountdownHelpers(int time, int turn, DatagramSocket server, Room room) {
        this.time = time;
        this.turn = turn;
        this.server = server;
        this.room = room;
        this.roomID = room.getId();
    }

    @Override
    public void run() {
        while (time >= 0) {
            //send to all player in room
            String senderTime = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.COUNTDOWN.name()
                    + ";" + this.turn + ";" + this.time;
            ObjectModel obj = new ObjectModel(senderTime, null);
            for (Player player : room.getListPlayer()) {
                senderServer.sendObjectData(obj, server, player.getHost(), player.getPort());
            }

            time--;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
    }

    public int getTime() {
        return time;
    }

    public int getRoomID() {
        return roomID;
    }
}
