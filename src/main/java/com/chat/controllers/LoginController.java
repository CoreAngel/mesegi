package com.chat.controllers;

import com.chat.message.type.NewClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;

public class LoginController {

    @FXML
    private TextField nameField;

    private final SimpleStringProperty stringProperty = new SimpleStringProperty();
    private MainController parentController;

    @FXML
    public void initialize() {
        nameField.textProperty().bindBidirectional(stringProperty);
    }

    @FXML
    public void onEnterName() {
        if (checkName(stringProperty.getValue())) {
            sendNewClient();
        }
    }

    @FXML
    public void onEnterPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER && checkName(stringProperty.getValue())) {
            sendNewClient();
        }
    }

    private void sendNewClient() {
        NewClient newClient = new NewClient(stringProperty.getValue());
        parentController.getClient().send(newClient);
    }

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    private boolean checkName(String name) {
        return name != null && name.length() > 3 && name.length() < 12;
    }

}
