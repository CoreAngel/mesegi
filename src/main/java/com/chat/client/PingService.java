package com.chat.client;

import com.chat.message.type.Ping;

import java.io.ObjectOutputStream;

public class PingService implements Runnable {

    private ObjectOutputStream outputStream;

    public PingService(ObjectOutputStream inputStream) {
        this.outputStream = inputStream;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Ping ping = new Ping();
                outputStream.writeObject(ping);
                Thread.sleep(30000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
