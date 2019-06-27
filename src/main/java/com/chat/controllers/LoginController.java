package com.chat.controllers;

import com.chat.message.factory.NewClientFactory;
import com.chat.message.type.NewClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class LoginController {

    private final SimpleStringProperty stringProperty = new SimpleStringProperty();
    private MainController parentController;

    @FXML
    private TextField nameField;
    @FXML
    private Text errorMessage;

    @FXML
    public void initialize() {
        nameField.textProperty().bindBidirectional(stringProperty);
        errorMessage.setVisible(false);
    }

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    @FXML
    public void onEnterName() {
        if (checkName(stringProperty.getValue())) {
            sendNewClient();
        } else {
            errorMessage.setVisible(true);
        }
    }

    @FXML
    public void onEnterPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            if (checkName(stringProperty.getValue())) {
                sendNewClient();
            } else {
                errorMessage.setVisible(true);
            }

        }
    }

    private void sendNewClient() {
        NewClient newClient = NewClientFactory.create(stringProperty.getValue());
        parentController.getClient().trySendMessage(newClient);
    }

    private boolean checkName(String name) {
        return name != null && name.length() > 3 && name.length() < 12;
    }

}
