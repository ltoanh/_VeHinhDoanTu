package constant;

/**
 *
 * @author whiwf
 */
public class StreamData {

    public enum Type {
        LOGIN, //dang nhap
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
        PAINT1, //player 1 ve 
        PAINT2, //player 2 ve
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

        try {
            result = Enum.valueOf(StreamData.Type.class, typeName);
        } catch (Exception e) {
            System.err.println("Unknow type: " + e.getMessage());
        }

        return result;
    }

    public static Type getTypeFromReceivedData(String data) {
        String[] typeStr = data.split(";");
        return getType(typeStr[0]);
    }
}
