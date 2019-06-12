package com.chat.client;

import com.chat.controllers.MainController;
import com.chat.message.NetMessage;
import javafx.application.Platform;

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

            ClientListener clientListener = new ClientListener(inputStream, mainController, running);
            this.listener = new Thread(clientListener);
            this.listener.start();
            PingService pingService = new PingService(outputStream, running);
            this.pingService = new Thread(pingService);
            this.pingService.start();

            Runtime.getRuntime().addShutdownHook(new Thread(controller::closeProgram));

        } catch (Exception e) {
            System.out.println("Client error");
            e.printStackTrace();
            mainController.closeProgram();
        }

    }

    public void send(NetMessage message) {
        boolean error = true;

        for(int i = 0; i < 10; i++) {
            try {
                outputStream.writeObject(message);
                error = false;
                break;
            } catch (Exception e) {
                System.out.println("Error in send method");
                e.printStackTrace();
            }
        }

        if (error) {
            mainController.closeProgram();
        }

    }

    public void setRunning(boolean flag) {
        running.set(flag);
    }

    public void closeThreads() {
        if(!listener.isInterrupted()) {
            System.out.println("close listener");
            listener.interrupt();
        }
        if(!pingService.isInterrupted()) {
            System.out.println("close ping");
            pingService.interrupt();
        }
    }

}
