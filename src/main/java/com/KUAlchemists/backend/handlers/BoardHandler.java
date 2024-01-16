package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.observer.PlayerObserver;
import com.KUAlchemists.backend.services.WisdomIdolService;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardHandler {


    private static BoardHandler INSTANCE = null;


    private WisdomIdolService wisdomIdolService;


    private BoardHandler() {
    }

    public static BoardHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BoardHandler();
        }
        return INSTANCE;
    }

    /**
     * This method is called when a player wants to end the tour.
     * @return The next tour.
     * @see GameEngine#nextTour()
     */
    public ArrayList<Integer> endTheTour() {
        //TODO: reset action points in backend
        ArrayList<Integer> result = GameEngine.getInstance().nextTour();
        //if it is final tour, then end the round return -1 -1

        //if current player is first player, update action points to 5
        if (GameEngine.getInstance().getCurrentPlayerIndex() == 0) {
            for(Player player : GameEngine.getInstance().getPlayerList()){
                player.setActionPoints(5);
            }
        }
        return result;
    }

    public void registerPlayerObserver(PlayerObserver playerObserver) {
        for(Player player : GameEngine.getInstance().getPlayerList()){
            player.registerObserver(playerObserver);
        }
    }

    public Integer getPlayerGold(int playerIndex) {
        return GameEngine.getInstance().getPlayer(playerIndex).getGold();
    }

    public Integer getPlayerReputation(int playerIndex) {
        return GameEngine.getInstance().getPlayer(playerIndex).getReputation();
    }

    public Integer getPlayerActionPoints(int playerIndex) {
        return GameEngine.getInstance().getPlayer(playerIndex).getActionPoints();
    }


    public ArrayList<String> getAvatarStrings(){
        ArrayList<String> avatarStrings = new ArrayList<>();
        for (Player player : GameEngine.getInstance().getPlayerList()) {
            avatarStrings.add(player.getAvatar());
        }
        return avatarStrings;
    }

    // HashMap that has player that has the wisdom idol and the theory name (ingredient name) that is debunked
    public HashMap<Player, String> getNotificationMap() {
        return wisdomIdolService.getNotificationMap();
    }
}
