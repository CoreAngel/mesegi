package com.chat.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserController {

    private long id;
    private String name;
    @FXML
    private Label label;

    public UserController(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> label.setText(name));
    }

}