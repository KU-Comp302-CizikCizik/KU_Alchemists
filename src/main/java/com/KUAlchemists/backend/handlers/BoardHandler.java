package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.observer.PlayerObserver;
import com.KUAlchemists.backend.services.WisdomIdolService;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardHandler {


    private static BoardHandler INSTANCE = null;

    private HashMap<Player, ArrayList<Object>> notificationMap;



    private BoardHandler() {
        this.notificationMap = new HashMap<>();
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

    public String getAvatarPath(int playerIndex){
        for (int i = 0; i < GameEngine.getInstance().getPlayerList().size(); i++) {
            if (i == playerIndex){
                return GameEngine.getInstance().getPlayerList().get(i).getAvatar();
            }
        }
        return "";
    }



    // HashMap that has player that has the wisdom idol and the theory name (ingredient name) that is debunked
    public HashMap<Player, ArrayList<Object>> getNotificationMap() {
        return notificationMap;
    }

    public boolean isThereWisdomIdolNotification() {
        if (notificationMap.size() > 0) {
            return true;
        }
        return false;
    }

    public void saveNotificationToBoardHandler(Theory selectedTheory, Player player, int deductionPoint) {
        String ingredientName = selectedTheory.getIngredient().getName();
        ArrayList<Object> notification = new ArrayList<>();
        notification.add(ingredientName);
        notification.add(deductionPoint);
        notificationMap.put(player, notification);
    }

    public void deleteNotification(ArrayList<Object> notfication) {
         for(Player player : notificationMap.keySet()) {
                    if (notificationMap.get(player).equals(notfication)) {
                        notificationMap.remove(player);
                        break;
                    }
                }
        }
}
