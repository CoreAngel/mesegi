package com.chat.client.router;

import com.chat.controllers.ChatController;
import com.chat.controllers.MainController;
import com.chat.message.NetMessage;
import com.chat.message.type.UserLeft;

public class UserLeftRoute extends MessageRoute {

    UserLeftRoute(NetMessage msg, MainController controller) {
        super(msg, controller);
    }

    @Override
    public void execute() {
        UserLeft newUser = (UserLeft) message;
        ChatController chatController = controller.getChatController();
        chatController.removeUser(newUser.getID());
        chatController.renderUserList();
    }

}
