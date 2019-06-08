package com.chat.message.type;

public class NewClient extends NetMessage {

    private String name;

    public NewClient(String name) {
        super(NewClient.class.getSimpleName());
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
