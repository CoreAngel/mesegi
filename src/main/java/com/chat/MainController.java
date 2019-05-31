package com.chat;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML
    private void switchToSecondary() throws IOException {

    }

    @FXML
    void onPressed(MouseEvent event) {
        Platform.exit();
    }
}
