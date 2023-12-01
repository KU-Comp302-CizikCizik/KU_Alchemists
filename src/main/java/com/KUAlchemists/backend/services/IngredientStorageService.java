package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.IngredientStorage;

/**
 * This class is responsible for storing the ingredients in the game.
 */
public class IngredientStorageService {

    private IngredientStorage ingredientStorage;

    public IngredientStorageService(IngredientStorage storage) {
        this.ingredientStorage = storage;
    }

    public void addIngredientToStorage(Ingredient ingredient) {
        ingredientStorage.addIngredient(ingredient);
    }

    public void removeIngredientFromStorage(Ingredient ingredient) {
        ingredientStorage.removeIngredient(ingredient);
    }

    public Ingredient getIngredientFromStorage(String name) {
        return ingredientStorage.getIngredient(name);
    }

}
