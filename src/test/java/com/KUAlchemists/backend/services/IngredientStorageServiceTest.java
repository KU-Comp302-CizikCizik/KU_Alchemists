package com.KUAlchemists.backend.services;


import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.IngredientType;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.IngredientStorage;
import com.KUAlchemists.backend.models.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
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
        player = new Player();
        ingredientStorage = new IngredientStorage();
        ingredientStorageService = new IngredientStorageService();
        Board.getInstance().createEmptyStoragesForPlayer(player);
        Board.getInstance().getIngredientStorages().put(player, ingredientStorage);
        ingredientStorageService.addIngredientToStorage(player,"Salt");

    }

    @Test
    void testRemoveIngredientFromStorage() {
        // Assume that 'Salt' is already added to the player's storage
        assertTrue(ingredientStorageService.getIngredientsNameList(player).contains("Salt"));

        // Act - Remove 'Salt' from the storage
        ingredientStorageService.removeIngredientFromStorage(player, "Salt");

        // Assert - 'Salt' should no longer be in the storage
        assertFalse(ingredientStorageService.getIngredientsNameList(player).contains("Salt"));
    }


    @Test
    void testGetIngredientDescription() {
        // Setup mock data
        String description = "A common ingredient...";
        assertEquals(description, ingredientStorageService.getIngredientDescription(player,"Salt"));
    }

    @Test
    void testGetIngredientsList() {
        ArrayList<String> expectedList = new ArrayList<>(Arrays.asList("Salt"));
        assertEquals(expectedList, ingredientStorageService.getIngredientsNameList(player));
    }
}
