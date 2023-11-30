/**
package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;


class IngredientStorageServiceTest {

    @Mock
    private Player currentPlayer;

    private IngredientStorageService ingredientStorageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientStorageService = new IngredientStorageService(currentPlayer);
    }

    @Test
    void addIngredientToStorage() {
        String name = "testIngredient";
        Atom atom1 = new Atom(true,true);
        Atom atom2 = new Atom(true,true);
        Atom atom3 = new Atom(true,true);
        Alchemical alchemical = new Alchemical(atom1, atom2, atom3);
        Ingredient ingredient = new Ingredient(name, alchemical);
        ingredientStorageService.addIngredientToStorage(ingredient);
        verify(currentPlayer, times(1)).addIngredient(ingredient);
    }

    @Test
    void removeIngredientFromStorage() {
        String name = "testIngredient";
        Atom atom1 = new Atom(true,true);
        Atom atom2 = new Atom(true,true);
        Atom atom3 = new Atom(true,true);
        Alchemical alchemical = new Alchemical(atom1, atom2, atom3);
        Ingredient ingredient = new Ingredient(name, alchemical);
        ingredientStorageService.removeIngredientFromStorage(ingredient);

        verify(ingredientStorage, times(1)).removeIngredient(ingredient);
    }

    @Test
    void getIngredientFromStorage() {
        String ingredientName = "testIngredient";
        ingredientStorageService.getIngredientFromStorage(ingredientName);

        verify(ingredientStorage, times(1)).getIngredient(ingredientName);
    }
}
**/