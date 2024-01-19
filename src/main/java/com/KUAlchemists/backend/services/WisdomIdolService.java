package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.handlers.BoardHandler;
import com.KUAlchemists.backend.models.Artifact;
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
        if (Board.getInstance().getArtifactStorage(player).getArtifactByName("wisdom_idol") != null && GameEngine.getInstance().getCurrentPlayer().getActionPoints() >= 1) {
            Board.getInstance().getArtifactStorage(player).getArtifactByName("wisdom_idol").setActivated(true);
        }
    }
    public void sendNotificationToBoardHandler(Theory selectedTheory, Player player, int deductionPoint) {
        BoardHandler.getInstance().saveNotificationToBoardHandler(selectedTheory, player, deductionPoint);
    }
    public void applyWizardIdol(Player player, int deductedPoint) {
        //negate deductedPoint and add it to the player's reputation points
        if (Board.getInstance().getArtifactStorage(player).getArtifactByName("wisdom_idol") != null && Board.getInstance().getArtifactStorage(player).getArtifactByName("wisdom_idol").isActivated()) {
            player.increaseReputationPoints(-deductedPoint);
            Board.getInstance().getArtifactStorage(player).getArtifactByName("wisdom_idol").setActivated(false); // Deactivate the artifact after use
            player.deactivateArtifact("wisdom_idol");
            Artifact artifact = Board.getInstance().getArtifactStorage(player).getArtifactByName("wisdom_idol");
            Board.getInstance().getArtifactStorage(player).removeArtifact(artifact); // Remove the artifact from storage
        }
    }
}

