package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.DebunkTheoryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DebunkController {

    @FXML
    private ImageView blueAspect;

    @FXML
    private ImageView greenAspect;

    @FXML
    private ImageView redAspect;

    @FXML
    private Button debunkButton;

    @FXML
    private ImageView ingredient;

    private String selectedAspect;

    @FXML
    void blueAspectClicked(MouseEvent event) {
        if (blueAspect.getEffect() != null) {
            blueAspect.setEffect(null);
            selectedAspect = null;
            return;
        }
        if(selectedAspect == null) {
            selectedAspect = "blue";
            setEffect(blueAspect);
        }
    }


    private void setEffect(ImageView aspect) {
        Effect glow = new Glow(0.4);
        aspect.setEffect(glow);
    }

    @FXML
    void greenAspectClicked(MouseEvent event) {
        if(greenAspect.getEffect() != null) {
            greenAspect.setEffect(null);
            selectedAspect = null;
            return;
        }
        if(selectedAspect == null) {
            selectedAspect = "green";
            setEffect(greenAspect);
        }
    }

    @FXML
    void redAspectClicked(MouseEvent event) {
        if(redAspect.getEffect() != null) {
            redAspect.setEffect(null);
            selectedAspect = null;
            return;
        }
        if(selectedAspect == null) {
            selectedAspect = "red";
            setEffect(redAspect);
        }
    }


    @FXML
    void debunkButtonClicked(ActionEvent event) {
        if(selectedAspect == null) {
            return;
        }
        String checkedAspect = DebunkTheoryHandler.getInstance().checkAspect(selectedAspect);

        String imagePath = "com.KUAlchemists/images/alchemy/" + checkedAspect;
        Image  newImage = null;
        try {
            newImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));
            // Set the image to the ImageView
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        //if first five character blue it means bluesAspect
        if(checkedAspect.startsWith("blue")) {
            blueAspect.setImage(newImage);
        } else if(checkedAspect.startsWith("green")) {
            greenAspect.setImage(newImage);
        } else if(checkedAspect.startsWith("red")) {
            redAspect.setImage(newImage);
        }

        debunkButton.setDisable(true);
    }

    @FXML
    public void initialize() {
        String  theory = DebunkTheoryHandler.getInstance().getTheory();
        setIngredient(theory);
    }
    public void setIngredient(String ingredientName){
        String imagePath = "com.KUAlchemists/images/" + ingredientName + "-ingredient.jpg";
        try {
            Image newImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));
            // Set the image to the ImageView
            ingredient.setImage(newImage);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }


}