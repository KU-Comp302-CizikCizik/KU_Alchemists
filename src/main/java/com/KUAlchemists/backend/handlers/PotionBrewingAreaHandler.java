package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.managers.EventManager;
import com.KUAlchemists.backend.models.*;
import com.KUAlchemists.backend.services.MagicMortarService;
import com.KUAlchemists.backend.services.PotionBrewingService;

import java.util.ArrayList;

public class PotionBrewingAreaHandler {


    private static PotionBrewingAreaHandler INSTANCE;
    private ArrayList<String> ingredientsToBeBrewed;
    private MagicMortarService magicMortarService;


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
        if(GameEngine.getInstance().getCurrentPlayer().getActionPoints() < 1){
            return null;
        }
        String ingredient1NameFormatted = potionBrewingService.getFormattedName(ingredient1Name);
        String ingredient2NameFormatted = potionBrewingService.getFormattedName(ingredient2Name);
        //search for ingredient1 and ingredient2 in player inventory
        Ingredient ingredient1 = IngredientStorageHandler.getInstance().handleGetIngredientByName(ingredient1NameFormatted);
        Ingredient ingredient2 = IngredientStorageHandler.getInstance().handleGetIngredientByName(ingredient2NameFormatted);

        //brew the potion following the rules
        Potion potion = potionBrewingService.brewPotion(ingredient1,ingredient2);
        //notify the observers
        EventManager.getInstance().onPotionBrewingPerformed(potion);

        //add the potion to the player inventory
        PotionStorageHandler.getInstance().handleAddPotion(potion);

        //magic mortar effect... Backend agam buraya bi bakarsın
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        currentPlayer.deduceActionPoints(1);
        MagicMortarHandler.getInstance().handlePerformMagicMortar(ingredient1,ingredient2,ingredient1NameFormatted,ingredient2NameFormatted);
        //get potionCode for UI
        String potionCode = potionBrewingService.getPotionCode(potion);

        //notify the observers such as DeductionBoard
        EventManager.getInstance().onPotionBrewingPerformed(potion);

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

    public boolean isMagicMortarActivated(){
        Artifact magicMortar = Board.getInstance().getArtifactStorage(GameEngine.getInstance().getCurrentPlayer()).getArtifactByName("magic_mortar");
        return magicMortar != null && magicMortar.isActivated();
    }
}
