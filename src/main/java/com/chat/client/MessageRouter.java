package com.chat.client;

import com.chat.controllers.ChatController;
import com.chat.controllers.MainController;
import com.chat.message.NetMessage;
import com.chat.message.type.EnterNewUser;
import com.chat.message.type.NewUser;
import com.chat.message.type.TextMessage;
import com.chat.message.type.UserLeft;

public class MessageRouter {

    public static void router(NetMessage msg, MainController controller) {
        try {
            if(msg instanceof EnterNewUser) {
                EnterNewUser enterNewUser = (EnterNewUser) msg;
                controller.switchToChat();
                ChatController chatController = controller.getChatController();
                chatController.setUserData(enterNewUser.getID(), enterNewUser.getName(), enterNewUser.getUserList());
                chatController.renderUserList();
            }

            if(msg instanceof NewUser) {
                NewUser newUser = (NewUser) msg;
                ChatController chatController = controller.getChatController();
                chatController.addUser(newUser.getID(), newUser.getName());
                chatController.renderUserList();
            }

            if(msg instanceof UserLeft) {
                UserLeft newUser = (UserLeft) msg;
                ChatController chatController = controller.getChatController();
                chatController.removeUser(newUser.getID());
                chatController.renderUserList();
            }
            if(msg instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) msg;

                ChatController chatController = controller.getChatController();
                chatController.addMessage(textMessage.getUserID(), textMessage.getUserName(), textMessage.getText(), textMessage.getDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
