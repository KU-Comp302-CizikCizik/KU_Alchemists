package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DebunkController {

    @FXML
    private ImageView blueAspect;

    @FXML
    private ImageView greenAspect;

    @FXML
    private ImageView redAspect;

    private String selectedAspect;

    @FXML
    void blueAspectClicked(MouseEvent event) {
        if (blueAspect.getEffect() != null) {
            blueAspect.setEffect(null);
            selectedAspect = null;
            return;
        }
        selectedAspect = "blue";
        setEffect(blueAspect);

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
        selectedAspect = "green";
        setEffect(greenAspect);
    }

    @FXML
    void redAspectClicked(MouseEvent event) {
        if(redAspect.getEffect() != null) {
            redAspect.setEffect(null);
            selectedAspect = null;
            return;
        }
        selectedAspect = "red";
        setEffect(redAspect);
    }

}
