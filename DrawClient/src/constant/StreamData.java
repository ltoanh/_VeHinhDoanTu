package constant;

/**
 *
 * @author whiwf
 */
public class StreamData {

    public enum Type {
        
        LOGIN,
        
        // in game
        JOIN_ROOM, // join room
        CHAT_ROOM, //chat 

        //game
        GAME_EVENT, // cac su kien lien quan den in game
        START, // bat dau game
        DRAW_POSITION, // ve hinh

        // specific
        EXIT, // thoat
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
