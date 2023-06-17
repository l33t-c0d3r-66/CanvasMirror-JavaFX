package com.javafx.cm.controller;

import com.javafx.cm.utils.CanvasMirror;
import com.javafx.cm.utils.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Canvas Mirror");
                alert.setHeaderText("Please Select Valid png Image");
                alert.showAndWait();
            }
        } else {
            // Show Error Messages Here
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Canvas Mirror");
            alert.setHeaderText("Please Select a File");
            alert.showAndWait();
        }
    }

    @FXML
    public void convertImage() {
       String sizeOfCanvasText = sizeOfCanvas.getText();
       Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
       if(filePathTextField.getText().isEmpty()) {
           if(pattern.matcher(sizeOfCanvasText).matches() ) {
               int sizeOfCanvas = Integer.parseInt(sizeOfCanvasText);
               CanvasMirror canvasMirror = new CanvasMirror();

               if(sizeOfCanvas > Constants.MAX_CANVAS_SIZE) {
                   // Show Message
                   String outputImagePath = canvasMirror.canvasMirrorEffect(filePathTextField.getText(),sizeOfCanvas);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Canvas Mirror");
                   alert.setHeaderText("The Image might look different for this size");
                   alert.showAndWait();
                   if(outputImagePath != null) {
                       Image outputImage = new Image(new File(outputImagePath).toURI().toString());
                       outputImageView.setImage(outputImage);
                   }
               } else {
                   // Do Processing
                   String outputImagePath = canvasMirror.canvasMirrorEffect(filePathTextField.getText(),sizeOfCanvas);
                   if(outputImagePath != null) {
                       Image outputImage = new Image(new File(outputImagePath).toURI().toString());
                       outputImageView.setImage(outputImage);
                   }
               }

           } else {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Canvas Mirror");
               alert.setHeaderText("Please Enter Valid Canvas Size");
               alert.showAndWait();
           }
       } else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Canvas Mirror");
           alert.setHeaderText("Please Select a png image");
           alert.showAndWait();
       }

    }
}
