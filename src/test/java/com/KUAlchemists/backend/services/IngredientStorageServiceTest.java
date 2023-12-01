package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.KUAlchemists.backend.enums.Aspect.POSITIVE_BIG;
import static org.mockito.Mockito.*;

class IngredientStorageServiceTest {

    @Mock
    private IngredientStorage ingredientStorage;

    private IngredientStorageService ingredientStorageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientStorageService = new IngredientStorageService(ingredientStorage);
    }

    @Test
    void addIngredientToStorage() {
        String name = "testIngredient";
        Aspect aspect1 = POSITIVE_BIG;
        Aspect aspect2 = POSITIVE_BIG;
        Aspect aspect3 = POSITIVE_BIG;
        Alchemical alchemical = new Alchemical(aspect1, aspect2, aspect3);
        Ingredient ingredient = new Ingredient(name, alchemical);
        ingredientStorageService.addIngredientToStorage(ingredient);
        verify(ingredientStorage, times(1)).addIngredient(ingredient);
    }

    @Test
    void removeIngredientFromStorage() {
        String name = "testIngredient";
        Aspect aspect1 = POSITIVE_BIG;
        Aspect aspect2 = POSITIVE_BIG;
        Aspect aspect3 = POSITIVE_BIG;
        Alchemical alchemical = new Alchemical(aspect1, aspect2, aspect3);
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
