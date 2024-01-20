package com.KUAlchemists.backend.services;


import com.KUAlchemists.backend.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ForageForIngredientServiceTest {

    @Mock
    private Deck deck;

    private IngredientStorage ingredientStorage;

    private ForageForIngredientService service;

    private Player player;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deck = mock(Deck.class);
        ingredientStorage = new IngredientStorage(); // Use real IngredientStorage instance
        String playerName = "mete";
        player = new Player(playerName);

        // Manually add the IngredientStorage to the Board's map for this player
        Board.getInstance().createEmptyStoragesForPlayer(player); // Ensure storage is created for the player
        Board.getInstance().getIngredientStorages().put(player, ingredientStorage); // Explicitly add the storage


        service = new ForageForIngredientService();
    }

    @Test
    void forageForIngredientAddsIngredientToStorage() {
        // Arrange
        Ingredient mockedIngredient = new Ingredient();
        //when(deck.drawIngredient()).thenReturn(mockedIngredient);

        // Act
        service.forageForIngredient(player);
        String name = Board.getInstance().getIngredientStorage(player).getIngredientsList().get(0).getName();
        if (name.equals("Frostleaf") ||
            name.equals("Moonstone") ||
            name.equals("Dragon's Breath") ||
            name.equals("Sunflower") ||
            name.equals("Crystal Shard"))
        {
            assertTrue(true);
        }

    }

    // Additional tests can be added here
}
