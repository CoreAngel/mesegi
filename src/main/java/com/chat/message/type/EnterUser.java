package com.chat.message.type;

import com.chat.message.NetMessage;

import java.util.TreeMap;

public class EnterUser extends NetMessage {

    private String name;
    private  long id;
    private TreeMap<Long, String> list;

    public EnterUser(String name, long id, TreeMap<Long, String> list) {
        this.name = name;
        this.id = id;
        this.list = list;
    }

    public TreeMap<Long, String> getUserList() {
        return list;
    }

    public String getName() {
        return name;
    }

    public long getID() {
        return id;
    }
}
