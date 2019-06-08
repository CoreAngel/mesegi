package com.chat.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    public Client(String address, int port) {
        try {
            this.address = InetAddress.getByName(address);
            this.port = port;

            this.socket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
        send("\\con:LOL");
    }

    public void send(String message) {
        try {
            message += "\\e";
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
