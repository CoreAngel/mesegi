package com.chat.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {

    private final Server server = new Server(1000, 1000);

    @Test
    void createPingCheckerThread() {
        assertNotNull(server.createPingCheckerThread());
    }

}
