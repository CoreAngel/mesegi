package com.chat.message.factory;

import com.chat.message.type.TextMessage;

public class TextMessageFactory implements MessageFactory {

    public static TextMessage create(String name, long id, String text) {
        return new TextMessage(name, id, text);
    }

}
