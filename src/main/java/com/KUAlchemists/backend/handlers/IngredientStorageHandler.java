package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.IngredientStorageService;

import java.util.ArrayList;

public class IngredientStorageHandler {


    private static IngredientStorageHandler INSTANCE;

    private IngredientStorageService service;


    public static IngredientStorageHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IngredientStorageHandler();
        }
        return INSTANCE;
    }

    private IngredientStorageHandler() {
        this.service = new IngredientStorageService();
    }

    public void handleRemoveIngredient(String ingredientName) {
        Player player = GameEngine.getInstance().getCurrentPlayer();
        try {
            service.removeIngredientFromStorage(player,ingredientName);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
        }
    }

    public void handleAddIngredient(String ingredientName){
        Player player = GameEngine.getInstance().getCurrentPlayer();
        try {
            service.addIngredientToStorage(player, ingredientName);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
        }
    }

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

    public String handleGetIngredientDescription(String ingredientName) {
        Player player = GameEngine.getInstance().getCurrentPlayer();
        try {
            return service.getIngredientDescription(player, ingredientName);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            return "Ingredient not found";
            // Update UI with error message
        }

    }

    public ArrayList<String> handleGetIngredientList(Player player) {
        try {
            // Update UI with the ingredient list
            return service.getIngredientsNameList(player);
        } catch (IllegalArgumentException e) {
            return null;
            // Update UI with error message
        }
    }

    public void handleTransmuteIngredient(String ingredientName) {
        try {
            service.transmuteIngredient(ingredientName);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
        }
    }
}
