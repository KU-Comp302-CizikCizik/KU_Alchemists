package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.*;
import com.KUAlchemists.backend.services.MagicMortarService;

import java.util.ArrayList;

public class MagicMortarHandler {

        private static MagicMortarHandler instance = null;
        private MagicMortarService magicMortarService;
        private MagicMortarHandler() {
            this.magicMortarService = new MagicMortarService();
        }

        public static MagicMortarHandler getInstance() {
            if (instance == null) {
                instance = new MagicMortarHandler();
            }
            return instance;
        }
        public void handleActivateMagicMortar(){
            Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
            magicMortarService.activateMagicMortar(currentPlayer);
        }

    //this returns the ingredient names in the potion brewing area. It is called by Mahmut.
        public ArrayList<String> handleGetIngredientNames(){
//            ArrayList<String> ingredientNames = new ArrayList<>();
//            String ingredientName1 = magicMortarService.getIngredientName1();
//            String ingredientName2 = magicMortarService.getIngredientName2();
//            ingredientNames.add(ingredientName1);
//            ingredientNames.add(ingredientName2);
//                return ingredientNames;
            return PotionBrewingAreaHandler.getInstance().getIngredientsToBeBrewed();
        }
        //this method is called when the player clicks on the ingredient to retain. UI should give the retained ingredient.
        public void handleRetainedIngredient(String ingredientName){
//            UseArtifactHandler.getInstance().deactivateArtifact("magic_mortar");
            magicMortarService.setIngredientNameToRetain(ingredientName);
        }

    public void handlePerformMagicMortar(Ingredient ingredient1, Ingredient ingredient2, String ingredient1NameFormatted, String ingredient2NameFormatted) {
        Artifact magicMortar= Board.getInstance().getArtifactStorage(GameEngine.getInstance().getCurrentPlayer()).getArtifactByName("magic_mortar");
        if(magicMortar != null && magicMortar.isActivated()){
            magicMortarService.setIngredientName1(ingredient1NameFormatted);
            magicMortarService.setIngredientName2(ingredient2NameFormatted);


            if (ingredient1NameFormatted.toLowerCase().equals(magicMortarService.getIngredientNameToRetain())){
                IngredientStorageHandler.getInstance().handleRemoveIngredient(ingredient2NameFormatted);
                Deck.getInstance().addIngredient(ingredient2);
            }

            else if (ingredient2NameFormatted.toLowerCase().equals(magicMortarService.getIngredientNameToRetain())){
                IngredientStorageHandler.getInstance().handleRemoveIngredient(ingredient1NameFormatted);
                Deck.getInstance().addIngredient(ingredient1);
            }

            Board.getInstance().getArtifactStorage(GameEngine.getInstance().getCurrentPlayer()).getArtifactByName("magic_mortar").setActivated(false);
            Board.getInstance().getArtifactStorage(GameEngine.getInstance().getCurrentPlayer()).removeArtifact(magicMortar);
            GameEngine.getInstance().getCurrentPlayer().removeActivedArtifact("magic_mortar");




        }
        else{
            //remove the ingredients from the player inventory
            IngredientStorageHandler.getInstance().handleRemoveIngredient(ingredient1NameFormatted);
            IngredientStorageHandler.getInstance().handleRemoveIngredient(ingredient2NameFormatted);
            //add the ingredients back to the deck
            Deck.getInstance().addIngredient(ingredient1);
            Deck.getInstance().addIngredient(ingredient2);
        }
    }
}
