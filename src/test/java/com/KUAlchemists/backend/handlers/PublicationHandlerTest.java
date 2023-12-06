package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.services.PublicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PublicationHandlerTest {

    private PublicationHandler publicationHandler;
    private PublicationService publicationService; // Mocked dependency

    @BeforeEach
    void setUp() {
        publicationService = mock(PublicationService.class);
        publicationHandler = new PublicationHandler(publicationService);

    }

    @Test
    void handlePublishRequestSuccess() {
        // Arrange
        String ingredientName = "Wolfsbane";
        when(publicationService.publishTheory(any(), eq(ingredientName), anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        String message = publicationHandler.handlePublishRequest(ingredientName, "POSITIVE_BIG", "NEGATIVE_SMALL", "POSITIVE_SMALL");

        // Assert
        assertEquals("Theory published successfully!", message);
    }

    @Test
    void handlePublishRequestFailure() {
        // Arrange
        String ingredientName = "Wolfsbane";
        when(publicationService.publishTheory(any(), eq(ingredientName), anyString(), anyString(), anyString())).thenReturn(false);

        // Act
        String message = publicationHandler.handlePublishRequest(ingredientName, "POSITIVE_BIG", "NEGATIVE_SMALL", "POSITIVE_SMALL");

        // Assert
        assertEquals("Failed to publish theory.", message);
    }

    // Additional tests for edge cases and failure scenarios...
}
