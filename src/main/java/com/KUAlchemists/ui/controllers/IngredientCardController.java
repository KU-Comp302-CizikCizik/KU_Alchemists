package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.net.URL;

public class IngredientCardController {
    @FXML
    private ImageView ingredientCard;

    @FXML
    private JButton ingredientCardButton;

    @FXML
    public void setIngredientCard(String ingredientName){
        URL url = getClass().getResource("/images/ingredientCards/" + ingredientName + ".png");
        ImageIcon icon = new ImageIcon(url);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(ingredientCard.getWidth(), ingredientCard.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        ingredientCard.setIcon(icon);
    }



}
