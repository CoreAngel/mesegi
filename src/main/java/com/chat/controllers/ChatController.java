package com.chat.controllers;

import com.chat.client.Client;
import com.chat.client.ClientWindow;
import com.chat.message.type.TextMessage;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class ChatController {

    private long id;
    private String name;
    private TreeMap<Long, String> users = new TreeMap<>();
    private Client client;
    private final SimpleStringProperty stringProperty = new SimpleStringProperty();

    @FXML
    private TextField messageField;
    @FXML
    private VBox usersContainer;
    @FXML
    private VBox messagesContainer;


    public void setUserData(long id, String name, TreeMap<Long, String> list) {
        this.id = id;
        this.name = name;
        this.users = list;
    }

    public void addUser(long id, String name) {
        users.put(id, name);
    }

    public void removeUser(long id) {
        users.remove(id);
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void renderUserList() {
        Platform.runLater(() -> usersContainer.getChildren().clear());

        for(Map.Entry<Long,String> user : users.entrySet()) {
            FXMLLoader loader = ClientWindow.loadFXML("fxml/user");
            UserController controller = new UserController(user.getKey(), user.getValue());
            loader.setController(controller);
            Platform.runLater(() -> {
                try {
                    usersContainer.getChildren().add(loader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    public void addMessage(long id, String name, String text, Date date) {
        TextMessageController textMessageController;

        if (this.id == id) {
            textMessageController = new TextMessageController(text, date);
        } else {
            textMessageController = new TextMessageController(name, text, date);
        }

        FXMLLoader loader = ClientWindow.loadFXML("fxml/textMessage");
        loader.setController(textMessageController);
        Platform.runLater(() -> {
            try {
                messagesContainer.getChildren().add(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    public void onEnterMessage() {
        if (!stringProperty.getValue().isBlank()) {
            sendTextMessage();
        }
    }

    @FXML
    public void onEnterPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER && !stringProperty.getValue().isBlank()) {
            sendTextMessage();
        }
    }

    private void sendTextMessage() {
        TextMessage msg = new TextMessage(name, id, stringProperty.getValue());
        client.send(msg);
        stringProperty.setValue("");
    }

    @FXML
    public void initialize() {
        messageField.textProperty().bindBidirectional(stringProperty);
    }
}