package com.cs261.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class StartNewPage 
{
    public void goBack(ActionEvent event) throws IOException 
    {

        Parent root = FXMLLoader.load(
        getClass().getResource("start.fxml"));

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();

        stage.getScene().setRoot(root);
    }   
}
