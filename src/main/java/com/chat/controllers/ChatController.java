package com.chat.controllers;

import java.io.IOException;

import com.chat.client.ClientWindow;
import javafx.fxml.FXML;

public class ChatController {

    @FXML
    private void switchToPrimary() throws IOException {
        ClientWindow.setRoot("fxml/login");
    }
}