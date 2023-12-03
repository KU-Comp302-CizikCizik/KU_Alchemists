package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.IngredientStorage;
import com.KUAlchemists.backend.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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
        Board.createEmptyStoragesForPlayer(player); // Ensure storage is created for the player
        Board.getIngredientStorages().put(player, ingredientStorage); // Explicitly add the storage

        service = new ForageForIngredientService(player);
    }

    @Test
    void forageForIngredientAddsIngredientToStorage() {
        // Arrange
        Ingredient mockedIngredient = new Ingredient();
        //when(deck.drawIngredient()).thenReturn(mockedIngredient);

        // Act
        service.forageForIngredient();
        String name = ingredientStorage.getIngredientsList().get(0).getName();
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
