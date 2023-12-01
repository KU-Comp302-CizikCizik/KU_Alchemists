package com.KUAlchemists.backend.services;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;


/**
 * Handles the business logic for publishing and endorsing theories in the game.
 */
public class PublicationService {

    /**
     * Publishes a theory if the player decides to do so.
     * It handles the logic of changing the state of the theory to published and adjusting the player's reputation.
     *
     * @param player The player who is publishing the theory.
     * @param theory The theory to be published.
     * @return True if the theory was successfully published; otherwise, false.
     */
    public boolean publishTheory(Player player, String ingredientName, String predicted_Alchemy) {
        Theory theory = new Theory();
        //bu bilgilerle theory oluşturmak gerekiyor.
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
    /**
     * Endorses a theory that has been published.
     *
     * @param theory The theory to endorse.
     * @return True if the theory was successfully endorsed; otherwise, false.
     */
//    public boolean endorseTheory(Theory theory) {
//        if (theory.isPublished() && !theory.isEndorsed()) {
//            theory.setEndorsed(true); // Set the theory as endorsed
//
//            // Additional logic to update the game state, if necessary
//            return true;
//        }
//        return false;
//    }
