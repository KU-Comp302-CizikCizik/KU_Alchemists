package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.PublishTheoryHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class IngredientCard_PublishController {
    @FXML
    public String ingredient;
    @FXML
    private Button bttn;

    @FXML
    private ImageView ing_image;

    @FXML
    public void setCard(String ingredientName) {
        // Assuming your image files have a common extension like ".png" or ".jpg"
        String imagePath = "/com.KUAlchemists/images/" + ingredientName + ".png";
        // Load the image using the class loader to ensure it works regardless of the build type

        try {
            ingredient=ingredientName;
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            ing_image.setImage(image);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    public void selected(ActionEvent event) {

        PublishTheoryHandler.getInstance().setSelectedIngredientName(ingredient);
        Stage stage = (Stage)ing_image.getScene().getWindow();
        stage.close();

        SceneLoader.getInstance().loadPublishAlchemyPart();
    }
}
