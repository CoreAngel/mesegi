package com.chat.controllers;

import java.io.IOException;

import com.chat.App;
import javafx.fxml.FXML;

public class ChatController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("fxml/login");
    }
}