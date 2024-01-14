package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;

import java.util.HashMap;

public class WisdomIdolService {

    private HashMap<Player, String> notificationMap;

    public WisdomIdolService() {
        this.notificationMap = new HashMap<>();
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
    public void sendNotificationToBoardHandler(Theory selectedTheory, Player player) {
        String name = selectedTheory.getIngredient().getName();
        notificationMap.put(player, name);
    }
    public void rewardPlayerAtGameEnd(Player player) {
        if (Board.getInstance().getArtifactStorage(player).getArtifactByName("wisdom_idol") != null) {
            player.setReputation(1); // Assuming a method to add reputation points
        }
    }
    public HashMap<Player, String> getNotificationMap() {
        return notificationMap;
    }




}

