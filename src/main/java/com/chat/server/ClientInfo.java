package com.chat.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientInfo {

    private String name;
    private long id;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

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

}
