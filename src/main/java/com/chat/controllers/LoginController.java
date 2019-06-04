package com.chat.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField nameField;

    private SimpleStringProperty stringProperty = new SimpleStringProperty();
    private MainController parentController;

    @FXML
    public void initialize() {
        nameField.textProperty().bindBidirectional(stringProperty);
    }

    @FXML
    public void onEnterName() throws IOException {
        if (checkName(stringProperty.getValue())) {
            this.parentController.switchToChat();
        }
    }

    @FXML
    public void onEnterPressed(KeyEvent e) throws IOException {
        if (e.getCode() == KeyCode.ENTER) {
            parentController.switchToChat();
        }

    }

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    private boolean checkName(String name) {
        return name != null && name.length() > 3;
    }

}
