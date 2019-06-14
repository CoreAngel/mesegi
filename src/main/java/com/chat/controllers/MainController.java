package com.chat.controllers;

import java.io.IOException;

import com.chat.client.Client;
import com.chat.client.ClientWindow;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class MainController {

    private Client client;
    private double x = 0;
    private double y = 0;

    @FXML
    private VBox mainInclude;
    @FXML
    private HBox menuBar;

    private final FXMLLoader loginLoader = ClientWindow.loadFXML("fxml/login");
    private final FXMLLoader chatLoader = ClientWindow.loadFXML("fxml/chat");

    @FXML
    public void initialize() throws IOException {
        mainInclude.getChildren().add(loginLoader.load());
        LoginController loginController = loginLoader.getController();
        loginController.setParentController(this);

        this.client = new Client("localhost", 58395, this);
    }

    @FXML
    public void switchToChat() throws IOException {
        Platform.runLater(() -> mainInclude.getChildren().clear());
        BorderPane borderPane = chatLoader.load();
        Platform.runLater(() -> mainInclude.getChildren().add(borderPane));
        ((ChatController)chatLoader.getController()).setClient(client);
        ClientWindow.setSize(borderPane.getPrefWidth(), borderPane.getPrefHeight() + menuBar.getHeight());
    }

    @FXML
    public void closeProgram() {
        client.setRunning(false);
        client.interruptThreads();
        Platform.exit();
    }

    @FXML
    void minimaliseProgram() {
        ClientWindow.getStage().setIconified(true);
    }

    @FXML
    void onDragged(MouseEvent e) {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(e.getScreenX() - x);
        stage.setY(e.getScreenY() - y);
    }

    @FXML
    void onPressed(MouseEvent e) {
        x = e.getSceneX();
        y = e.getSceneY();
    }

    public ChatController getChatController() {
        return chatLoader.getController();
    }

    public Client getClient() {
        return client;
    }

}
