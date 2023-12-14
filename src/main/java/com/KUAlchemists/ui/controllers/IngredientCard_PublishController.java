package com.KUAlchemists.ui.controllers;
import com.KUAlchemists.ui.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;import javafx.event.ActionEvent;
import javafx.scene.control.Button;
public class IngredientCard_PublishController {
    @FXML

    private String ingredient;
    @FXML
    private Button bttn;

    @FXML
    private ImageView ing_image;

    @FXML
    public void setCard(String ingredientName) {
        System.out.println("girdi");
        // Assuming your image files have a common extension like ".png" or ".jpg"
        String imagePath = "com.KUAlchemists/images/" + ingredientName + ".png";
        // Load the image using the class loader to ensure it works regardless of the build type

        try {
            ingredient=ingredientName;
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            ing_image.setImage(image);
            System.out.println("başardı");
        } catch (Exception e) {
            System.out.println("başaraöado");
            System.err.println(e.getMessage());
        }
    }
    @FXML
    public void selected(ActionEvent event) {
        System.out.println(ingredient);
        SceneLoader.getInstance().loadPublicationTrack();



    }
}
