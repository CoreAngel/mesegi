package com.chat.server;

import com.chat.message.type.Controller;
import com.chat.message.type.Types;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Server {

    private DatagramSocket socket;
    private boolean running;
    private long clientID = 0;

    private ArrayList<ClientInfo> clients = new ArrayList<>();


    public void start(int port) {
        try {
            socket = new DatagramSocket(port);
            running = true;

            listen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) {
        for (ClientInfo client : clients) {
            send(message, client.getAddresss(), client.getPort());
        }
    }

    public void send(String message, InetAddress address, int port) {
        try {
            message += "\\e";
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        Thread listener = new Thread(() -> {
            try {
                while (running) {
                    byte[] data = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(data, data.length);
                    socket.receive(packet);

                    String message = new String(data);
                    message = message.substring(0, message.indexOf("\\e"));

                    Types type = Controller.getType(message);
                    if (Controller.isCommand(type)) {
                        executeCommand(type, message, packet);
                    } else if (Controller.isMessage(type)) {
                        broadcast(message);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        listener.start();

    }

    public void stop() {
        running = false;
    }

    private void executeCommand(Types type, String command, DatagramPacket packet) {
        if (type == Types.CONNECT) {
            command = command.substring(command.indexOf(":")+1);
            String name = command.strip();
            clients.add(new ClientInfo(name, clientID++, packet.getAddress(), packet.getPort()));
        }
    }


}
