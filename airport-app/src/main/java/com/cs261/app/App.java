package com.cs261.app;
import javafx.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage stage){
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 600, 400);
        Label label = new Label("Welcome to the Airport App");
        root.getChildren().add(label);
        stage.setTitle("Airport App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {            
        // launch ui
        launch(args);

    }
}