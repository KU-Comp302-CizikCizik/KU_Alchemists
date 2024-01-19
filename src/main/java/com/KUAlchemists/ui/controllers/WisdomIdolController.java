package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.WisdomIdolHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class WisdomIdolController {

    @FXML
    private ImageView ingredientImage;

    @FXML
    void initialize() {
        String ingredientnName = WisdomIdolHandler.getInstance().getDebunkIngredientName();

        // Assuming your image files have a common extension like ".png" or ".jpg"
        String imagePath = "/com.KUAlchemists/images/" + ingredientnName + "-ingredient-square.png";
        // Load the image using the class loader to ensure it works regardless of the build type
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            ingredientImage.setImage(image);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    @FXML
    void keepArtifactClicked(ActionEvent event) {
        closeWindow();
    }
    @FXML
    void useArtifactClicked(ActionEvent event) {
        WisdomIdolHandler.getInstance().useWisdomIdol();
        closeWindow();

    }
    private void closeWindow() {
        Stage stage = (Stage) ingredientImage.getScene().getWindow();
        stage.close();
    }
}
