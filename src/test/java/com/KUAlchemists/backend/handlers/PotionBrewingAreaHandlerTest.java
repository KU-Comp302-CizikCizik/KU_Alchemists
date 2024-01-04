package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.managers.EventManager;
import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.services.PotionBrewingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class PotionBrewingAreaHandlerTest {

    @Mock
    private PotionBrewingService potionBrewingService;
    @Mock
    private IngredientStorageHandler ingredientStorageHandler;
    @Mock
    private EventManager eventManager;
    @Mock
    private PotionStorageHandler potionStorageHandler;
    @Mock
    private Deck deck;

    private PotionBrewingAreaHandler potionBrewingAreaHandler;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        potionBrewingAreaHandler = PotionBrewingAreaHandler.getInstance();
    }

    @Test
    void testPotionBrewingWithValidIngredients(){

    }


    @Test
    void testPotionBrewingWithInvalidIngredients(){

    }

    @Test
    void testPotionBrewingWithSingleIngredientTest(){

    }


    @Test
    void testEventManagerHasBeenCalled(){

    }

    @Test
    void testPotionStorageHandlerUpdatedCorrectly(){

    }

    @Test
    void testPotionEffectAccurate(){

    }


}