package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.IngredientStorageHandler;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.ui.SceneLoader;
import com.KUAlchemists.ui.utils.UILoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.util.Stack;

public class IngredientCardController {

    @FXML
    private ImageView ingredientImage;


    private String ingredient;

    private Stage stage;


    @FXML
    public void setIngredientCard(String ingredientName) {
        ingredient = ingredientName;

        // Assuming your image files have a common extension like ".png" or ".jpg"
        String imagePath = "/com.KUAlchemists/images/" + ingredientName + "-ingredient.jpg";
        // Load the image using the class loader to ensure it works regardless of the build type
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
           // Image image = new Image(new File(imagePath2).toURI().toString());
            // Set the image to the ImageView
            ingredientImage.setImage(image);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }


    }

    @FXML
    void sellButtonActivated(ActionEvent event) {
        IngredientStorageHandler.getInstance().handleTransmuteIngredient(ingredient);
        closeWindow();

        //TO-DO: refresh the ingredient storage or close the window

    }

    private void closeWindow() {
        stage = (Stage) ingredientImage.getScene().getWindow();
        stage.close();
    }

    public String getIngredientName() {
        //TO-DO: get ingredient name should be implemented
        return ingredient;
    }
}
