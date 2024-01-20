package com.KUAlchemists.backend.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Holds various types of ingredients.
 *
 */
public class IngredientStorage implements Serializable {

    /**
     * List of ingredients in the storage.
     */
    private ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>();

    /**
     * Constructor for IngredientStorage
     */
    public IngredientStorage(){
    }

    /**
     * Adds an ingredient to the storage.
     *
     * @param ingredient The ingredient to be added.
     */
    public void addIngredient(Ingredient ingredient){
        ingredientsList.add(ingredient);
    }

    /**
     * Removes an ingredient from the storage.
     *
     * @param ingredient The ingredient to be removed.
     */
    public void removeIngredient(Ingredient ingredient){
        ingredientsList.remove(ingredient);
    }

    /**
     * Removes an ingredient from the storage by name.
     *
     * @param ingredientName The name of the ingredient to be removed.
     */
    public void removeIngredientByName(String ingredientName){
        for (Ingredient ingredient : ingredientsList) {
            if (ingredientName.equals(ingredient.getName())) {
                ingredientsList.remove(ingredient);
                return;
            }
        }
    }

    /**
     * Gets the ingredients in the storage.
     *
     * @return The ingredients in the storage.
     */
    public ArrayList<Ingredient> getIngredientsList(){
        return ingredientsList;
    }

    /**
     * Gets the ingredient by name.
     *
     * @param name The name of the ingredient.
     * @return The ingredient with the given name.
     */
    public Ingredient getIngredient(String name) {
        for (Ingredient ingredient : ingredientsList) {
            if (name.equals(ingredient.getName())) {
                return ingredient;
            }
        }
        return null;
    }




}

