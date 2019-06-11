package com.chat.client;

import com.chat.controllers.MainController;
import com.chat.message.NetMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private Socket socket;
    private InetAddress address;
    private int port;

    private MainController mainController;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private Thread listener;
    private Thread pingService;

    public Client(String address, int port, MainController controller) {
        try {
            this.address = InetAddress.getByName(address);
            this.port = port;
            this.socket = new Socket(address, port);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.mainController = controller;

            ClientListener clientListener = new ClientListener(inputStream, mainController);
            this.listener = new Thread(clientListener);
            this.listener.start();
            PingService pingService = new PingService(outputStream);
            this.pingService = new Thread(pingService);
            this.pingService.start();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void send(NetMessage message) {
        try {
            outputStream.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
