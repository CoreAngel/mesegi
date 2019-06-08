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

    @FXML
    private VBox mainInclude;
    @FXML
    private HBox menuBar;
    private LoginController loginController;
    private double x = 0;
    private double y = 0;

    @FXML
    public void switchToChat() throws IOException {
        mainInclude.getChildren().clear();
        BorderPane borderPane = ClientWindow.loadFXML("fxml/chat").load();
        mainInclude.getChildren().add(borderPane);
        ClientWindow.setSize(borderPane.getPrefWidth(), borderPane.getPrefHeight() + menuBar.getHeight());
    }

    @FXML
    void closeProgram() {
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

    @FXML
    public void initialize() throws IOException {
        FXMLLoader loader = ClientWindow.loadFXML("fxml/login");
        mainInclude.getChildren().add(loader.load());
        loginController = loader.getController();
        loginController.setParentController(this);

        this.client = new Client("localhost", 58395);
    }
}
