package com.KUAlchemists.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InventoryStorageController {
    @FXML
    private GridPane cardContainer;

    @FXML
    private ScrollPane ingredientScrollPane;

    private List<String> cards;

    @FXML
    private void initialize() {
        cards = getCards();
        int column = 0;
        int row = 0;
        try {
            for(int i = 0; i < cards.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("IngredientCardUI.fxml"));
                VBox cardBox = fxmlLoader.load();
                IngredientCardController controller = fxmlLoader.getController();
                controller.setIngredientCard(cards.get(i));

                //TO-DO: row colum should be checked
                if (column == 3) {
                    column = 0;
                    row++;
                }
                cardContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private List<String> getCards() {
        List<String> ls = new ArrayList<>();
        ls.add("mushroom-ingredient.jpg");
        ls.add("plant-ingredient.jpg");
        ls.add("mushroom-ingredient.jpg");
        ls.add("plant-ingredient.jpg");
        ls.add("mushroom-ingredient.jpg");
        ls.add("plant-ingredient.jpg");
        ls.add("mushroom-ingredient.jpg");
        ls.add("plant-ingredient.jpg");
        return ls;
    }
}
