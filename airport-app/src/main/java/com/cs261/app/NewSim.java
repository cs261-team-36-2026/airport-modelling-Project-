package com.cs261.app;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewSim 
{
    int runways = 1;
    int maxRun = 10;



    @FXML
    private ComboBox<String> modeBox;

    @FXML
    private ComboBox<String> statusBox;
    //<?xml version="1.0" encoding="UTF-8"?>


    @FXML
    public void initialize() {
        statusBox.getItems().addAll(
            "Available",
            "Runway Inspection",
            "Snow Clearance",
            "Equipment Failure"
        );
        modeBox.getItems().addAll(
            "Landing",
            "Takeoff",
            "Mixed"
        );

    }
    
    public void goBack(ActionEvent event) throws IOException 
    {

        Parent root = FXMLLoader.load(
        getClass().getResource("start.fxml"));

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();

        stage.getScene().setRoot(root);
    }   

    @FXML
    private VBox runwayBox;

    @FXML
    private void addSection() 
    {

        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/cs261/app/RunwayInput.fxml")
            );

            VBox newSection = loader.load();
            runwayBox.getChildren().add(newSection);

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
