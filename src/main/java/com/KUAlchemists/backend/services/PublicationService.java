package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.models.Alchemical;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;

import java.util.List;

/**
 * Handles the business logic for publishing and endorsing theories in the game.
 */
public class PublicationService {


    private final IngredientService ingredientService;

    private final PlayerService playerService;

    public PublicationService() {
        this.ingredientService = new IngredientService();
        this.playerService = new PlayerService();
    }
    /**
     * Publishes a theory if the player decides to do so.
     * It handles the logic of changing the state of the theory to published and adjusting the player's reputation.
     *
     * @param player The player who is publishing the theory.
     * @param ingredientName The UI input.
     * @param predictedRedAspectString UI input
     * @return True if the theory was successfully published; otherwise, false.
     */
    public boolean publishTheory(Player player, String ingredientName, String predictedRedAspectString, String predictedGreenAspectString, String predictedBlueAspectString) {
        Aspect redAspect = Aspect.fromString(predictedRedAspectString);
        Aspect greenAspect = Aspect.fromString(predictedGreenAspectString);
        Aspect blueAspect = Aspect.fromString(predictedBlueAspectString);

        Alchemical predictedAlchemical = new Alchemical(redAspect, greenAspect, blueAspect);
        Ingredient ingredient = ingredientService.findIngredientByName(ingredientName);
        // Create a new theory with the found ingredient and alchemical
        Theory theory = new Theory(ingredient, predictedAlchemical);

        if (!theory.isPublished() && player.getGold() >= 1) { // Check if the theory is not already published and the player has enough gold
            playerService.updatePlayerGold(player.getName(), player.getGold()-1);



            theory.setPublished(true); // Set the theory as published
            playerService.updatePlayerReputation(player.getName(), player.getReputation()+1);
            // Increment the player's reputation
            playerService.addPublishedTheory(player.getName(), theory);

            // Additional logic to update the game state if necessary
            return true;
        }
        return false;
    }

    public String getPublishedTheoriesInfo(Player player){
        List<Theory> publishedTheories = player.getPublishedTheories();
        String info = "";
        for (int i = 0; i < publishedTheories.size(); i++) {
            info += publishedTheories.get(i).getIngredient().getName() +
                    " " + publishedTheories.get(i).getPredictedAlchemical().getRedAspect().toString() +
                    " " + publishedTheories.get(i).getPredictedAlchemical().getGreenAspect().toString() +
                    " " + publishedTheories.get(i).getPredictedAlchemical().getBlueAspect().toString() +
                    "\n";
        }
        return info;
    }



}

