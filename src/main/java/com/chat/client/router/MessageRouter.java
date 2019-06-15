package com.chat.client.router;

import com.chat.controllers.MainController;
import com.chat.message.NetMessage;
import com.chat.message.type.EnterUser;
import com.chat.message.type.NewUser;
import com.chat.message.type.TextMessage;
import com.chat.message.type.UserLeft;

//command
public class MessageRouter {

    public static void router(NetMessage msg, MainController controller) {
        if(msg instanceof EnterUser) {
            MessageRoute route = new EnterUserRoute(msg, controller);
            route.execute();
        }

        if(msg instanceof NewUser) {
            MessageRoute route = new NewUserRoute(msg, controller);
            route.execute();
        }

        if(msg instanceof UserLeft) {
            MessageRoute route = new UserLeftRoute(msg, controller);
            route.execute();
        }

        if(msg instanceof TextMessage) {
            MessageRoute route = new TextMessageRoute(msg, controller);
            route.execute();
        }
    }
}
