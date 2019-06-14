package com.chat.client;

import com.chat.controllers.MainController;
import com.chat.message.NetMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {

    private Socket socket;
    private InetAddress address;
    private int port;

    private AtomicBoolean running = new AtomicBoolean(true);

    private MainController mainController;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private Thread listener;
    private Thread pingService;

    public Client(String address, int port, MainController controller) {
        this.mainController = controller;
        this.port = port;

        try {
            this.address = InetAddress.getByName(address);
            this.socket = new Socket(address, port);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());

            listener = createListenerThread();
            listener.start();
            pingService = createPingThread();
            pingService.start();

            Runtime.getRuntime().addShutdownHook(new Thread(controller::closeProgram));
        } catch (Exception e) {
            System.out.println("Client error");
            mainController.closeProgram();
            e.printStackTrace();
        }

    }

    private Thread createListenerThread() {
        ClientListener clientListener = new ClientListener(inputStream, mainController, running);
        return new Thread(clientListener);
    }

    private Thread createPingThread() {
        PingService pingService = new PingService(outputStream, running);
        return new Thread(pingService);
    }

    public void trySendMessage(NetMessage message) {
        boolean error = true;

        for(int i = 0; i < 10; i++) {
            try {
                send(message);
                error = false;
                break;
            } catch (IOException e) {
                System.out.println("Error in trySendMessage method");
                e.printStackTrace();
            }
        }

        if (error) {
            mainController.closeProgram();
        }

    }

    private void send(NetMessage message) throws IOException {
        outputStream.writeObject(message);
    }

    public void interruptThreads() {
        if(!listener.isInterrupted()) {
            System.out.println("close listener");
            listener.interrupt();
        }
        if(!pingService.isInterrupted()) {
            System.out.println("close ping");
            pingService.interrupt();
        }
    }

    public void setRunning(boolean flag) {
        running.set(flag);
    }

}
