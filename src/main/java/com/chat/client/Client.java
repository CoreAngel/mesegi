package com.chat.client;

import com.chat.controllers.ChatController;
import com.chat.message.NetMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private Socket socket;
    private InetAddress address;
    private int port;

    private ChatController chatController;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private Thread listener;
    private Thread pingService;

    public Client(String address, int port, ChatController controller) {
        try {
            this.address = InetAddress.getByName(address);
            this.port = port;
            this.socket = new Socket(address, port);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.chatController = controller;

            ClientListener clientListener = new ClientListener(inputStream, chatController);
            this.listener = new Thread(clientListener);
            this.listener.start();
            PingService pingService = new PingService(outputStream);
            this.pingService = new Thread(pingService);
            this.pingService.start();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setChatController(ChatController controller) {
        this.chatController = controller;
    }

    public void send(NetMessage message) {
        try {
            outputStream.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
