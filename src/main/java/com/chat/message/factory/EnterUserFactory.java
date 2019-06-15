package com.chat.message.factory;

import com.chat.message.type.EnterUser;

import java.util.TreeMap;

public class EnterUserFactory implements MessageFactory {

    public static EnterUser create(String name, long id, TreeMap<Long, String> list) {
        return new EnterUser(name, id, list);
    }

}
