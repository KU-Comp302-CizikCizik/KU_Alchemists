package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;

public class WisdomIdolService {

    public WisdomIdolService() {
    }

    /**
     * Activates the Wisdom Idol for the given player.
     *
     * @param player The player who is activating the Wisdom Idol.
     */
    public void activateWisdomIdol(Player player) {
        // TODO - implement WisdomIdolService.activateWisdomIdol
        if (Board.getInstance().getArtifactStorage(player).getArtifactByName("wisdom_idol") != null) {
            Board.getInstance().getArtifactStorage(player).getArtifactByName("wisdom_idol").setActivated(true);
        }
    }
    /**
     * Applies the effects of the Wisdom Idol when a player's theory is debunked.
     *
     * @param debunkedTheory The theory that has been debunked.
     */
    public void applyWisdomIdolOnDebunk(Theory debunkedTheory) {
        Player author = GameEngine.getInstance().getCurrentPlayer();

        if (author.getPublishedTheories().contains(debunkedTheory) && Board.getInstance().getArtifactStorage(author).getArtifactByName("wisdom_idol").isActivated()) {
            // If the Wisdom Idol is active, the player does not lose reputation points for the debunked theory.
            // another if statement to check if the player is not the author of the theory but endorsed it.
            // No action needed here as the player's reputation remains unchanged,
            // and the Wisdom Idol is removed in the player's ArtifactStorage
            // kaybedilen reputation pointlerin geri alınması gerekiyor.

        } else {
            //else if the player is not the author of the theory, the player does not lose reputation points for the debunked theory.
            // No action needed here as the player's reputation remains unchanged.
        }

    }
    public void rewardPlayerAtGameEnd(Player player) {
        if (Board.getInstance().getArtifactStorage(player).getArtifactByName("wisdom_idol") != null) {
            player.setReputation(1); // Assuming a method to add reputation points
        }
    }



}