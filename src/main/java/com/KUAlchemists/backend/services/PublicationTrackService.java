package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;

import java.util.List;

public class PublicationTrackService {

    public PublicationTrackService() {
    }

    /**
     * Gets information about published theories for a given player.
     * @param player The player whose published theories information is needed.
     * @return A formatted string containing information about the player's published theories.
     */
    public String getPublishedTheoriesInfo(Player player) {
        List<Theory> publishedTheories = player.getPublishedTheories();
        StringBuilder info = new StringBuilder();
        for (Theory theory : publishedTheories) {
            info.append(theory.getIngredient().getName())
                    .append(" ")
                    .append(theory.getPredictedAlchemical().getRedAspect().toString())
                    .append(" ")
                    .append(theory.getPredictedAlchemical().getGreenAspect().toString())
                    .append(" ")
                    .append(theory.getPredictedAlchemical().getBlueAspect().toString())
                    .append("\n");
        }
        return info.toString();
    }

    /**
     * Gets information about all published theories in the game.
     * @return A formatted string containing information about all published theories.
     */
    public String getAllPublishedTheoriesInfo() {
        List<Theory> publishedTheories = Board.getInstance().getPublishedTheoriesList();
        StringBuilder info = new StringBuilder();
        for (Theory theory : publishedTheories) {
            info.append(theory.getIngredient().getName())
                    .append(" ")
                    .append(theory.getPredictedAlchemical().getRedAspect().toString())
                    .append(" ")
                    .append(theory.getPredictedAlchemical().getGreenAspect().toString())
                    .append(" ")
                    .append(theory.getPredictedAlchemical().getBlueAspect().toString())
                    .append("\n");
        }
        return info.toString();
    }
}