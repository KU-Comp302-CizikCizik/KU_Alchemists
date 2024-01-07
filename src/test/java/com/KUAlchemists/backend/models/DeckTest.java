package com.KUAlchemists.backend.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;



public class DeckTest {
    private Deck deck;
    private String path = "ingredients.csv";

    @BeforeEach
    public void setUp() {
        deck = Deck.getInstance();
        deck.setIngredientList(new ArrayList<>());
    }

    @Test
    public void testLoadIngredientsFromResources_ValidFile() {
        deck.loadIngredientsFromResources(path);
        ArrayList<Ingredient> ingredients = deck.getIngredientsList();
        Assertions.assertFalse(ingredients.isEmpty(), "Ingredients list should not be empty");
        Ingredient firstIngredient = ingredients.get(0);
        Assertions.assertEquals("Feather", firstIngredient.getName());
        Assertions.assertEquals(1, firstIngredient.getValue());
    }

     // TODO: Uncomment this test once the FileNotFoundException is handled
    @Test
    public void testLoadIngredientsFromResources_MissingFile() {
        String missingFile = "missing.csv";
        deck.loadIngredientsFromResources(missingFile); // it will not add any ingredient to the list, so it will be empty
        Assertions.assertTrue(deck.getIngredientsList().isEmpty(), "Ingredients list should be empty");
    }


    @Test
    public void testLoadIngredientsFromResources_IncompleteData() {
        String incompleteDataFile = "empty.csv";
        deck.loadIngredientsFromResources(incompleteDataFile);
        boolean isPartiallyFilled = deck.getIngredientsList().size() < 8;
        Assertions.assertTrue(isPartiallyFilled, "Ingredients list should be partially filled");
    }


    @Test
    public void testLoadIngredientsFromResources_EmptyFile() throws IOException {
        String emptyFile = "empty.csv";
        deck.loadIngredientsFromResources(emptyFile);
        Assertions.assertTrue(deck.getIngredientsList().isEmpty(), "Ingredients list should be empty");

    }

    @Test
    public void testLoadIngredientsFromResources_InvalidDataFormat() {
        String invalidFormatFile = "empty.csv";
        deck.loadIngredientsFromResources(invalidFormatFile); // it will not add any ingredient to the list, so it will be empty
        Assertions.assertTrue(deck.getIngredientsList().isEmpty(), "Ingredients list should be empty after format exception");
    }


}

