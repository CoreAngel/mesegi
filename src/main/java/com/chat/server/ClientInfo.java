package com.chat.server;

import java.net.InetAddress;

public class ClientInfo {

    private String name;
    private long id;
    private InetAddress address;
    private int port;

    public ClientInfo(String name, long id, InetAddress address, int port) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public long getID() {
        return id;
    }

    public InetAddress getAddresss() {
        return address;
    }

    public int getPort() {
        return port;
    }

}
