package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.services.IngredientStorageService;

public class IngredientStorageHandler {

    private IngredientStorageService service;

    public IngredientStorageHandler() {
        this.service = new IngredientStorageService(GameEngine.getCurrentPlayer());
    }

    public void handleAddIngredient(Ingredient ingredient) {
        service.addIngredientToStorage(ingredient);
        // Update UI or send response to UI indicating success/failure
    }

    public void handleRemoveIngredient(Ingredient ingredient) {
        service.removeIngredientFromStorage(ingredient);
        // Update UI or send response to UI indicating success/failure
    }

    public void handleGetIngredient(String name) {
        try {
            Ingredient ingredient = service.getIngredientFromStorage(name);
            // Update UI with the ingredient details
        } catch (IllegalArgumentException e) {
            // Update UI with error message
        }
    }
}
