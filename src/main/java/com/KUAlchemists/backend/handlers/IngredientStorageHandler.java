package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.IngredientStorageService;

import java.util.ArrayList;

/**
 * This class is responsible for handling the ingredient storage.
 */
public class IngredientStorageHandler {

    /**
     * The singleton instance of IngredientStorageHandler.
     */
    private static IngredientStorageHandler INSTANCE;
    /**
     * The service for handling the ingredient storage.
     */
    private IngredientStorageService service;

    /**
     * Gets the singleton instance of IngredientStorageHandler.
     *
     * @return The singleton instance of IngredientStorageHandler.
     */
    public static IngredientStorageHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IngredientStorageHandler();
        }
        return INSTANCE;
    }



    /**
     * Constructor for IngredientStorageHandler
     */
    private IngredientStorageHandler() {
        this.service = new IngredientStorageService();
    }

    /**
     * Removes an ingredient from the player's storage.
     *
     * @param ingredientName The name of the ingredient to be removed.
     */
    public void handleRemoveIngredient(String ingredientName) {
        Player player = GameEngine.getInstance().getCurrentPlayer();
        try {
            service.removeIngredientFromStorage(player,ingredientName);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
        }
    }

    /**
     * Gets the ingredient by name.
     *
     * @param name The name of the ingredient.
     * @return The ingredient.
     */
    public Ingredient handleGetIngredientByName(String name){
        Player player = GameEngine.getInstance().getCurrentPlayer();
        try {
            return service.getIngredientByName(player,name);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
            return null;
        }
    }


    /**
     * Gets the ingredients in the player's storage.
     *
     * @return The ingredients in the player's storage.
     */
    public ArrayList<String> handleGetIngredientList(Player player) {
        try {
            // Update UI with the ingredient list
            return service.getIngredientsNameList(player);
        } catch (IllegalArgumentException e) {
            return null;
            // Update UI with error message
        }
    }

    /**
     * Transmutes an ingredient into gold.
     * @param ingredientName
     */
    public void handleTransmuteIngredient(String ingredientName) {
        try {
            service.transmuteIngredient(ingredientName);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
        }
    }

    public ArrayList getAllIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Player> players = GameEngine.getInstance().getPlayerList();
        for(Player player: players){
            ingredients.addAll(Board.getInstance().getIngredientStorage(player).getIngredientsList());
        }
        ingredients.addAll(Deck.getInstance().getIngredientsList());
        return ingredients;
    }
}
