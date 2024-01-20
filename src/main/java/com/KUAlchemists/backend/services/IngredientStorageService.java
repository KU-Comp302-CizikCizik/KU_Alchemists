package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.IngredientType;
import com.KUAlchemists.backend.models.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for storing the ingredients in the game.
 */
public class IngredientStorageService {

    /**
     * Constructor for IngredientStorageService
     */
    public IngredientStorageService() {
    }

    /**
     * Removes an ingredient from the player's storage.
     *
     * @param player The player whose storage the ingredient is to be removed from.
     * @param ingredientName The name of the ingredient to be removed.
     */
    public void removeIngredientFromStorage(Player player, String ingredientName) {
        Board.getInstance().getIngredientStorage(player).removeIngredientByName(ingredientName);
    }

    /**
     * Gets the description of an ingredient.
     *
     * @param player The player whose storage the ingredient is in.
     * @param name The name of the ingredient.
     * @return The description of the ingredient.
     */
    public String getIngredientDescription(Player player, String name) {
        IngredientStorage ingredientStorage = Board.getInstance().getIngredientStorage(player);
        return ingredientStorage.getIngredient(name).getDescription();
    }

    /**
     * Gets the ingredients in the player's storage.
     *
     * @param player The player whose storage the ingredients are in.
     * @return The ingredients in the player's storage.
     */
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

    /**
     * Transmutes an ingredient into gold.
     * @param ingredientName
     */
    public void transmuteIngredient(String ingredientName) {
        IngredientStorage ingredientStorage = Board.getInstance().getIngredientStorage(GameEngine.getInstance().getCurrentPlayer());
        Player player = GameEngine.getInstance().getCurrentPlayer();
        ArrayList<Ingredient> ingredientsList = ingredientStorage.getIngredientsList();
        for (Ingredient ingredient : ingredientsList) {
            String[] words= ingredient.getName().split(" ");
            StringBuilder result = new StringBuilder();

            for (String word : words) {
                if (!word.isEmpty()) {
                    result.append(Character.toLowerCase(word.charAt(0))).append(word.substring(1)).append(" ");
                }
            }
            String ing=result.toString().trim();

            if (ing.equals(ingredientName)) {
                ingredientStorage.removeIngredient(ingredient);
                Deck.getInstance().addIngredient(ingredient);
                player.setGold(player.getGold() + 1); //
                return;
            }
        }
    }

    /**
     * Gets an ingredient by its name.
     *
     * @param currentPlayer The player whose storage the ingredient is in.
     * @param name The name of the ingredient.
     * @return The ingredient.
     */
    public Ingredient getIngredientByName(Player currentPlayer,String name) {
        IngredientStorage ingredientStorage = Board.getInstance().getIngredientStorage(currentPlayer);
        return ingredientStorage.getIngredient(name);
    }

    /**
     * Adds an ingredient to the player's storage.
     *
     * @param player The player whose storage the ingredient is to be added to.
     * @param ingredientName The name of the ingredient to be added.
     */
    public void addIngredientToStorage(Player player,String ingredientName) {
        Ingredient ingredient = new Ingredient(ingredientName, 0, "A common ingredient...", IngredientType.HERB);
        Board.getInstance().getIngredientStorage(player).addIngredient(ingredient);
    }
}
