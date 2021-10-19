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
public class CountdownHelpers {
    // thay doi khi code obj
    private static Timer timer;
    private static final int countdownTime = 10;
    private static int curSecond;
    private static int turn;
    
    //============================= countdown ==================================
    public static void countdownToChangeTurn(DatagramSocket server, int roomID) {
        timer = new Timer();
        curSecond = countdownTime;
        turn = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String msg = StreamData.Type.GAME_EVENT.name() + ";" 
                        + StreamData.Type.COUNTDOWN.name() + ";" 
                        + turn + ";" + curSecond;
                ObjectModel obj = new ObjectModel(msg, null);
                //send to all player in room
                Room curRoom = helpers.RoomHelpers.checkRoomByID(roomID);
                for (Player player : curRoom.getListPlayer()) {
                    senderServer.sendObjectData(obj, server, player.getHost(), player.getPort());
                }
            }

        }, 0, 1000);
    }

    public static int setIntervalCountdownTime() {
        if (curSecond < 0) {
            curSecond = countdownTime;
            turn++;
            if (turn == 3) {
                timer.cancel();
                return 0;
            }
        }

        return curSecond--;
    }
}
