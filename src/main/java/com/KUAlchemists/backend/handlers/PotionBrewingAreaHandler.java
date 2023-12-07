package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.managers.EventManager;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Deck;
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

        //search for ingredient1 and ingredient2 in player inventory
        Ingredient ingredient1 = IngredientStorageHandler.getInstance().handleGetIngredientByName(ingredient1Name);
        Ingredient ingredient2 = IngredientStorageHandler.getInstance().handleGetIngredientByName(ingredient2Name);

        //brew the potion following the rules
        Potion potion = potionBrewingService.brewPotion(ingredient1,ingredient2);
        //notify the observers
        EventManager.getInstance().onPotionBrewingActionPerformed(potion);

        //add the potion to the player inventory
        PotionStorageHandler.getInstance().handleAddPotion(potion);

        //remove the ingredients from the player inventory
        IngredientStorageHandler.getInstance().handleRemoveIngredient(ingredient1Name);
        IngredientStorageHandler.getInstance().handleRemoveIngredient(ingredient2Name);

        //add the ingredients back to the deck
        Deck.getInstance().addIngredient(ingredient1);
        Deck.getInstance().addIngredient(ingredient2);


    }



}
