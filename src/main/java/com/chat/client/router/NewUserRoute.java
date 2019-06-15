package com.chat.client.router;

import com.chat.controllers.ChatController;
import com.chat.controllers.MainController;
import com.chat.message.NetMessage;
import com.chat.message.type.NewUser;

public class NewUserRoute extends MessageRoute {

    NewUserRoute(NetMessage msg, MainController controller) {
        super(msg, controller);
    }

    @Override
    public void execute() {
        NewUser newUser = (NewUser) message;
        ChatController chatController = controller.getChatController();
        chatController.addUser(newUser.getID(), newUser.getName());
        chatController.renderUserList();
    }

}
