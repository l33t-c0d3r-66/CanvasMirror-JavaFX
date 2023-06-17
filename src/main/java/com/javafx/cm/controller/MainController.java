package com.javafx.cm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.File;

public class MainController {

    @FXML
    private TextField filePathTextField;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;


    @FXML
    public void openFileDialog() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        if(file != null) {

        } else {
            // Show Error Messages Here
        }
    }

    @FXML
    public void convertImage() {

    }
}
