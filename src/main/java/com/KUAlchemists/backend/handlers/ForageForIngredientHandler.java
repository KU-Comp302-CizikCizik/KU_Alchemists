package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.services.ForageForIngredientService;

/**
 * This class is responsible for handling forage for ingredient requests.
 */
public class ForageForIngredientHandler {

        private final ForageForIngredientService forageForIngredientService;
        public ForageForIngredientHandler() {
                forageForIngredientService = new ForageForIngredientService(GameEngine.getCurrentPlayer());
        }

        public void forageForIngredient() {
                forageForIngredientService.forageForIngredient();
        }


}
