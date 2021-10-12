package helpers;

import model.Room;

/**
 *
 * @author whiwf
 */
public class RoomHelpers {
    public static Room checkRoomByID(int id){
        for(Room room : server.Server.listRoom){
            if(room.getId() == id){
                return room;
            }
        }
        
        return null;
    }
}
