package com.chat.message;

import com.chat.message.type.NetMessage;
import com.chat.message.type.NewClient;

public class MsgParser {

    public static NetMessage convertToType(NetMessage msg) {
        String type = msg.getMsgType();

        String newClientType = NewClient.class.getSimpleName();

        if (newClientType.equals(type))
            return (NewClient)msg;

        return null;
    }
}
