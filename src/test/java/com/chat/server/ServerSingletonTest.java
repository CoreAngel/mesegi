package com.chat.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServerSingletonTest {

    @Test
    void getInstance() {
        Server instanece1 = ServerSingleton.getInstance(1000);
        Server instanece2 = ServerSingleton.getInstance(1000, 500);

        assertEquals(instanece1, instanece2);
    }

}
