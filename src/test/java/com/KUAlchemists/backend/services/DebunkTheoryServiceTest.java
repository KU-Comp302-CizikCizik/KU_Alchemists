package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DebunkTheoryServiceTest {

    private DebunkTheoryService debunkTheoryService;
    private TheoryService theoryService; // Mocked dependency
    private PlayerService playerService; // Mocked dependency

    private Player player; // Mocked dependency
    private Theory theory; // Mocked dependency

    @BeforeEach
    void setUp() {
        theoryService = mock(TheoryService.class);
        playerService = mock(PlayerService.class);
        player = mock(Player.class);
        theory = mock(Theory.class);

        List<Theory> theories = Arrays.asList(theory);
        debunkTheoryService = new DebunkTheoryService(theoryService, playerService, theories);
    }

    @Test
    void debunkTheorySuccess() {
        // Arrange
        String playerName = "John Doe";
        String theoryId = "theory1";

        when(player.getName()).thenReturn(playerName);
        when(theoryService.findTheoryById(theoryId)).thenReturn(theory);
        when(theory.isDebunked()).thenReturn(false);

        // Act
        boolean result = debunkTheoryService.debunkTheory(playerName, theoryId);

        // Assert
        assertTrue(result);
        verify(theory).setDebunked(true);
        verify(playerService).updatePlayerReputation(playerName, 2);
    }

    @Test
    void debunkTheoryFailure_AlreadyDebunked() {
        // Arrange
        String playerName = "John Doe";
        String theoryId = "theory1";

        when(theoryService.findTheoryById(theoryId)).thenReturn(theory);
        when(theory.isDebunked()).thenReturn(true);

        // Act
        boolean result = debunkTheoryService.debunkTheory(playerName, theoryId);

        // Assert
        assertFalse(result);
        verify(theory, never()).setDebunked(anyBoolean());
        verify(playerService, never()).updatePlayerReputation(anyString(), anyInt());
    }

    @Test
    void debunkTheoryFailure_TheoryNotFound() {
        // Arrange
        String playerName = "John Doe";
        String theoryId = "invalidTheory";

        when(theoryService.findTheoryById(theoryId)).thenReturn(null);

        // Act
        boolean result = debunkTheoryService.debunkTheory(playerName, theoryId);

        // Assert
        assertFalse(result);
        verify(playerService, never()).updatePlayerReputation(anyString(), anyInt());
    }

    // Additional tests for other failure scenarios...
}
