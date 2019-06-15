package com.chat.client.router;

import com.chat.controllers.MainController;
import com.chat.message.NetMessage;

public abstract class MessageRoute {

    final NetMessage message;
    final MainController controller;

    MessageRoute(NetMessage msg, MainController controller) {
        this.message = msg;
        this.controller = controller;
    }

    public abstract void execute();

}
