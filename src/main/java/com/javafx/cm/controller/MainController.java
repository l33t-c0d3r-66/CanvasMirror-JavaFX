package com.javafx.cm.controller;

import com.javafx.cm.utils.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.regex.Pattern;

public class MainController {

    @FXML
    private TextField filePathTextField;
    @FXML
    private TextField sizeOfCanvas;
    @FXML
    private ImageView inputImageView;
    @FXML
    private ImageView outputImageView;



    @FXML
    public void openFileDialog() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        if(file != null) {
            if(file.getAbsolutePath().endsWith(".png")) {
                filePathTextField.setText(file.getAbsolutePath());
                filePathTextField.setDisable(true);
                Image image = new Image(file.toURI().toString());
                inputImageView.setImage(image);
            } else {
              // Show Error Message Here
                System.out.println("Not Image");
            }
        } else {
            // Show Error Messages Here
            System.out.println("Null File");
        }
    }

    @FXML
    public void convertImage() {
       String sizeOfCanvasText = sizeOfCanvas.getText();
       Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
       if(pattern.matcher(sizeOfCanvasText).matches() ) {
            int sizeOfCanvas = Integer.parseInt(sizeOfCanvasText);

            if(sizeOfCanvas > Constants.MAX_CANVAS_SIZE) {
                // Show Message
            } else {
                // Do Processing
            }

       } else {
           // Error Message Not a Number
           System.out.println("Not a Number");
       }
    }
}
