package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PublishTheoryAlchemySelectController {

    @FXML
    private ImageView blue_big_n;

    @FXML
    private ImageView blue_big_p;

    @FXML
    private ImageView blue_small_n;

    @FXML
    private ImageView blue_small_p;

    @FXML
    private ImageView green_big_n;

    @FXML
    private ImageView green_big_p;

    @FXML
    private ImageView green_small_n;

    @FXML
    private ImageView green_small_p;

    @FXML
    private ImageView red_big_n;

    @FXML
    private ImageView red_big_p;

    @FXML
    private ImageView red_small_n;

    @FXML
    private ImageView red_small_p;

    @FXML
    void alchemyClicked(MouseEvent event) {
        System.out.println( IngredientCard_PublishController.ingredient);
        System.out.println(event.toString().substring(34,event.toString().indexOf(",")));

    }

}
