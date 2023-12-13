package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.PotionEffect;
import com.KUAlchemists.backend.managers.EventManager;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.services.PotionBrewingService;
import com.KUAlchemists.backend.models.Potion;

import java.util.ArrayList;

public class PotionBrewingAreaHandler {


    private static PotionBrewingAreaHandler INSTANCE;

    private ArrayList<String> ingredientsToBeBrewed;


    public static PotionBrewingAreaHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PotionBrewingAreaHandler();
        }
        return INSTANCE;
    }

    private final PotionBrewingService potionBrewingService; //instance of PotionBrewingService

    /**
     * PotionBrewingAreaHandler
     */
    private PotionBrewingAreaHandler() {
        this.potionBrewingService = new PotionBrewingService();
    }

    /**
     * brewPotion
     * @param ingredient1Name, ingredient2Name
     */
    public String brewPotion(String ingredient1Name, String ingredient2Name) {
        String ingredient1NameFormatted = potionBrewingService.getFormattedName(ingredient1Name);
        String ingredient2NameFormatted = potionBrewingService.getFormattedName(ingredient2Name);
        //search for ingredient1 and ingredient2 in player inventory
        Ingredient ingredient1 = IngredientStorageHandler.getInstance().handleGetIngredientByName(ingredient1NameFormatted);
        Ingredient ingredient2 = IngredientStorageHandler.getInstance().handleGetIngredientByName(ingredient2NameFormatted);

        //brew the potion following the rules
        Potion potion = potionBrewingService.brewPotion(ingredient1,ingredient2);
        //notify the observers
        EventManager.getInstance().onPotionBrewingActionPerformed(potion);

        //add the potion to the player inventory
        PotionStorageHandler.getInstance().handleAddPotion(potion);

        //remove the ingredients from the player inventory
        IngredientStorageHandler.getInstance().handleRemoveIngredient(ingredient1NameFormatted);
        IngredientStorageHandler.getInstance().handleRemoveIngredient(ingredient2NameFormatted);

        //add the ingredients back to the deck
        Deck.getInstance().addIngredient(ingredient1);
        Deck.getInstance().addIngredient(ingredient2);

        //get potionCode for UI
        String potionCode = potionBrewingService.getPotionCode(potion);

        //notify the observers such as DeductionBoard, NEUTRAL potions should not be notified
        if(potion.getPotionEffect() != PotionEffect.NEUTRAL)
            EventManager.getInstance().onPotionBrewingActionPerformed(potion);

        return potionCode;
    }

    public ArrayList<String> getIngredientList(){
        ArrayList<String> ingredientNames = IngredientStorageHandler.getInstance().handleGetIngredientList(GameEngine.getInstance().getCurrentPlayer());
        for(int i =0;i<ingredientNames.size();i++){
            String name = ingredientNames.get(i).toLowerCase();
            ingredientNames.set(i,name);
        }
        return ingredientNames;
    }

    public void setIngredientsToBeBrewed(String ingredient1, String ingredient2){
        ingredientsToBeBrewed = new ArrayList<>();
        ingredientsToBeBrewed.add(ingredient1);
        ingredientsToBeBrewed.add(ingredient2);
    }

    public ArrayList<String> getIngredientsToBeBrewed(){
        return ingredientsToBeBrewed;
    }





}
