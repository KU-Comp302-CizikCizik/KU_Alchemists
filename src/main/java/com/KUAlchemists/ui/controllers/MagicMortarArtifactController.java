package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.handlers.MagicMortarHandler;
import com.KUAlchemists.ui.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MagicMortarArtifactController {

    @FXML
    private Button button;

    @FXML
    private ImageView ingredient1ImageView;

    @FXML
    private Pane ingredient1Pane;

    @FXML
    private Text ingredient1Text;

    @FXML
    private ImageView ingredient2ImageView;

    @FXML
    private Pane ingredient2Pane;

    @FXML
    private Text ingredient2Text;

    private final Effect glowEffectHover = new Glow(0.5);
    private final Effect glowEffectSelected = new Glow(0.9);
    private final Effect dropShadowEffect = new DropShadow();

    private int selected = -1;

    private ArrayList<String> ingredients;

    public void initialize(){
        ingredients = MagicMortarHandler.getInstance().handleGetIngredientNames();
        String photo1Url = "/com.KUAlchemists/images/" + ingredients.get(0)+"-ingredient.jpg";
        String photo2Url = "/com.KUAlchemists/images/" + ingredients.get(1)+"-ingredient.jpg";

        try{
            Image image = new Image(getClass().getResourceAsStream(photo1Url));
            ingredient1ImageView.setImage(image);
            ingredient1Text.setText(ingredients.get(0).replace("-", " "));
            image = new Image(getClass().getResourceAsStream(photo2Url));
            ingredient2ImageView.setImage(image);
            ingredient2Text.setText(ingredients.get(1).replace("-", " "));
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        button.setDisable(true);


    }

    @FXML
    void actionButton(ActionEvent event) {
        if (selected != -1){
            MagicMortarHandler.getInstance().handleRetainedIngredient(ingredients.get(selected));//ex: root, mushroom
            ((Stage) button.getScene().getWindow()).close(); //Closes the window
            SceneLoader.getInstance().loadMakeExperiment();
        }
    }

    @FXML
    void handleMouseClicked(MouseEvent event) {
        if (event.getSource() == ingredient1Pane){
            ingredient1Pane.setEffect(glowEffectSelected);
            ingredient2Pane.setEffect(dropShadowEffect);
            selected = 0;
            button.setDisable(false);
        }
        else{
            ingredient2Pane.setEffect(glowEffectSelected);
            ingredient1Pane.setEffect(dropShadowEffect);
            selected = 1;
            button.setDisable(false);
        }
    }

    @FXML
    void handleMouseEntered(MouseEvent event) {
        if (event.getSource() == ingredient1Pane && selected != 0){
            ingredient1Pane.setEffect(glowEffectHover);
        }
        else if (event.getSource() == ingredient2Pane && selected != 1){
            ingredient2Pane.setEffect(glowEffectHover);
        }
    }

    @FXML
    void handleMouseExited(MouseEvent event) {
        if (event.getSource() == ingredient1Pane && selected != 0){
            ingredient1Pane.setEffect(dropShadowEffect);
        }
        else if (event.getSource() == ingredient2Pane && selected != 1){
            ingredient2Pane.setEffect(dropShadowEffect);
        }

    }

}
