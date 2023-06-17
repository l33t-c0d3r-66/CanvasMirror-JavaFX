package com.javafx.cm.controller;

import com.javafx.cm.utils.CanvasMirror;
import com.javafx.cm.utils.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private Button convertImageBtn;

    private boolean isSaveAllowed = false;



    @FXML
    public void openFileDialog() {
        this.convertImageBtn.setText("Transform Image");
        this.isSaveAllowed = false;
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        if(file != null) {
            if(file.getAbsolutePath().endsWith(".png")) {
                this.filePathTextField.setText(file.getAbsolutePath());
                this.filePathTextField.setDisable(true);
                Image image = new Image(file.toURI().toString());
                this.inputImageView.setImage(image);
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
       String sizeOfCanvasText = this.sizeOfCanvas.getText();
       Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        CanvasMirror canvasMirror = new CanvasMirror();
        if(!this.filePathTextField.getText().isEmpty()) {
            if (this.isSaveAllowed) {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Save File");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"));
                File file = chooser.showSaveDialog(new Stage());
                if (file != null) {
                    File sourceFile = new File(this.filePathTextField.getText());
                    Path destinationPath = Paths.get(file.getAbsolutePath());
                    boolean result = canvasMirror.moveFileToAnotherDestination(sourceFile, destinationPath);
                    if(result) {
                        this.isSaveAllowed = false;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Canvas Mirror");
                        alert.setHeaderText("File Saved Successfully");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Canvas Mirror");
                        alert.setHeaderText("Failed to Save the File");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Canvas Mirror");
                    alert.setHeaderText("The File is Empty");
                    alert.showAndWait();
                }


            } else {

                if (pattern.matcher(sizeOfCanvasText).matches()) {
                    int sizeOfCanvas = Integer.parseInt(sizeOfCanvasText);


                    if (sizeOfCanvas > Constants.MAX_CANVAS_SIZE) {
                        // Show Message
                        String outputImagePath = canvasMirror.canvasMirrorEffect(this.filePathTextField.getText(), sizeOfCanvas);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Canvas Mirror");
                        alert.setHeaderText("The Image might look different for this size");
                        alert.showAndWait();
                        if (outputImagePath != null) {
                            Image outputImage = new Image(new File(outputImagePath).toURI().toString());
                            this.outputImageView.setImage(outputImage);
                            this.convertImageBtn.setText("Save Output Image");

                            this.isSaveAllowed = true;

                        }
                    } else {
                        // Do Processing
                        String outputImagePath = canvasMirror.canvasMirrorEffect(filePathTextField.getText(), sizeOfCanvas);
                        if (outputImagePath != null) {
                            Image outputImage = new Image(new File(outputImagePath).toURI().toString());
                            this.outputImageView.setImage(outputImage);
                            this.convertImageBtn.setText("Save Output Image");

                            this.isSaveAllowed = true;
                        }
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Canvas Mirror");
                    alert.setHeaderText("Please Enter Valid Canvas Size");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Canvas Mirror");
            alert.setHeaderText("Please Select a png image");
            alert.showAndWait();
        }
    }
}
