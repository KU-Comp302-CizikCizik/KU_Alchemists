package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.IngredientStorage;
import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is responsible for storing the ingredients in the game.
 */
public class IngredientStorageService {

    private IngredientStorage ingredientStorage;

    private Player player;

    public IngredientStorageService(Player player) {
        this.player = player;
        this.ingredientStorage = Board.getIngredientStorage(player);
    }

    public void removeIngredientFromStorage(String ingredientName) {
        ArrayList<Ingredient> ingredientsList = ingredientStorage.getIngredientsList();
        for (Ingredient ingredient : ingredientsList) {
            if (ingredient.getName().equals(ingredientName)) {
                ingredientStorage.removeIngredient(ingredient);
            }
        }
    }

    public String getIngredientDescription(String name) {
        return ingredientStorage.getIngredient(name).getDescription();
    }

    public ArrayList<String> getIngredientsList() {
        ArrayList<String> ingredientsList = new ArrayList<>();
        // Add ingredientsNames to the ingredientsList
        for (Ingredient ingredient : ingredientStorage.getIngredientsList()) {
            String ingredientName = ingredient.getName();
            ingredientsList.add(ingredientName);
        }
        return ingredientsList;
    }

    public void transmuteIngredient(String ingredientName) {
        ArrayList<Ingredient> ingredientsList = ingredientStorage.getIngredientsList();
        for (Ingredient ingredient : ingredientsList) {
            if (ingredient.getName().equals(ingredientName)) {
                ingredientStorage.removeIngredient(ingredient);
                player.setGold(player.getGold() + 1); //
            }
        }
    }



}
