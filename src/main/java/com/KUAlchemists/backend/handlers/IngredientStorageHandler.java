package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.IngredientStorage;
import com.KUAlchemists.backend.services.IngredientStorageService;

import java.util.ArrayList;
import java.util.HashMap;

public class IngredientStorageHandler {

    private IngredientStorageService service;

    public IngredientStorageHandler() {
        this.service = new IngredientStorageService(GameEngine.getCurrentPlayer());
    }

    public void handleRemoveIngredient(String ingredientName) {
        try {
            service.removeIngredientFromStorage(ingredientName);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
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
            return service.getIngredientsList();
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
