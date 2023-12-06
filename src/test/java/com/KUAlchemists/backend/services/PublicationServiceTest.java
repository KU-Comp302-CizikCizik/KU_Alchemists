package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.*;
import com.KUAlchemists.backend.enums.Aspect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    void getPublishedTheoriesInfo_ReturnsCorrectInfo() {
        // Arrange
        Alchemical alc1 = new Alchemical(Aspect.POSITIVE_BIG, Aspect.NEGATIVE_SMALL, Aspect.POSITIVE_SMALL);
        Alchemical alc2 = new Alchemical(Aspect.NEGATIVE_BIG, Aspect.POSITIVE_SMALL, Aspect.NEGATIVE_SMALL);
        Theory theory1 = new Theory(new Ingredient("Wolfsbane",alc1 ), alc1);
        Theory theory2 = new Theory(new Ingredient("Nightshade",alc2 ), alc2);
        List<Theory> publishedTheories = Arrays.asList(theory1, theory2);

        when(player.getPublishedTheories()).thenReturn(publishedTheories);

        String expectedInfo = "Wolfsbane POSITIVE_BIG NEGATIVE_SMALL POSITIVE_SMALL\n" +
                "Nightshade NEGATIVE_BIG POSITIVE_SMALL NEGATIVE_SMALL\n";

        // Act
        String actualInfo = publicationService.getPublishedTheoriesInfo(player);

        // Assert
        assertEquals(expectedInfo, actualInfo);
    }


}

