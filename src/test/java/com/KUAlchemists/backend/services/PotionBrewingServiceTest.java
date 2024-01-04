package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.enums.PotionEffect;
import com.KUAlchemists.backend.models.Alchemical;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Potion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionBrewingServiceTest {


    @Test
    public void testNullCase(){
        PotionBrewingService potionBrewingService = new PotionBrewingService();
        Ingredient ingredient1 = new Ingredient("");
        Alchemical alchemical1 = new Alchemical(Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG);
        Alchemical alchemical2 = new Alchemical(Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_BIG);
        Ingredient ingredient2 = new Ingredient("");
        ingredient1.setAlchemical(alchemical1);
        ingredient2.setAlchemical(alchemical2);
        Potion potion = potionBrewingService.brewPotion(ingredient1,ingredient2);
        assertNotNull(potion);
    }

    @Test
    public void testPotionEffect(){
        PotionBrewingService potionBrewingService = new PotionBrewingService();
        Ingredient ingredient1 = new Ingredient("");
        Alchemical alchemical1 = new Alchemical(Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG);
        Alchemical alchemical2 = new Alchemical(Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_BIG);
        Ingredient ingredient2 = new Ingredient("");
        ingredient1.setAlchemical(alchemical1);
        ingredient2.setAlchemical(alchemical2);
        Potion potion = potionBrewingService.brewPotion(ingredient1,ingredient2);
        assertEquals(PotionEffect.NEUTRAL, potion.getPotionEffect());
    }


    @Test
    public void testHealthPotion(){
        PotionBrewingService potionBrewingService = new PotionBrewingService();
        Ingredient ingredient1 = new Ingredient("");
        Alchemical alchemical1 = new Alchemical(Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG);
        Alchemical alchemical2 = new Alchemical(Aspect.POSITIVE_SMALL, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG);
        Ingredient ingredient2 = new Ingredient("");
        ingredient1.setAlchemical(alchemical1);
        ingredient2.setAlchemical(alchemical2);
        Potion potion = potionBrewingService.brewPotion(ingredient1,ingredient2);
        assertEquals(PotionEffect.HEALING, potion.getPotionEffect());
    }


}