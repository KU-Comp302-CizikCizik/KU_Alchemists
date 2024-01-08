package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.handlers.IngredientStorageHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class PublishTheoryController {

    @FXML
    private GridPane box;
    public boolean validateIngredient(String ingredient, String ing2){
        return ing2.equals(ingredient);
    }

    @FXML
    private void initialize() {

        int column = 0;
        int row = 0;
        try {
            ArrayList<String> ingredients = new ArrayList<>();
            ingredients.add("birdfeet-ingredient-square");
            ingredients.add("feather-ingredient-square");
            ingredients.add("flower-ingredient-square");
            ingredients.add("frog-ingredient-square");
            ingredients.add("mushroom-ingredient-square");
            ingredients.add("plant-ingredient-square");
            ingredients.add("root-ingredient-square");
            ingredients.add("scorpion-ingredient-square");

            for(int i = 0; i < ingredients.size(); i++) {
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("IngredientCard_PublishUI.fxml"));
               VBox cardBox = fxmlLoader.load();
               IngredientCard_PublishController controller = fxmlLoader.getController();
                controller.setCard(ingredients.get(i));

                //TO-DO: row colum should be checked
                if (column == 4) {
                    column = 0;
                    row++;
                }
               box.add(cardBox, column++, row);
               GridPane.setMargin(cardBox, new Insets(2,2,2,2));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
