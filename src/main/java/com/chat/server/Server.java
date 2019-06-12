package com.chat.server;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {

    private AtomicBoolean running = new AtomicBoolean(true);
    private long clientID = 0;
    private int port;
    private int maxClients;

    private Thread pingChecker;

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

        pingChecker = new Thread(new LastPingChecker(clients, running));
        pingChecker.start();

        Runtime.getRuntime().addShutdownHook(new Thread(this::closeServer));

        pool = Executors.newFixedThreadPool(maxClients);
        try {
            ServerSocket listener = new ServerSocket(port);
            while(running.get()) {
                pool.execute(new ThreadHandler(listener.accept(), clientID++, clients));
            }
        } catch (Exception e) {
            System.out.println("Server exception");
            closeServer();
        }
    }

    private void closeServer() {
        running.set(false);
        if(!pingChecker.isInterrupted()) {
            pingChecker.interrupt();
        }

        try {
            if(!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }


    }

}
