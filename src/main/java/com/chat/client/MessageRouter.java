package com.chat.client;

import com.chat.controllers.ChatController;
import com.chat.message.NetMessage;
import com.chat.message.type.NewUser;
import com.chat.message.type.UserLeft;

public class MessageRouter {

    public static void router(NetMessage msg, ChatController controller) {

        if(msg instanceof NewUser) {
            //@TODO controller method
        }
        if(msg instanceof UserLeft) {
            //@TODO controller method
        }

    }

}
