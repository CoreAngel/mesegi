package com.chat.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class LastPingChecker implements Runnable {

    private ArrayList<ClientInfo> clients;
    private AtomicBoolean running;

    private static long TIMEOUT = 60;

    public LastPingChecker(ArrayList<ClientInfo> clients, AtomicBoolean running) {
        this.clients = clients;
        this.running = running;
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                long nowInSec = (new Date()).getTime() / 1000;

                for (ClientInfo client : clients) {
                    long lastPingInSec = client.getLastPing().getTime() / 1000;
                    if ((nowInSec - lastPingInSec) > TIMEOUT) {
                        clients.remove(client);
                    }
                }

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

}
