package com.chat.message.type;

import com.chat.message.NetMessage;

public class NewUser extends NetMessage {

    private String name;
    private long id;

    public NewUser(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public long getID() {
        return id;
    }

    public String getName() {
        return name;
    }
}
