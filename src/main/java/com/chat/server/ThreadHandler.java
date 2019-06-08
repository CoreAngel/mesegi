package com.chat.server;

import com.chat.message.MsgParser;
import com.chat.message.type.NetMessage;
import com.chat.message.type.NewClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadHandler implements Runnable {
    private Socket socket;
    private ArrayList<ClientInfo> clients;
    private long id;

    public ThreadHandler(Socket socket, long id, ArrayList<ClientInfo> clients) {
        this.socket = socket;
        this.clients = clients;
        this.id = id;
    }

    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                NetMessage msg = (NetMessage) in.readObject();
                msg = MsgParser.convertToType(msg);
                if (!(msg instanceof NewClient)) {
                    continue;
                }
                NewClient message = (NewClient)msg;
                ClientInfo client = new ClientInfo(message.getName(), id, in, out);
                clients.add(client);
                System.out.println(message.getName() + "join");
                break;
            }

//            for (PrintWriter writer : writers) {
//                writer.println("MESSAGE " + name + " has joined");
//            }

            // Accept messages from this client and broadcast them.
//            while (true) {
//                String input = in.nextLine();
//                if (input.toLowerCase().startsWith("/quit")) {
//                    return;
//                }
//                for (PrintWriter writer : writers) {
//                    writer.println("MESSAGE " + name + ": " + input);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (out != null) {
//                writers.remove(out);
//            }
//            if (name != null) {
//                System.out.println(name + " is leaving");
//                names.remove(name);
//                for (PrintWriter writer : writers) {
//                    writer.println("MESSAGE " + name + " has left");
//                }
//            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
