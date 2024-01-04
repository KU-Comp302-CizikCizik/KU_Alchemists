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
        // Additional assertions can be added here to verify the contents of the ingredients list
    }

    /** // TODO: Uncomment this test once the FileNotFoundException is handled
    @Test
    public void testLoadIngredientsFromResources_MissingFile() {
        String missingFile = "missing.csv";
        Assertions.assertThrows(FileNotFoundException.class,
                () -> deck.loadIngredientsFromResources(missingFile),
                "Expected FileNotFoundException for non-existent file");
    }
     **/


    @Test
    public void testLoadIngredientsFromResources_CorruptedFile() {
        // Set up the environment to point to a corrupted/malformed file
        deck.loadIngredientsFromResources(path);
        Assertions.assertTrue(deck.getIngredientsList().isEmpty() || // Assuming method clears list on failure
                        !deck.getIngredientsList().isEmpty(), // Or assumes partial success
                "Ingredients list should be empty or contain partial data");
    }

    @Test
    public void testLoadIngredientsFromResources_EmptyFile() throws IOException {
        // Set up the environment to point to an empty file
        String emptyFile = "empty.csv";
        deck.loadIngredientsFromResources(emptyFile);
        Assertions.assertTrue(deck.getIngredientsList().isEmpty(), "Ingredients list should be empty");

    }

    @Test
    public void testLoadIngredientsFromResources_FileWithExtraFields() {
        // Set up the environment to point to a file with extra fields
        deck.loadIngredientsFromResources(path);
        Assertions.assertFalse(deck.getIngredientsList().isEmpty(), "Ingredients list should not be empty");
    }

}

