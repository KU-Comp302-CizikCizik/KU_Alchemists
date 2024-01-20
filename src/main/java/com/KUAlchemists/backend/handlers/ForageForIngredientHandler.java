package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.ForageForIngredientService;

/**
 * This class is responsible for handling forage for ingredient requests.
 */
public class ForageForIngredientHandler {
        /**
         * The action cost for forage for ingredient.
         */
        private int ForageForIngredientActionCost = 1;
        /**
         * The singleton instance of ForageForIngredientHandler.
         */
        public static ForageForIngredientHandler INSTANCE;
        /**
         * The service for handling forage for ingredient requests.
         */
        private final ForageForIngredientService forageForIngredientService;

        /**
         * Gets the singleton instance of ForageForIngredientHandler.
         *
         * @return The singleton instance of ForageForIngredientHandler.
         */
        public static ForageForIngredientHandler getInstance(){
                if(INSTANCE == null){
                        INSTANCE = new ForageForIngredientHandler();
                }
                return INSTANCE;
        }

        /**
         * Constructor for ForageForIngredientHandler
         */
        private ForageForIngredientHandler() {
                forageForIngredientService = new ForageForIngredientService();
        }

        /**
         * Forages for an ingredient.
         *
         * @return The name of the ingredient.
         */
        public String forageForIngredient() {
                // Check if the player has enough action points
                if(GameEngine.getInstance().getCurrentPlayer().getActionPoints() < ForageForIngredientActionCost){
                        return null;
                }
                // Forage for an ingredient
                String ingredientName =  forageForIngredientService.forageForIngredient(GameEngine.getInstance().getCurrentPlayer());
                // Deduct action points
                GameEngine.getInstance().getCurrentPlayer().deduceActionPoints(ForageForIngredientActionCost);
                // Update UI with the ingredient details
                return ingredientName;
        }

        /**
         * Forages for an ingredient.
         * This method is used for game initialization.
         * @param player The player who is foraging for an ingredient.
         * @return The name of the ingredient.
         */
        public String forageForIngredient(Player player) {
                return forageForIngredientService.forageForIngredient(player);
        }


}
