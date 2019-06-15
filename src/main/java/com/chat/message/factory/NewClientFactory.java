package com.chat.message.factory;

import com.chat.message.type.NewClient;

public class NewClientFactory implements MessageFactory {

    public static NewClient create(String name) {
        return new NewClient(name);
    }

}
