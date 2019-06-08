package com.chat.server;

import com.chat.message.NetMessage;
import com.chat.message.type.NewClient;
import com.chat.message.type.NewUser;
import com.chat.message.type.UserLeft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadHandler implements Runnable {
    private Socket socket;
    private ArrayList<ClientInfo> clients;
    private ClientInfo client;
    private long id;

    public ThreadHandler(Socket socket, long id, ArrayList<ClientInfo> clients) {
        this.socket = socket;
        this.clients = clients;
        this.id = id;
    }

    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            client = waitForNameFromClient(out, in);
            NewUser user = new NewUser(client.getName(), client.getID());

            for (ClientInfo clientInfo : clients) {
                clientInfo.getOutputStream().writeObject(user);
            }

            while(true) {
                NetMessage message = (NetMessage) in.readObject();

                for (ClientInfo clientInfo : clients) {
                    clientInfo.getOutputStream().writeObject(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client.getOutputStream() != null) {
                clients.remove(client);
            }

            UserLeft message = new UserLeft(client.getID());
            try {
                for (ClientInfo clientInfo : clients) {
                    clientInfo.getOutputStream().writeObject(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            client = null;

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ClientInfo waitForNameFromClient(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException {
        while (true) {
            NetMessage msg = (NetMessage) in.readObject();
            if (!(msg instanceof NewClient)) {
                continue;
            }
            NewClient message = (NewClient)msg;
            ClientInfo client = new ClientInfo(message.getName(), id, in, out);
            clients.add(client);
            System.out.println(message.getName() + " join");
            return client;
        }
    }
}
