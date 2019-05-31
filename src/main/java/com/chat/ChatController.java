package com.chat;

import java.io.IOException;
import javafx.fxml.FXML;

public class ChatController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("fxml/login");
    }
}