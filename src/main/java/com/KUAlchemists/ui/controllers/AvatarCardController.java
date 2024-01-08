package com.KUAlchemists.ui.controllers;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class AvatarCardController {

    @FXML
    private Text actionPoint;

    @FXML
    private Text goldPoint;

    @FXML
    private Text reputationPoint;

    @FXML
    private ImageView avatarImage;


    public void setActionPoint(int actionPoint) {
        this.actionPoint.setText(String.valueOf(actionPoint));
    }

    public void setGoldPoint(int goldPoint) {
        this.goldPoint.setText(String.valueOf(goldPoint));
    }

    public void setReputationPoint(int reputationPoint) {
        this.reputationPoint.setText(String.valueOf(reputationPoint));
    }

    @FXML
    public void setAvatarCardImage(String avatarCardName) {
        // Assuming your image files have a common extension like ".png" or ".jpg"
        String imagePath = "/com.KUAlchemists/images/profile_p/" + avatarCardName + "_circle.png";
        // Load the image using the class loader to ensure it works regardless of the build type
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            avatarImage.setImage(image);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }


    }
}

