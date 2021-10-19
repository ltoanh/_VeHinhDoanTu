package helpers;

import java.util.ArrayList;
import java.util.Random;
import model.Player;
import model.Room;

/**
 *
 * @author whiwf
 */
public class RoomHelpers {
    /**
     * Tim kiem phong trong danh sach phong hien co cua server
     * @param id
     * @return 
     */
    public static Room checkRoomByID(int id){
        for(Room room : server.Server.listRoom){
            if(room.getId() == id){
                return room;
            }
        }
        
        return null;
    }
    
    /**
     * Lua chon nguoi ve
     */
    public static ArrayList chooseLsPlayerToDraw(ArrayList<Player> lsPlayers){
        ArrayList<String> lsPainterUsername = new ArrayList<>();
       
        int size = lsPlayers.size();
        
        Random rd = new Random();
        int num1 = rd.nextInt(0 + size);
        
        int num2 = 0;
        
        do{
            num2 = rd.nextInt(0 + size);
        } while(num2 == num1);
        
        lsPainterUsername.add(lsPlayers.get(num1).getAccount().getUsername());
        lsPainterUsername.add(lsPlayers.get(num2).getAccount().getUsername());
        
        return lsPainterUsername;
    }
}
