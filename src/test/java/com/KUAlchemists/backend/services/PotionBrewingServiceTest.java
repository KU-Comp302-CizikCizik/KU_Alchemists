package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.PotionEffect;
import com.KUAlchemists.backend.models.Alchemical;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Potion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionBrewingServiceTest {

    private static PotionBrewingService service;
    @BeforeEach
    public void setUp() {
        service = new PotionBrewingService();
    }
    @Test
    public void testNullCase(){
        Ingredient ingredient1 = createIngredient(Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG);
        Ingredient ingredient2 = createIngredient(Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_BIG);
        Potion potion = service.brewPotion(ingredient1,ingredient2);
        assertNotNull(potion);
    }

    @Test
    public void testNeutralEffect(){
        Ingredient ingredient1 = createIngredient(Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG);
        Ingredient ingredient2 = createIngredient(Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_BIG);
        Potion potion = service.brewPotion(ingredient1,ingredient2);
        assertEquals(PotionEffect.NEUTRAL, potion.getPotionEffect());
    }

    @Test
    public void testHealthEffect(){
        Ingredient ingredient1 = createIngredient(Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG);
        Ingredient ingredient2 = createIngredient(Aspect.POSITIVE_SMALL, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG);
        Potion potion = service.brewPotion(ingredient1,ingredient2);
        assertEquals(PotionEffect.HEALING, potion.getPotionEffect());
    }

    @Test
    public void testPoisonEffect(){
        Ingredient ingredient1 = createIngredient(Aspect.NEGATIVE_BIG, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG);
        Ingredient ingredient2 = createIngredient(Aspect.NEGATIVE_SMALL, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG);
        Potion potion = service.brewPotion(ingredient1,ingredient2);
        assertEquals(PotionEffect.POISON, potion.getPotionEffect());
    }

    @Test
    public void testWisdomEffect(){
        Ingredient ingredient1 = createIngredient(Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG);
        Ingredient ingredient2 = createIngredient(Aspect.NEGATIVE_SMALL, Aspect.POSITIVE_BIG, Aspect.POSITIVE_SMALL);
        Potion potion = service.brewPotion(ingredient1,ingredient2);
        assertEquals(PotionEffect.POISON, potion.getPotionEffect());
    }


    @Test
    public void testInsanityEffect(){
        Ingredient ingredient1 = createIngredient(Aspect.POSITIVE_BIG, Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_BIG);
        Ingredient ingredient2 = createIngredient(Aspect.POSITIVE_BIG, Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_SMALL);
        Potion potion = service.brewPotion(ingredient1,ingredient2);
        assertEquals(PotionEffect.INSANITY, potion.getPotionEffect());
    }


    @Test
    public void testSpeedEffect(){
        Ingredient ingredient1 = createIngredient(Aspect.POSITIVE_BIG, Aspect.POSITIVE_SMALL, Aspect.POSITIVE_SMALL);
        Ingredient ingredient2 = createIngredient(Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG, Aspect.NEGATIVE_SMALL);
        Potion potion = service.brewPotion(ingredient1,ingredient2);
        assertEquals(PotionEffect.SPEED, potion.getPotionEffect());
    }

    @Test
    public void testParalysisEffect(){
        Ingredient ingredient1 = createIngredient(Aspect.POSITIVE_BIG, Aspect.NEGATIVE_BIG, Aspect.POSITIVE_SMALL);
        Ingredient ingredient2 = createIngredient(Aspect.POSITIVE_BIG, Aspect.NEGATIVE_SMALL, Aspect.NEGATIVE_SMALL);
        Potion potion = service.brewPotion(ingredient1,ingredient2);
        assertEquals(PotionEffect.PARALYSIS, potion.getPotionEffect());
    }


    private Ingredient createIngredient(Aspect r, Aspect g, Aspect b) {
        Alchemical alchemical = new Alchemical(r, g, b);
        Ingredient ingredient = new Ingredient();
        ingredient.setAlchemical(alchemical);
        return ingredient;
    }


}