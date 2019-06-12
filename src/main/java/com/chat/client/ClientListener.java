package com.chat.client;

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
                System.out.println("listener " + running.get());
                NetMessage msg = (NetMessage) inputStream.readObject();
                MessageRouter.router(msg, controller);
            } catch (InterruptedIOException e) {
                System.out.println("ClientListener: InterruptedIOException");
                closeStream();
                return;
            }
            catch (SocketException e) {
                System.out.println("ClientListener: Socket");
                closeStream();
                return;
            }
            catch (IOException e) {
                System.out.println("ClientListener: IOException");
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
        } catch (Exception ex){
            //ignore
        }
    }

}
