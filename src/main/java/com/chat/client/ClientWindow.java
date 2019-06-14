package com.chat.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class ClientWindow extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        ClientWindow.stage = stage;
        Scene scene = new Scene(ClientWindow.loadFXML("fxml/main").load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static FXMLLoader loadFXML(String fxml) {
        return new FXMLLoader(ClientWindow.class.getResource(fxml + ".fxml"));
    }

    public static void setSize(double width, double height) {
        stage.setWidth(width);
        stage.setHeight(height);
    }

    public static Stage getStage() {
        return ClientWindow.stage;
    }

    public static void run() {
        launch();
    }

}