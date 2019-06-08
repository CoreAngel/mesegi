package com.chat;

import com.chat.client.ClientWindow;
import com.chat.server.Server;

public class Main {

    public static void main(String[] args) {
        if(args.length == 1 && args[0].equals("server")) {
            Server server = new Server(58395);
            server.start();
        } else {
            ClientWindow.run();
        }
    }

}
