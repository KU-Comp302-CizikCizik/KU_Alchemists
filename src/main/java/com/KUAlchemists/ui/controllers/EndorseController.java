package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class EndorseController {

    @FXML
    private ImageView alchemy;

    @FXML
    private Text endorseButton;

    @FXML
    private ImageView seal1;

    @FXML
    private ImageView seal2;

    @FXML
    private ImageView seal3;

    @FXML
    private ImageView selfSeal1;

    @FXML
    private ImageView selfSeal2;

    @FXML
    private ImageView selfSeal3;

    @FXML
    private ImageView selfSeal4;

    @FXML
    private ImageView selfSeal5;

    @FXML
    private ImageView theory;

    @FXML
    private void initialize() {
        int player = GameEngine.getInstance().getCurrentPlayerIndex();
        if (player == 0) {
            setSeals("red");
        } else if (player == 1) {
            setSeals("blue");
        } else if (player == 2) {
            setSeals("green");
        } else if (player == 3) {
            setSeals("yellow");
        }
    }

    private void setSeals(String colour) {
        selfSeal1.setImage(getImage("blue" + "SealGS" ));
        selfSeal2.setImage(getImage(colour + "SealSS" ));
        selfSeal3.setImage(getImage(colour + "SealRQ" ));
        selfSeal4.setImage(getImage(colour + "SealBQ" ));
        selfSeal5.setImage(getImage(colour + "SealGQ" ));
    }

    private Image getImage(String s) {
        String imagePath = "/com.KUAlchemists/images/" + s + ".png";
        Image newImage = new Image(getClass().getResourceAsStream(imagePath));
        return newImage;
    }

    @FXML
    void endorseClicked(MouseEvent event) {

    }

    @FXML
    void seal1Clicked(MouseEvent event) {

    }

    @FXML
    void seal2Clicked(MouseEvent event) {

    }

    @FXML
    void seal3Clicked(MouseEvent event) {

    }

    @FXML
    void seal4Clicked(MouseEvent event) {

    }

    @FXML
    void seal5Clicked(MouseEvent event) {

    }

}
