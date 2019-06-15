package com.chat.message.factory;

import com.chat.message.type.NewUser;

public class NewUserFactory implements MessageFactory {

    public static NewUser create(String name, long id) {
        return new NewUser(name, id);
    }

}
