package com.KUAlchemists.backend.models;

import java.util.ArrayList;

/**
 * Holds various types of ingredients.
 *
 */
public class IngredientStorage {
    //OVERVIEW: IngredientStorage is an immutable collection of ingredients that are available in the game.

    /**
     * List of ingredients in the storage.
     */
    private ArrayList<Ingredient> ingredientsList;

    /**
     * Constructor for IngredientStorage
     */
    //constructor
    public IngredientStorage(){
        //@effects: creates an ingredientList
        ingredientsList = new ArrayList<Ingredient>();
    }

    //methods
    /**
     * Adds an ingredient to the storage.
     *
     * @param ingredient The ingredient to be added.
     */
    public void addIngredient(Ingredient ingredient){
        //Partial Procedure
        //@requires: ingredient != null
        //@modifies: this
        //@effects: adds the ingredient to the storage of this
        ingredientsList.add(ingredient);
    }

    /**
     * Removes an ingredient from the storage.
     *
     * @param ingredient The ingredient to be removed.
     */
    public void removeIngredient(Ingredient ingredient){
        //Partial Procedure
        //@requires: ingredient != null
        //@modifies: this
        //@effects: removes the ingredient from the storage of this
        ingredientsList.remove(ingredient);
    }

    /**
     * Removes an ingredient from the storage by name.
     *
     * @param ingredientName The name of the ingredient to be removed.
     */
    public void removeIngredientByName(String ingredientName){
        //Partial procedure
        //@requires: ingredientName != null
        //@modifies: this
        //@effects: removes the ingredient associated with the given name from the storage of this
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
        //Total procedure
        //@effects: returns the ingredients in the storage
        return ingredientsList;
    }

    /**
     * Gets the ingredient by name.
     *
     * @param name The name of the ingredient.
     * @return The ingredient with the given name.
     */
    public Ingredient getIngredient(String name) {
        //Partial procedure
        //@requires: name != null
        //@effects: returns the ingredient with the given name
        for (Ingredient ingredient : ingredientsList) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }



}

