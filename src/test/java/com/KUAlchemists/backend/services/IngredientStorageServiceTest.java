package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.IngredientStorage;
import com.KUAlchemists.backend.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IngredientStorageServiceTest {

    private IngredientStorageService ingredientStorageService;
    @Mock
    private IngredientStorage ingredientStorage;
    private Player player;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        //ingredientStorage = Mockito.mock(IngredientStorage.class);
        ingredientStorage = mock(IngredientStorage.class);
        when(Board.getIngredientStorage(player)).thenReturn(ingredientStorage);
        ingredientStorageService = new IngredientStorageService(player);
    }

    @Test
    void testRemoveIngredientFromStorage() {
        // Setup mock data
        Ingredient ingredient = new Ingredient("Salt", "A common ingredient...");
        when(ingredientStorage.getIngredientsList()).thenReturn(new ArrayList<>(Arrays.asList(ingredient)));

        // Execute method
        ingredientStorageService.removeIngredientFromStorage("Salt");

        // Verify behavior
        verify(ingredientStorage, times(1)).removeIngredient(ingredient);
    }

    @Test
    void testGetIngredientDescription() {
        // Setup mock data
        String description = "A magical herb...";
        Ingredient ingredient = new Ingredient("Herb", description);
        when(ingredientStorage.getIngredient("Herb")).thenReturn(ingredient);

        // Execute and assert
        assertEquals(description, ingredientStorageService.getIngredientDescription("Herb"));
    }

    @Test
    void testGetIngredientsList() {
        // Setup mock data
        ArrayList<Ingredient> mockIngredients = new ArrayList<>(Arrays.asList(new Ingredient("Herb", "desc"), new Ingredient("Salt", "desc")));
        when(ingredientStorage.getIngredientsList()).thenReturn(mockIngredients);

        // Execute and assert
        ArrayList<String> expectedList = new ArrayList<>(Arrays.asList("Herb", "Salt"));
        assertEquals(expectedList, ingredientStorageService.getIngredientsList());
    }
}
