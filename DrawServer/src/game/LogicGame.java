package game;

import constant.StreamData;
import helpers.CountdownHelpers;
import java.net.DatagramSocket;
import java.util.ArrayList;
import model.ObjectModel;
import model.Player;
import model.Room;
import static server.Server.senderServer;

/**
 *
 * @author whiwf
 */
public class LogicGame implements Runnable {

    private DatagramSocket server;

    private int roomID;
    private Room room;
    private int turn;

    public LogicGame(DatagramSocket server, Room room, int turn) {
        this.server = server;
        this.room = room;
        this.roomID = room.getId();
        this.turn = turn;
    }

    @Override
    public void run() {
        ArrayList<Player> lsPlayers = room.getListPlayer();

        for (int i = 1; i <= turn; ++i) {
            // get 2 painter
            ArrayList<String> lsPainterID = helpers.RoomHelpers.chooseLsPlayerToDraw(room.getListPlayer());
            room.setLsPainterUsername(lsPainterID);
            System.out.println(lsPainterID);

            //note: send word!
            //...code...
            //countdown
            CountdownHelpers countdown = new CountdownHelpers(10, i, server, room);
            
            // send start message
            String msgToPlayer = "";
            if (i == 1) {
                //send start message
                msgToPlayer = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.START.name();
            } else {
                //send painter
                msgToPlayer = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.CHANGE_TURN.name();
            }
            ObjectModel obj = new ObjectModel(msgToPlayer, room);
            for (Player player : lsPlayers) {
                senderServer.sendObjectData(obj, server, player.getHost(), player.getPort());
            }
            
            countdown.start();

            try {
                countdown.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //send room result when end countdown
            if (!countdown.isAlive()) {
                System.out.println("end countdown" + i);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
