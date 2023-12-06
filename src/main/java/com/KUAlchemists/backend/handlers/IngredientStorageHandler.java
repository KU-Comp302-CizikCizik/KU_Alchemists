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
        try {
            service.removeIngredientFromStorage(GameEngine.getInstance().getCurrentPlayer(),ingredientName);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
        }
    }

    public void handleAddIngredient(String ingredientName){
        try {
            service.addIngredientToStorage(ingredientName);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
        }
    }

    public Ingredient handleGetIngredientByName(Player player, String name){
        try {
            return service.getIngredientByName(player,name);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
            return null;
        }
    }

    public String handleGetIngredientDescription(String ingredientName) {
        try {
            return service.getIngredientDescription(ingredientName);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            return "Ingredient not found";
            // Update UI with error message
        }

    }

    public ArrayList<String> handleGetIngredientList() {
        try {
            // Update UI with the ingredient list
            return service.getIngredientsNameList();
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
