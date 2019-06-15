package com.chat.client.router;

import com.chat.controllers.ChatController;
import com.chat.controllers.MainController;
import com.chat.message.NetMessage;
import com.chat.message.type.TextMessage;

public class TextMessageRoute extends MessageRoute {

    TextMessageRoute(NetMessage msg, MainController controller) {
        super(msg, controller);
    }

    @Override
    public void execute() {
        TextMessage textMessage = (TextMessage) message;
        ChatController chatController = controller.getChatController();
        chatController.addMessage(textMessage.getUserID(), textMessage.getUserName(), textMessage.getText(), textMessage.getDate());
    }

}
