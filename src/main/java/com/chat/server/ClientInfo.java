package com.chat.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class ClientInfo {

    private String name;
    private long id;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Date lastPing = new Date();

    public ClientInfo(String name, long id, ObjectInputStream in, ObjectOutputStream out) {
        this.name = name;
        this.id = id;
        this.inputStream = in;
        this.outputStream = out;
    }

    public String getName() {
        return name;
    }

    public long getID() {
        return id;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public Date getLastPing() {
        return lastPing;
    }

    public void setLastPing(Date lastPing) {
        this.lastPing = lastPing;
    }
}
