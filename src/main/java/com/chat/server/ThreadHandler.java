package com.chat.server;

import com.chat.message.NetMessage;
import com.chat.message.type.*;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

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

            while(true) {
                NetMessage message = (NetMessage) in.readObject();

                if(message instanceof TextMessage) {
                    ((TextMessage) message).setDate(new Date());
                }

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

            TreeMap<Long, String> list = new TreeMap<>();
            for (ClientInfo clientInfo : clients) {
                list.put(clientInfo.getID(), clientInfo.getName());
            }
            EnterNewUser enterNewUser = new EnterNewUser(client.getName(), client.getID(), list);
            client.getOutputStream().writeObject(enterNewUser);

            NewUser user = new NewUser(client.getName(), client.getID());
            for (ClientInfo clientInfo : clients) {
                clientInfo.getOutputStream().writeObject(user);
            }

            clients.add(client);
            System.out.println(client.getID() + ": " + client.getName() + " - join");
            return client;
        }
    }
}
