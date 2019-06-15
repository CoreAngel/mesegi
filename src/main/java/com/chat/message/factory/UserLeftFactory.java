package com.chat.message.factory;

import com.chat.message.type.UserLeft;

public class UserLeftFactory {

    public static UserLeft create(long id) {
        return new UserLeft(id);
    }

}
