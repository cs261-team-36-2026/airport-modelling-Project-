package com.cs261.app;

import javafx.application.Application;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Map;
import java.util.HashMap;
import javafx.collections.FXCollections;

public class App extends Application {
    Label label;

    Map<Integer, TestObj> all = new HashMap<>();
    ObservableMap<Integer, TestObj> obmap;
    SimpleMapProperty<Integer, TestObj> obmapprop;

    public void start(Stage stage) {
        for (int i = 0; i < 15; i++){
            all.put(i, new TestObj(i, "Bea"));
        }

        obmap = FXCollections.observableMap(all);
        obmapprop = new SimpleMapProperty<>(obmap);

        label = new Label("Hello");

        TextField textField2 = new TextField();
        textField2.setPromptText("name");

        Button setBtn = new Button("Set");

        setBtn.setOnAction(e -> {
            all.replace(7, 
            new TestObj(7, textField2.getText()));
        });


        Button startBtn = new Button("Enter");

        startBtn.setOnAction(e -> {
            startThread("A", "B", all);
        });

        VBox root = new VBox(10, label, textField2, setBtn, startBtn);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 300, 150);
        stage.setTitle("Simple JavaFX App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {            
        // launch ui
        launch(args);

    }

    public void startThread(String input1, String input2, Map<Integer, TestObj> list){
        // task that will run in thread

        TestThread loopy = new TestThread(input1, input2, list, 
            (result) -> {
                label.setText(result);
            }
        );

        loopy.getPropertyMap().bind(obmapprop);

        Thread ourThread = new Thread(loopy);
        ourThread.setDaemon(true); // runs in background
        ourThread.start();
    }
}