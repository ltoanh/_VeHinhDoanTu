package constant;

/**
 *
 * @author whiwf
 */
public class StreamData {

    public enum Type {
        LOGIN, // dang nhap
        SIGNUP, // dang ky
        
        // room
        CREATE_ROOM, //tao phong
        LOBBY_ROOM, // phong cho
        
        // in game
        JOIN_ROOM, // join room
        CHAT_ROOM, //chat

        //game
        GAME_EVENT, // cac su kien lien quan den in game
        START, // bat dau game
        DRAW_POSITION, // ve hinh
        CHANGE_TURN, // thay doi turn nguoi ve
        RECEIVE_WORD,
        // specific
        EXIT, // thoat
        COUNTDOWN, // countdown time
        ERROR,
        UNKNOW_TYPE,
    }

    public static Type getType(String typeName) {
        Type result = Type.UNKNOW_TYPE;

        result = Enum.valueOf(StreamData.Type.class, typeName);

        return result;
    }
    
    // received data: enumname;data1;data2;...
    public static Type getTypeFromReceivedData(String msg){
        String typeStr = msg.split(";")[0];
        return getType(typeStr);
    }
}
