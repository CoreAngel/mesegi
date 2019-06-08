package com.chat.server;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private boolean running = true;
    private long clientID = 0;
    private int port;
    private int maxClients;

    private static final int MAX_CLINTS = 500;

    private ArrayList<ClientInfo> clients = new ArrayList<>();
    private ExecutorService pool;

    public Server(int port) {
        this(port, MAX_CLINTS);
    }

    public Server(int port, int maxClients) {
        this.port = port;
        this.maxClients = maxClients;
    }


    public void start() {
        System.out.println("The chat server is running...");
        pool = Executors.newFixedThreadPool(maxClients);
        try {
            ServerSocket listener = new ServerSocket(port);
            while(running) {
                pool.execute(new ThreadHandler(listener.accept(), clientID++, clients));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
