package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.IngredientType;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.IngredientStorage;
import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for storing the ingredients in the game.
 */
public class IngredientStorageService {


    public IngredientStorageService() {

    }

    public void removeIngredientFromStorage(Player player, String ingredientName) {
        Board.getInstance().getIngredientStorage(player).removeIngredientByName(ingredientName);
    }



    public String getIngredientDescription(Player player, String name) {
        IngredientStorage ingredientStorage = Board.getInstance().getIngredientStorage(player);
        return ingredientStorage.getIngredient(name).getDescription();
    }

    public ArrayList<String> getIngredientList(){
        ArrayList<String> ingredientsList = new ArrayList<>();
        // Add ingredientsNames to the ingredientsList
        IngredientStorage ingredientStorage = Board.getInstance().getIngredientStorage(GameEngine.getInstance().getCurrentPlayer());
        for (Ingredient ingredient : ingredientStorage.getIngredientsList()) {
            String ingredientName = ingredient.getName();
            ingredientsList.add(ingredientName);
        }
        return ingredientsList;
    }

    public ArrayList<String> getIngredientsNameList(Player player) {
        ArrayList<String> ingredientsList = new ArrayList<>();
        // Add ingredientsNames to the ingredientsList
        IngredientStorage ingredientStorage = Board.getInstance().getIngredientStorage(player);
        for (Ingredient ingredient : ingredientStorage.getIngredientsList()) {
            String ingredientName = ingredient.getName();
            ingredientsList.add(ingredientName);
        }
        return ingredientsList;
    }

    public void transmuteIngredient(String ingredientName) {
        IngredientStorage ingredientStorage = Board.getInstance().getIngredientStorage(GameEngine.getInstance().getCurrentPlayer());
        Player player = GameEngine.getInstance().getCurrentPlayer();
        ArrayList<Ingredient> ingredientsList = ingredientStorage.getIngredientsList();
        for (Ingredient ingredient : ingredientsList) {
            if (ingredient.getName().equals(ingredientName)) {
                ingredientStorage.removeIngredient(ingredient);
                player.setGold(player.getGold() + 1); //
                return;
            }
        }
    }


    public Ingredient getIngredientByName(Player currentPlayer,String name) {
        IngredientStorage ingredientStorage = Board.getInstance().getIngredientStorage(currentPlayer);
        return ingredientStorage.getIngredient(name);
    }

    public void addIngredientToStorage(Player player,String ingredientName) {
        Ingredient ingredient = new Ingredient(ingredientName, 0, "A common ingredient...", IngredientType.HERB);
        Board.getInstance().getIngredientStorage(player).addIngredient(ingredient);

    }
}
