package com.chat.client;

import com.chat.client.router.MessageRouter;
import com.chat.controllers.MainController;
import com.chat.message.NetMessage;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientListener implements Runnable {

    private ObjectInputStream inputStream;
    private MainController controller;
    private AtomicBoolean running;

    public ClientListener(ObjectInputStream inputStream, MainController controller, AtomicBoolean running) {
        this.inputStream = inputStream;
        this.controller = controller;
        this.running = running;
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                NetMessage msg = (NetMessage) inputStream.readObject();
                MessageRouter.router(msg, controller);
            } catch (InterruptedIOException | SocketException e) {
                closeStream();
                return;
            } catch (IOException e) {
                closeStream();
                controller.closeProgram();
            } catch (ClassNotFoundException e) {
                //ignore;
            }
        }
    }

    private void closeStream() {
        try {
            inputStream.close();
        } catch (IOException ex){
            //ignore
        }
    }

}
