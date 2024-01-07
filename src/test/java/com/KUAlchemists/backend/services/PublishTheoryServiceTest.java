package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PublishTheoryServiceTest {
    @Mock
    private Player mockPlayer;

    private GameEngine mockGameEngine;
    private Board mockBoard;

    private PublishTheoryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock GameEngine and Board
        mockGameEngine = mock(GameEngine.class);
        mockBoard = mock(Board.class);

        when(mockGameEngine.getCurrentPlayer()).thenReturn(mockPlayer);

        // Set the singleton instances to the mocks
        GameEngine.setInstanceForTest(mockGameEngine);
        Board.setInstanceForTest(mockBoard);

        // Setup for PublishTheoryService or any other service you are testing
        service = new PublishTheoryService();
    }
    @AfterEach
    void tearDown() {
        // Reset the singleton instances after each test to avoid state leakage between tests
        GameEngine.setInstanceForTest(null);
        Board.setInstanceForTest(null);
    }
    @Test
    void testPublishTheorySuccess() {
        // Set up the mock behavior for a successful theory publication
        when(mockGameEngine.getCurrentPlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getGold()).thenReturn(2); // Assume the player has enough gold
        when(mockBoard.getPublishedTheoriesList()).thenReturn(new ArrayList<>()); // No existing theories

        // Use valid Aspect enum values
        boolean result = service.publishTheory("Mushroom", "POSITIVE_BIG", "NEGATIVE_SMALL", "POSITIVE_SMALL", List.of("SS"));
        assertTrue(result, "Theory should be published successfully");

        // Verify interactions and state changes
        verify(mockPlayer).setGold(anyInt()); // Check if the player's gold is decremented
        verify(mockBoard).addTheoryToPublishedList(any(Theory.class)); // Check if the theory is added to the board
    }

    @Test
    void testPublishTheoryFailureDueToInsufficientGold() {
        when(mockGameEngine.getCurrentPlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getGold()).thenReturn(0); // Player has no gold

        // Use valid Aspect enum values
        boolean result = service.publishTheory("Ingredient", "NEGATIVE_BIG", "POSITIVE_SMALL", "NEGATIVE_SMALL", List.of("SS"));
        assertFalse(result, "Theory should not be published due to insufficient gold");


        // Verify no theory is published
        verify(mockBoard, never()).addTheoryToPublishedList(any(Theory.class));
    }

    @Test
    void testPublishTheoryFailureDueToDuplicate() {
        Theory existingTheory = new Theory(new Ingredient("Ingredient"),  new Alchemical(Aspect.POSITIVE_BIG, Aspect.NEGATIVE_SMALL, Aspect.POSITIVE_SMALL), new ArrayList<>());
        List<Theory> existingTheories = new ArrayList<>();
        existingTheories.add(existingTheory);

        when(mockGameEngine.getCurrentPlayer()).thenReturn(mockPlayer);
        when(mockBoard.getPublishedTheoriesList()).thenReturn(existingTheories);

        boolean result = service.publishTheory("Ingredient", "NEGATIVE_BIG", "NEGATIVE_SMALL", "POSITIVE_BIG", List.of("GS"));
        assertFalse(result, "Theory should not be published due to duplicate");

        // Verify no additional theory is published
        verify(mockBoard, never()).addTheoryToPublishedList(any(Theory.class));
    }
    @Test
    void testPlayerGoldDecreaseOnPublishTheory() {
        when(mockPlayer.getGold()).thenReturn(2); // Player has enough gold
        List<Theory> publishedTheories = new ArrayList<>();
        when(mockBoard.getPublishedTheoriesList()).thenReturn(publishedTheories); // No existing theories

        service.publishTheory("Mushroom", "NEGATIVE_BIG", "POSITIVE_BIG", "NEGATIVE_SMALL", List.of("GQ"));

        verify(mockPlayer).setGold(1); // Check if player's gold is decremented
        verify(mockBoard).addTheoryToPublishedList(any(Theory.class)); // Verify theory is added
    }
    @Test
    void testPublishTheoryWithInvalidIngredientName() {
        boolean result = service.publishTheory("", "POSITIVE_SMALL", "NEGATIVE_BIG", "POSITIVE_BIG", List.of("GS"));
        assertFalse(result, "Theory should not be published with an invalid ingredient name");

        verify(mockBoard, never()).addTheoryToPublishedList(any(Theory.class)); // Ensure no theory is added to the board
    }


}