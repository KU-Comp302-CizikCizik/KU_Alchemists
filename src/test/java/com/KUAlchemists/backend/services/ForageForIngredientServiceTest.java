package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.IngredientStorage;
import com.KUAlchemists.backend.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ForageForIngredientServiceTest {

    @Mock
    private Deck deck;

    @Mock
    private IngredientStorage ingredientStorage;

    private ForageForIngredientService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deck = mock(Deck.class);
        ingredientStorage = mock(IngredientStorage.class);
        String playerName = "mete";
        try (MockedStatic<Board> boardMockedStatic = Mockito.mockStatic(Board.class)) {
            Board board = mock(Board.class);
            boardMockedStatic.when(Board::getInstance).thenReturn(board);
            when(board.getIngredientStorage(playerName)).thenReturn(ingredientStorage);
        }
        try (MockedStatic<Deck> deckMockedStatic = Mockito.mockStatic(Deck.class)) {
            Deck deck = mock(Deck.class);
            deckMockedStatic.when(Deck::getInstance).thenReturn(deck);
        }

        try(MockedStatic<GameEngine> GameEngineMockedStatic = Mockito.mockStatic(GameEngine.class)) {
            GameEngine gameEngine = mock(GameEngine.class);
            GameEngineMockedStatic.when(GameEngine::getInstance).thenReturn(gameEngine);
            when(gameEngine.getPlayer(playerName)).thenReturn(new Player(playerName));
            service = new ForageForIngredientService(gameEngine.getPlayer(playerName));
        }

    }

    @Test
    void forageForIngredientAddsIngredientToStorage() {
        // Arrange
        Ingredient mockedIngredient = new Ingredient();
        when(deck.drawIngredient()).thenReturn(mockedIngredient);

        // Act
        service.forageForIngredient();

        // Assert
        verify(deck).drawIngredient();
        verify(ingredientStorage).addIngredient(mockedIngredient);
    }

    // Additional tests can be added here
}
