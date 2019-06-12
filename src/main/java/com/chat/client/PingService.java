package com.chat.client;

import com.chat.message.type.Ping;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;

public class PingService implements Runnable {

    private ObjectOutputStream outputStream;
    private AtomicBoolean running;

    public PingService(ObjectOutputStream inputStream, AtomicBoolean running) {
        this.outputStream = inputStream;
        this.running = running;
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                Ping ping = new Ping();
                outputStream.writeObject(ping);
                System.out.println("ping");
                Thread.sleep(18000);
            } catch (InterruptedException e) {
                closeStream();
                return;
            } catch (SocketException e) {
                closeStream();
            }
            catch (IOException e) {
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
}
