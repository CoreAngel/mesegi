package com.chat.message.type;

public class NewUser extends NewClient {

    private long id;

    public NewUser(String name, long id) {
        super(name);
        this.id = id;
    }

    public long getID() {
        return id;
    }

}
