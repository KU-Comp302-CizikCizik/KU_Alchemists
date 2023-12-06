package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.*;
import com.KUAlchemists.backend.enums.Aspect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PublicationServiceTest {

    private PublicationService publicationService;
    private IngredientService ingredientService; // Mocked dependency
    private PlayerService playerService; // Mocked dependency
    private Player player; // Mocked dependency
    private Ingredient ingredient; // Mocked dependency

    @BeforeEach
    void setUp() {
        ingredientService = mock(IngredientService.class);
        playerService = mock(PlayerService.class);
        publicationService = new PublicationService(ingredientService, playerService);
        player = mock(Player.class);
        ingredient = mock(Ingredient.class);

        when(player.getName()).thenReturn("PlayerName");
    }

    @Test
    void publishTheorySuccess() {
        // Arrange
        String ingredientName = "Wolfsbane";
        when(ingredientService.findIngredientByName(ingredientName)).thenReturn(ingredient);
        when(player.getGold()).thenReturn(5);

        // Act
        boolean result = publicationService.publishTheory(player, ingredientName, "POSITIVE_BIG", "NEGATIVE_SMALL", "POSITIVE_SMALL");

        // Assert
        assertTrue(result);
        verify(playerService).updatePlayerGold("PlayerName", 4);
        verify(playerService).updatePlayerReputation("PlayerName", 1);
    }

    @Test
    void publishTheoryFailure_NotEnoughGold() {
        // Arrange
        String ingredientName = "Wolfsbane";
        when(ingredientService.findIngredientByName(ingredientName)).thenReturn(ingredient);
        when(player.getGold()).thenReturn(0); // Player does not have enough gold

        // Act
        boolean result = publicationService.publishTheory(player, ingredientName, "POSITIVE_BIG", "NEGATIVE_SMALL", "POSITIVE_SMALL");

        // Assert
        assertFalse(result);
        verify(playerService, never()).updatePlayerGold(anyString(), anyInt());
        verify(playerService, never()).updatePlayerReputation(anyString(), anyInt());
    }

    // Additional tests for edge cases and failure scenarios...
}

