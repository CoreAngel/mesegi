package com.chat.message.factory;

import com.chat.message.type.Ping;

public class PingFactory implements MessageFactory {

    public static Ping create() {
        return new Ping();
    }

}
