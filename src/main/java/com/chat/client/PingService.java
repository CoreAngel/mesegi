package com.chat.client;

import com.chat.message.type.Ping;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;

public class PingService implements Runnable {

    private ObjectOutputStream outputStream;
    private AtomicBoolean running;

    private final long SECOUND_TO_WAIT = 18;

    public PingService(ObjectOutputStream inputStream, AtomicBoolean running) {
        this.outputStream = inputStream;
        this.running = running;
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                sendPing();
                waitToSendNextPing();
            } catch (InterruptedException | SocketException e) {
                closeStream();
                return;
            } catch (IOException e) {
                //ignore
            }
        }
    }

    private void closeStream() {
        try {
            outputStream.flush();
            outputStream.close();
        } catch (Exception ex){
            //ignore
        }
    }

    private void sendPing() throws IOException {
        Ping ping = new Ping();
        outputStream.writeObject(ping);
    }

    private void waitToSendNextPing() throws InterruptedException {
        Thread.sleep(SECOUND_TO_WAIT * 1000);
    }
}
