package com.chat.client;

import com.chat.controllers.MainController;
import com.chat.message.NetMessage;

import java.io.ObjectInputStream;

public class ClientListener implements Runnable {

    private ObjectInputStream inputStream;
    private MainController controller;

    public ClientListener(ObjectInputStream inputStream, MainController controller) {
        this.inputStream = inputStream;
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            try {
                NetMessage msg = (NetMessage) inputStream.readObject();
                MessageRouter.router(msg, controller);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
