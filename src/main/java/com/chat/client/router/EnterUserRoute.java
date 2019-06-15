package com.chat.client.router;

import com.chat.controllers.ChatController;
import com.chat.controllers.MainController;
import com.chat.message.NetMessage;
import com.chat.message.type.EnterUser;

public class EnterUserRoute extends MessageRoute {

    EnterUserRoute(NetMessage msg, MainController controller) {
        super(msg, controller);
    }

    @Override
    public void execute() {
        EnterUser enterUser = (EnterUser) message;
        controller.switchToChat();
        ChatController chatController = controller.getChatController();
        chatController.setUserData(enterUser.getID(), enterUser.getName(), enterUser.getUserList());
        chatController.renderUserList();
    }
}
