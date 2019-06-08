package com.chat.client;

import com.chat.message.NetMessage;
import com.chat.message.type.NewClient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private Socket socket;
    private InetAddress address;
    private int port;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public Client(String address, int port) {
        try {
            this.address = InetAddress.getByName(address);
            this.port = port;
            this.socket = new Socket(address, port);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());

            NetMessage msg = new NewClient("Nowy uzytkownik");
            outputStream.writeObject(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public void send(String message) {
//        try {
//            message += "\\e";
//            byte[] data = message.getBytes();
//            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
//            socket.send(packet);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
