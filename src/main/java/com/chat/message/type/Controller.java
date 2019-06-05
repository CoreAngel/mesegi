package com.chat.message.type;

public class Controller {
    public static String getPrefix(Types type) {
       if (type == Types.CONNECT) { return "\\con:"; }
       if (type == Types.DISCONNECT) { return "\\dcon:"; }
       if (type == Types.TEXT) { return "\\txt:"; }
       if (type == Types.IMAGE) { return "\\img:"; }
       if (type == Types.FILE) { return "\\file:"; }
       return "";
    }

    public static Types getType(String prefix) {
        if (prefix.equals("\\con:")) { return Types.CONNECT; }
        if (prefix.equals("\\dcon:")) { return Types.DISCONNECT; }
        if (prefix.equals("\\txt:\":")) { return Types.TEXT; }
        if (prefix.equals("\\img:")) { return Types.IMAGE; }
        if (prefix.equals("\\file:")) { return Types.FILE; }
        return Types.UNKNOWN;
    }

    public static boolean isCommand(Types type) {
        return type == Types.CONNECT || type == Types.DISCONNECT;
    }
    public static boolean isMessage(Types type) {
        return !(type == Types.CONNECT || type == Types.DISCONNECT || type == Types.UNKNOWN);
    }
}
