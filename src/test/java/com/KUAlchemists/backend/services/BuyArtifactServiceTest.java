package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Artifact;
import com.KUAlchemists.backend.models.ArtifactShop;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BuyArtifactServiceTest {

    @Mock
    private ArtifactShop artifactShop;

    private BuyArtifactService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new BuyArtifactService(artifactShop);
    }

    @Test
    void buyArtifactSuccess() {
        // Arrange
        String artifactName = "Elixir of Insight";
        Player player = new Player("Alice");
        player.setGold(10); // Player has enough gold
        Artifact artifact = new Artifact(artifactName, 5, "Effect", 2); // Cost of artifact is 5 gold
        when(artifactShop.getArtifact(artifactName)).thenReturn(artifact);
        Player player1 = new Player();
        GameEngine.getInstance().addPlayer(player);
        GameEngine.getInstance().addPlayer(player1);
        Board.getInstance().initializePlayer(player);
        // Act
        boolean result = service.buyArtifact(player, artifactName);

        // Assert
        assertTrue(result);
        assertEquals(5, player.getGold()); // Player's gold should be decremented by the cost of the artifact
    }

    @Test
    void buyArtifactFailureNotEnoughGold() {
        // Arrange
        String artifactName = "Elixir of Insight";
        Player player = new Player("Bob");
        player.setGold(3); // Player does not have enough gold
        Artifact artifact = new Artifact(artifactName, 5, "Effect", 2);
        when(artifactShop.getArtifact(artifactName)).thenReturn(artifact);

        // Act
        boolean result = service.buyArtifact(player, artifactName);

        // Assert
        assertFalse(result);
        assertEquals(3, player.getGold()); // Player's gold should remain unchanged
    }

    @Test
    void buyArtifactFailureArtifactNotFound() {
        // Arrange
        String artifactName = "Unknown Artifact";
        Player player = new Player("Charlie");
        player.setGold(10);
        when(artifactShop.getArtifact(artifactName)).thenReturn(null); // Artifact does not exist

        // Act
        boolean result = service.buyArtifact(player, artifactName);

        // Assert
        assertFalse(result);
    }

    // Additional test cases as necessary...
}
