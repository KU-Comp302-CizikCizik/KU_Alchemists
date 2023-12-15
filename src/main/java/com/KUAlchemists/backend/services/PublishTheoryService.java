package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.models.*;
public class PublishTheoryService {

    public PublishTheoryService() {
    }

    /**
     * Publishes a new theory.
     * @param ingredientName Name of the ingredient in the theory.
     * @param predictedRedAspectString Predicted red aspect.
     * @param predictedGreenAspectString Predicted green aspect.
     * @param predictedBlueAspectString Predicted blue aspect.
     * @return True if the theory is successfully published, false otherwise.
     */
    public boolean publishTheory(String ingredientName,
                                 String predictedRedAspectString,
                                 String predictedGreenAspectString,
                                 String predictedBlueAspectString) {
        Player player = GameEngine.getInstance().getCurrentPlayer();
        Aspect redAspect = Aspect.fromString(predictedRedAspectString);
        Aspect greenAspect = Aspect.fromString(predictedGreenAspectString);
        Aspect blueAspect = Aspect.fromString(predictedBlueAspectString);
        Alchemical predictedAlchemical = new Alchemical(redAspect, greenAspect, blueAspect);

        Ingredient ingredient = Board.getInstance().getIngredientStorage(player).getIngredient(ingredientName);

        Theory theory = new Theory(ingredient, predictedAlchemical);
        if (!theory.isPublished() && player.getGold() >= 1) {
            player.setGold(player.getGold() - 1);
            theory.setPublished(true);
            player.setReputation(player.getReputation() + 1);
            player.getPublishedTheories().add(theory);
            Board.getInstance().getPublishedTheoriesList().add(theory);
            return true;
        }
        return false;
    }
}