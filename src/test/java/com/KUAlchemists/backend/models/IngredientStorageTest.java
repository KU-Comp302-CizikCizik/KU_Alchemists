package com.KUAlchemists.backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class IngredientStorageTest {

    IngredientStorage ingredientStorage;

    @BeforeEach
    void setUp() {
        ingredientStorage = new IngredientStorage();
    }

    @Test
    void addIngredient() {
        Ingredient ingredient = new Ingredient("test");
        ingredientStorage.addIngredient(ingredient);
        assertTrue(ingredientStorage.repOk());
        assertEquals(ingredientStorage.getIngredient("test"), ingredient);
        assertTrue(ingredientStorage.isIn("test"));
    }

    @Test
    void removeIngredient() {
        Ingredient ingredient = new Ingredient("test");
        ingredientStorage.addIngredient(ingredient);
        ingredientStorage.removeIngredient(ingredient);
        assertTrue(ingredientStorage.repOk());
        assertEquals(ingredientStorage.getIngredient("test"), null);
        assertFalse(ingredientStorage.isIn("test"));
    }

    @Test
    void removeIngredientByName() {
        Ingredient ingredient = new Ingredient("test");
        ingredientStorage.addIngredient(ingredient);
        ingredientStorage.removeIngredientByName("test");
        assertTrue(ingredientStorage.repOk());
        assertFalse(ingredientStorage.isIn("test"));
        assertEquals(ingredientStorage.getIngredient("test"), null);
    }

    @Test
    void getIngredientsList() {
        Vector<Ingredient> ingredients = new Vector<>();
        Ingredient ingredient = new Ingredient("test");
        ingredients.add(ingredient);
        ingredientStorage.addIngredient(ingredient);
        assertTrue(ingredientStorage.repOk());
        assertEquals(ingredientStorage.getIngredientsList(), ingredients);
    }

    @Test
    void getIngredient() {
        Ingredient ingredient = new Ingredient("test");
        ingredientStorage.addIngredient(ingredient);
        assertTrue(ingredientStorage.repOk());
        assertEquals(ingredientStorage.getIngredient("test"), ingredient);
    }

    @Test
    void nullCase(){
        assertTrue(ingredientStorage.repOk());
        assertFalse(ingredientStorage.isIn(null));
        assertEquals(ingredientStorage.getIngredient(""), null);
    }
}