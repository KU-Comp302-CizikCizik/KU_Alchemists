package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.ForageForIngredientService;

/**
 * This class is responsible for handling forage for ingredient requests.
 */
public class ForageForIngredientHandler {

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
                forageForIngredientService.forageForIngredient(GameEngine.getInstance().getCurrentPlayer());
                return "birdfeet";
        }

        public void forageForIngredient(Player player) {
                forageForIngredientService.forageForIngredient(player);
        }


}
