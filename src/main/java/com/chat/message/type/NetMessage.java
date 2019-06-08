package com.chat.message.type;

public abstract class NetMessage {

    private String type;

    public NetMessage(String type) {
        this.type = type;
    }

    public String getMsgType() {
        return type;
    }

}
