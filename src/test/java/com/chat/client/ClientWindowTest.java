package com.chat.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientWindowTest {

    @Test
    void loadFXML() {
        assertNotNull(ClientWindow.loadFXML("fxml/chat"));
    }

}
