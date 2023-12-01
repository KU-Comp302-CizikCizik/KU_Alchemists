package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.services.PotionBrewingService;
import com.KUAlchemists.backend.models.Potion;

public class PotionBrewingAreaHandler {

    private final PotionBrewingService potionBrewingService; //instance of PotionBrewingService

    /**
     * PotionBrewingAreaHandler
     */
    public PotionBrewingAreaHandler() {
        this.potionBrewingService = new PotionBrewingService();
    }

    /**
     * brewPotion
     * @param ingredient1Name, ingredient2Name
     */
    public void brewPotion(String ingredient1Name, String ingredient2Name) {

        //TO:DO better way to handle this instead of strings ??

        //search for ingredient1 and ingredient2 in player inventory
        Ingredient ingredient1 = GameEngine.getCurrentPlayer().getPlayerInventory().getIngredientWithName(ingredient1Name);
        Ingredient ingredient2 = GameEngine.getCurrentPlayer().getPlayerInventory().getIngredientWithName(ingredient2Name);

        //brew the potion following the rules
        Potion potion = potionBrewingService.brewPotion(ingredient1,ingredient2);

        //add the potion to the player inventory
        GameEngine.getCurrentPlayer().getPlayerInventory().addPotion(potion);

        //remove the ingredients from the player inventory
        GameEngine.getCurrentPlayer().getPlayerInventory().removeIngredient(ingredient1);
        GameEngine.getCurrentPlayer().getPlayerInventory().removeIngredient(ingredient2);

    }



}
