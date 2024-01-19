package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
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
            ArrayList<String> ingredientNames = new ArrayList<>();
            String ingredientName1 = magicMortarService.getIngredientName1();
            String ingredientName2 = magicMortarService.getIngredientName2();
            ingredientNames.add(ingredientName1);
            ingredientNames.add(ingredientName2);
                return ingredientNames;
        }
        //this method is called when the player clicks on the ingredient to retain. UI should give the retained ingredient.
        public void handleRetainedIngredient(String ingredientName){
            magicMortarService.setIngredientNameToRetain(ingredientName);
        }

}
