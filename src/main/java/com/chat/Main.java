package com.chat;

import com.chat.client.ClientWindow;
import com.chat.server.Server;
import com.chat.server.ServerSingleton;

public class Main {

    public static void main(String[] args) {
        if(args.length == 1 && args[0].equals("server")) {
            Server server = ServerSingleton.getInstance(58395);
            server.start();
        } else {
            ClientWindow.run();
        }
    }

}
