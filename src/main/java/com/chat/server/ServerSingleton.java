package com.chat.server;

public class ServerSingleton {

    private static Server instance;

    private ServerSingleton() {}

    public static synchronized Server getInstance(int port) {
        if (instance == null) {
            instance = new Server(port);
        }
        return instance;
    }

    public static synchronized Server getInstance(int port, int maxClients) {
        if (instance == null) {
            instance = new Server(port, maxClients);
        }
        return instance;
    }

}
