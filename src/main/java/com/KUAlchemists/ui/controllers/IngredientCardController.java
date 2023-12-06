package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.IngredientStorageHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IngredientCardController {

    @FXML
    private ImageView ingredientImage;


    private String ingredient;

    @FXML
    public void setIngredientCard(String ingredientName) {
        ingredient = ingredientName;

        // Assuming your image files have a common extension like ".png" or ".jpg"
        String imagePath = "com.KUAlchemists/images/" + ingredientName + "-ingredient.jpg";
        // Load the image using the class loader to ensure it works regardless of the build type

        try {
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            ingredientImage.setImage(image);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }


    }

    @FXML
    void sellButtonActivated(ActionEvent event) {
        //TO-DO: sell button should be implemented
        IngredientStorageHandler.getInstance().handleTransmuteIngredient(ingredient);

        //TO-DO: refresh the ingredient storage or close the window

    }

    public String getIngredientName() {
        //TO-DO: get ingredient name should be implemented
        return ingredient;
    }
}
