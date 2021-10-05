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

        result = Enum.valueOf(StreamData.Type.class, typeName);

        return result;
    }
    
    // received data: enumname;data1;data2;...
    public static Type getTypeFromReceivedData(String msg){
        String typeStr = msg.split(";")[0];
        return getType(typeStr);
    }
}
