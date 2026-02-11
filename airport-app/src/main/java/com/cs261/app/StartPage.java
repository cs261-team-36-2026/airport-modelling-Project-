package com.cs261.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class StartPage 
{
    public void goToSecondPage(ActionEvent event) throws IOException 
    {

        
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("startNew.fxml"));

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();

        stage.getScene().setRoot(root);   
    }
}
