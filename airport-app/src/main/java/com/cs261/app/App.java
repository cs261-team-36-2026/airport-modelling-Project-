package com.cs261.app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage stage) throws Exception 
    {
        
        FXMLLoader loader = new FXMLLoader(
                App.class.getResource("/com/cs261/app/start.fxml"));

        Scene scene = new Scene(loader.load());

        stage.setTitle("Airport App");
        stage.setScene(scene);
        stage.show();
    }


    {
                    
        // launch ui
        launch(args);

    }
}