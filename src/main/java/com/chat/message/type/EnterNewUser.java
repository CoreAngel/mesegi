package com.chat.message.type;

import java.util.TreeMap;

public class EnterNewUser extends NewUser {

    private TreeMap<Long, String> list;

    public EnterNewUser(String name, long id, TreeMap<Long, String> list) {
        super(name, id);
        this.list = list;
    }

    public TreeMap<Long, String> getUserList() {
        return list;
    }

}
