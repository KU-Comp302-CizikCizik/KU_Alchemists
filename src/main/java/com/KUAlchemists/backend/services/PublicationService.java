package com.KUAlchemists.backend.services;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.models.Alchemical;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.services.IngredientService;
/**
 * Handles the business logic for publishing and endorsing theories in the game.
 */
public class PublicationService {


    private final IngredientService ingredientService;

    public PublicationService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    /**
     * Publishes a theory if the player decides to do so.
     * It handles the logic of changing the state of the theory to published and adjusting the player's reputation.
     *
     * @param player The player who is publishing the theory.
     * @param theory The theory to be published.
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
            player.setGold(player.getGold() - 1); // Pay the publisher


            theory.setPublished(true); // Set the theory as published
            player.setReputation(player.getReputation() + 1); // Increment the player's reputation

            // Additional logic to update the game state if necessary
            return true;
        }
        return false;
    }
}

