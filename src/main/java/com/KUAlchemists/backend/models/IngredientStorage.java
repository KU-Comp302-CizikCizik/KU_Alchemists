package com.KUAlchemists.backend.models;

import java.util.ArrayList;

/**
 * Holds various types of ingredients.
 *
 */
public class IngredientStorage {

    private ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>();

    public IngredientStorage(){

    }

    public void addIngredient(Ingredient ingredient){
        ingredientsList.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredientsList.remove(ingredient);
    }

    public ArrayList<Ingredient> getIngredientsList(){
        return ingredientsList;
    }

    public Ingredient getIngredient(String name) {
        for (Ingredient ingredient : ingredientsList) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }



}
