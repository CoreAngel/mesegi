package com.chat.message.type;

import com.chat.message.NetMessage;

public class UserLeft extends NetMessage {

    private long id;

    public UserLeft(long id) {
        this.id = id;
    }

    public long getID() {
        return id;
    }

}
