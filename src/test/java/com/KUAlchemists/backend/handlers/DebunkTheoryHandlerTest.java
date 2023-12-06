package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.services.DebunkTheoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DebunkTheoryHandlerTest {

    private DebunkTheoryHandler debunkTheoryHandler;
    private DebunkTheoryService debunkTheoryService; // Mocked dependency

    @BeforeEach
    void setUp() {
        debunkTheoryService = mock(DebunkTheoryService.class);
        debunkTheoryHandler = new DebunkTheoryHandler(debunkTheoryService);
    }

    @Test
    void handleDebunkRequestSuccess() {
        // Arrange
        String theoryId = "123";
        when(debunkTheoryService.debunkTheory(anyString(), eq(theoryId))).thenReturn(true);

        // Act
        String message = debunkTheoryHandler.handleDebunkRequest(theoryId);

        // Assert
        assertEquals("Theory debunked successfully! Reputation increased.", message);
    }

    @Test
    void handleDebunkRequestFailure() {
        // Arrange
        String theoryId = "123";
        when(debunkTheoryService.debunkTheory(anyString(), eq(theoryId))).thenReturn(false);

        // Act
        String message = debunkTheoryHandler.handleDebunkRequest(theoryId);

        // Assert
        assertEquals("Failed to debunk theory.", message);
    }

    // Additional tests for edge cases and failure scenarios...
}
