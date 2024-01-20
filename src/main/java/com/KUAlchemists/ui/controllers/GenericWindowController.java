package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GenericWindowController {

    @FXML
    private ImageView notifacationImage;

    @FXML
    private TextArea description;


    @FXML
    public void setTextField(String text) {
        description.setText(text);
    }

    @FXML
    public void setImage(String imageFile) {
        String imagePath = "/com.KUAlchemists/images/" + imageFile;
        try {
            Image newImage = new Image(getClass().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            notifacationImage.setImage(newImage);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
