package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DebunkTheoryServiceTest {

    private DebunkTheoryService debunkTheoryService;

    @Mock
    private TheoryService theoryService;

    @Mock
    private Theory theory;

    @Mock
    private Player player;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        debunkTheoryService = new DebunkTheoryService();
    }

    @Test
    void debunkTheory_Success() {
        // Arrange
        when(theoryService.findTheoryById("theoryId")).thenReturn(theory);
        when(theory.isDebunked()).thenReturn(false);
        when(player.getReputation()).thenReturn(0);

        // Act
        boolean result = debunkTheoryService.debunkTheory(player, "theoryId");

        // Assert
        assertTrue(result);
        verify(theory).setDebunked(true);
        verify(player).setReputation(2); // Reputation increased by 2
    }

    @Test
    void debunkTheory_AlreadyDebunked() {
        // Arrange
        when(theoryService.findTheoryById("theoryId")).thenReturn(theory);
        when(theory.isDebunked()).thenReturn(true);

        // Act
        boolean result = debunkTheoryService.debunkTheory(player, "theoryId");

        // Assert
        assertFalse(result);
        verify(player, never()).setReputation(anyInt()); // Reputation should not change
    }

    @Test
    void debunkTheory_TheoryNotFound() {
        // Arrange
        when(theoryService.findTheoryById("theoryId")).thenReturn(null);

        // Act
        boolean result = debunkTheoryService.debunkTheory(player, "theoryId");

        // Assert
        assertFalse(result);
        verify(player, never()).setReputation(anyInt()); // Reputation should not change
    }

    // Additional test cases can be added here...
}
