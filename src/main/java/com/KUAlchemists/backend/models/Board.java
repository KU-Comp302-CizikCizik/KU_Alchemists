package com.KUAlchemists.backend.models;

public class Board {

    private IngredientStorage ingredientStorage;

    public Board(IngredientStorage ingredientStorage) {
        this.ingredientStorage = ingredientStorage;
    }

    public IngredientStorage getIngredientStorage() {
        return ingredientStorage;
    }

}

