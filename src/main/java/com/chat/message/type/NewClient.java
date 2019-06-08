package com.chat.message.type;

import com.chat.message.NetMessage;

public class NewClient extends NetMessage {

    private String name;

    public NewClient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
