package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.ForageForIngredientService;

/**
 * This class is responsible for handling forage for ingredient requests.
 */
public class ForageForIngredientHandler {

        private int ForageForIngredientActionCost = 1;
        public static ForageForIngredientHandler INSTANCE;

        public static ForageForIngredientHandler getInstance(){
                if(INSTANCE == null){
                        INSTANCE = new ForageForIngredientHandler();
                }
                return INSTANCE;
        }
        private final ForageForIngredientService forageForIngredientService;
        private ForageForIngredientHandler() {
                forageForIngredientService = new ForageForIngredientService();
        }

        public String forageForIngredient() {
                if(GameEngine.getInstance().getCurrentPlayer().getActionPoints() < ForageForIngredientActionCost){
                        return null;
                }
                String str =  forageForIngredientService.forageForIngredient(GameEngine.getInstance().getCurrentPlayer());
                GameEngine.getInstance().getCurrentPlayer().deduceActionPoints(ForageForIngredientActionCost);
                return str;
        }

        public String forageForIngredient(Player player) {
                return forageForIngredientService.forageForIngredient(player);
        }


}
