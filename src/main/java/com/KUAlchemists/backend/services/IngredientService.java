package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Ingredient;
import java.util.List;
import java.util.Optional;
public class IngredientService {
    private List<Ingredient> ingredients; // This should be replaced with a real data source

    public IngredientService(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Finds an Ingredient by its name.
     *
     * @param ingredientName The name of the ingredient to find.
     * @return The Ingredient object if found, or null if not found.
     */
    public Ingredient findIngredientByName(String ingredientName) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(ingredientName))
                .findFirst()
                .orElse(null); // Returns null if ingredient is not found
    }
}
