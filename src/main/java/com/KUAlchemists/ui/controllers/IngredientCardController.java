package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Path;

public class IngredientCardController {

    @FXML
    private ImageView ingredientImage;

    @FXML
    private Button ingredientCardButton;

    @FXML
    public void setIngredientCard(String ingredientName) {
        // Assuming your image files have a common extension like ".png" or ".jpg"
        String imagePath = "com.KUAlchemists/images/" + ingredientName;

        // Load the image using the class loader to ensure it works regardless of the build type

        Image image = new Image(getClass().getClassLoader().getResourceAsStream( imagePath));

        // Set the image to the ImageView
        ingredientImage.setImage(image);
    }
}
