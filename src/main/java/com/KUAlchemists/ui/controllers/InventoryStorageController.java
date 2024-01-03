package com.KUAlchemists.ui.controllers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.handlers.IngredientStorageHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class InventoryStorageController {
    @FXML
    private GridPane cardContainer;

    @FXML
    private AnchorPane anchorPane;
    Stage stage;

    public static InventoryStorageController getInstance() {
        return new InventoryStorageController();
    }


    @FXML
    private void initialize() {

        List<String> cards = IngredientStorageHandler.getInstance().handleGetIngredientList(GameEngine.getInstance().getCurrentPlayer());
        int column = 0;
        int row = 0;
        try {
            for(int i = 0; i < cards.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("IngredientCardUI.fxml"));
                VBox cardBox = fxmlLoader.load();
                IngredientCardController controller = fxmlLoader.getController();
                controller.setIngredientCard(cards.get(i));

                //TO-DO: row colum should be checked
                if (column == 4) {
                    column = 0;
                    row++;
                }
                cardContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(5,10,5,10));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
