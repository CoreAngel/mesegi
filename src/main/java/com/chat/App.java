package com.chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;



public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("fxml/main").load());
        App.stage = stage;
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    public static FXMLLoader loadFXML(String fxml) {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    public static void setSize(double width, double height) {
        stage.setWidth(width);
        stage.setHeight(height);
    }

    public static Stage getStage() {
        return App.stage;
    }

    public static void main(String[] args) {
        launch();
    }

}