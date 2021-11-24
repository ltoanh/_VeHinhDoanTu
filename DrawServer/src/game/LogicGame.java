package game;

import constant.StreamData;
import helpers.CountdownHelpers;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ObjectModel;
import model.Player;
import model.Room;
import static server.Server.senderServer;

/**
 *
 * @author whiwf
 */
public class LogicGame extends Thread {

    private DatagramSocket server;

    private int roomID;
    private Room room;
    private int turn;

    private String word;

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

            //countdown
            CountdownHelpers countdown = new CountdownHelpers(30, i, server, room);

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

            //send word!
            ArrayList<String> wordList = new ArrayList<>();
            dao.DAO dao = new dao.DAO();
            wordList = dao.getWord();

            Random rd = new Random();
            int num1 = rd.nextInt(wordList.size());
            String word = wordList.get(num1);
            room.setWord(word);
            String msgWord = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.RECEIVE_WORD.name();
            ObjectModel objWordModel = new ObjectModel(msgWord, room);

            for (Player player : lsPlayers) {
                senderServer.sendObjectData(obj, server, player.getHost(), player.getPort());

                senderServer.sendObjectData(objWordModel, server, player.getHost(), player.getPort());
            }

            countdown.start();

            try {
                // cho` ket thuc countdown
                countdown.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //send room result when end countdown
            if (!countdown.isAlive()) {
                try {
                    // sap xep ds theo thu tu diem desc
                    lsPlayers.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
                    room.setListPlayer(lsPlayers);
                    
                    System.out.println(lsPlayers);
                    // send result to all player
                    String turnResult = StreamData.Type.GAME_EVENT.name() + ";" + StreamData.Type.TURN_RESULT.name();
                    ObjectModel objResultTurn = new ObjectModel(turnResult, room);
                    for (Player player : lsPlayers) {
                        senderServer.sendObjectData(objResultTurn, server, player.getHost(), player.getPort());
                    }

                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getWord() {
        return word;
    }
}
