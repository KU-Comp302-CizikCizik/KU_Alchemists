package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.*;
import com.KUAlchemists.backend.enums.Aspect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PublicationServiceTest {

    private PublicationService publicationService;
    private IngredientService ingredientService; // Mocked dependency
    private Player player; // Mocked dependency

    @BeforeEach
    void setUp() {
        ingredientService = mock(IngredientService.class);
        publicationService = new PublicationService(ingredientService);
        player = mock(Player.class);
    }

    @Test
    void publishTheorySuccess() {
        // Arrange
        String ingredientName = "Wolfsbane";
        Aspect redAspect = Aspect.POSITIVE_BIG; // Mocked or created Aspect enum values
        Aspect greenAspect = Aspect.NEGATIVE_SMALL;
        Aspect blueAspect = Aspect.POSITIVE_SMALL;
        Alchemical alchemical = new Alchemical(redAspect, greenAspect, blueAspect); // Create an Alchemical instance
        Ingredient ingredient = new Ingredient(ingredientName, alchemical); // Include the Alchemical in the Ingredient constructor
        when(ingredientService.findIngredientByName(ingredientName)).thenReturn(ingredient);
        when(player.getGold()).thenReturn(5);

        // Act
        boolean result = publicationService.publishTheory(player, ingredientName, "POSITIVE_BIG", "NEGATIVE_SMALL", "POSITIVE_SMALL");

        // Assert
        assertTrue(result);
        verify(player).setGold(4); // Check if gold was decremented
        verify(player).setReputation(1); // Check if reputation was incremented
    }

    @Test
    void publishTheoryFailure_NotEnoughGold() {
        // Arrange
        String ingredientName = "Wolfsbane";
        when(player.getGold()).thenReturn(0); // Player does not have enough gold

        // Act
        boolean result = publicationService.publishTheory(player, ingredientName, "POSITIVE_BIG", "NEGATIVE_SMALL", "POSITIVE_SMALL");

        // Assert
        assertFalse(result);
        verify(player, never()).setReputation(anyInt()); // Reputation should not be changed
    }

    // Additional tests for edge cases and failure scenarios...
}
