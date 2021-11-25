package helpers;

import java.util.ArrayList;
import java.util.Random;
import model.Account;
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
     * Tim kiem idx room theo roomid
     */
    public static int getRoomIndexByRoomID(int roomID){
        for(int i = 0; i < server.Server.listRoom.size(); ++i){
            if(roomID == server.Server.listRoom.get(i).getId()){
                return i;
            }
        }
        return -1;
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
    
    /**
     * Tim kiem player theo account
     */
    public static int findPlayerIndexByAccount(ArrayList<Player> lsPlayers, Account account){
        for(int i = 0; i < lsPlayers.size(); ++i){
            if(lsPlayers.get(i).getAccount().getUsername().equals(account.getUsername())){
                return i;
            }
        }
        return 0;
    }
}
