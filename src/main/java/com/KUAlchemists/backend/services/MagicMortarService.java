package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;

public class MagicMortarService {

    public MagicMortarService() {
    }
    /**
     * Activates the Magic Mortar for the given player.
     *
     * @param player The player who is activating the Magic Mortar.
     */
    public void activateMagicMortar(Player player) {
        if (Board.getInstance().getArtifactStorage(player).getArtifactByName("magic_mortar") != null) {
            Board.getInstance().getArtifactStorage(player).getArtifactByName("magic_mortar").setActivated(true);
        }
    }
    // magic mortar is used to keep one ingredient when you make a potion.
    // this method is called when the player makes a potion.
    // i need to implement a method to keep the ingredient.
    // i need to send the ingredient names to the front end.
    private void retainIngredient(Player player, Ingredient ingredient1, Ingredient ingredient2) {
        // Logic to allow the player to retain one of the ingredients
        // This could involve adding one ingredient back to the player's inventory
        // You will need to decide which ingredient to retain.
    }


}
