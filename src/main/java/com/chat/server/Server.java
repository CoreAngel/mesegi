package com.chat.server;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Server {

    private int port;
    private int maxClients;

    private final AtomicLong nextClientID =  new AtomicLong(0);
    private AtomicBoolean running = new AtomicBoolean(true);

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

        pingChecker = createPingCheckerThread();
        pingChecker.start();

        Runtime.getRuntime().addShutdownHook(new Thread(this::closeServer));

        pool = Executors.newFixedThreadPool(maxClients);
        try {
            ServerSocket listener = new ServerSocket(port);
            while(running.get()) {
                synchronized (nextClientID) {
                    pool.execute(new ThreadHandler(listener.accept(), nextClientID.get(), clients));
                    nextClientID.set(nextClientID.get() + 1);
                }
            }
        } catch (Exception e) {
            System.out.println("Server exception");
            closeServer();
        }
    }

    public Thread createPingCheckerThread() {
        LastPingChecker lastPingChecker = new LastPingChecker(clients, running);
        return new Thread(lastPingChecker);
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
