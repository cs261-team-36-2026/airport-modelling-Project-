package com.cs261.app;


import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class RunwayInputController 
{
    
    @FXML
    private Label runwayLabel;

    public void setRunwayTitle(String text) 
    {
        runwayLabel.setText(text);
    }
    
}
