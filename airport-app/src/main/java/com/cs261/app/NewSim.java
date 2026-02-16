package com.cs261.app;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class NewSim 
{
    @FXML
    private ComboBox<String> statusBox;

    @FXML
    private ComboBox<String> modeBox;

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
    
}
