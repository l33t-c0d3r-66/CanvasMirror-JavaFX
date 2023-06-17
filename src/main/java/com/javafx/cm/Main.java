package com.javafx.cm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) throws Exception{
        launch(args);
    }

    public void start(Stage stage) throws Exception{
        String fxmlFile = "/fxml/Main.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        Scene scene = new Scene(loader.load());
        stage.setTitle("Canvas Mirror");
        stage.setScene(scene);
        stage.setMinWidth(1130);
        stage.setMinHeight(670);
        stage.setMaxWidth(1200);
        stage.setMaxHeight(700);
        stage.show();
   }

}
