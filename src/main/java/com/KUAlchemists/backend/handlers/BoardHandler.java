package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.network.NetworkHandler;
import com.KUAlchemists.backend.observer.PlayerObserver;
import com.KUAlchemists.backend.states.GameEngineState;
import com.KUAlchemists.backend.states.GameTurnState;
import com.KUAlchemists.backend.states.State;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class BoardHandler {


    private static BoardHandler INSTANCE = null;

    private HashMap<Player, ArrayList<Object>> notificationMap;


    private BoardHandler() {
        this.notificationMap = new HashMap<>();
        //notify the clients
        if(GameEngine.getInstance().getCurrentPlayer().getUserType() == UserType.HOST)
        {
            ArrayList<State> states = new ArrayList<>();
            GameTurnState gameTurnState = new GameTurnState(0);
            states.add(gameTurnState);
            NetworkHandler.getInstance().handleSendData(states);
        }

    }


    public static BoardHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BoardHandler();
        }
        return INSTANCE;
    }


    public boolean isItCurrentPlayerTurn() {
        return GameEngine.getInstance().getCurrentPlayer().getId() == GameEngine.getInstance().getCurrentClientID();
    }

    /**
     * This method is called when a player wants to end the tour.
     * @return The next tour.
     * @see GameEngine#nextTourOffline()
     */
    public ArrayList<Integer> endOfflineTour() {
        //TODO: reset action points in backend
        ArrayList<Integer> result = GameEngine.getInstance().nextTourOffline();
        //if it is final tour, then end the round return -1 -1

        //if current player is first player, update action points to 5
        if (GameEngine.getInstance().getCurrentPlayerIndex() == 0) {
            for(Player player : GameEngine.getInstance().getPlayerList()){
                player.setActionPoints(5);
            }
        }


        return result;
    }


    /**
     * This method is called when a player wants to end the tour.
     * @return The next tour.
     * @see GameEngine#nextTourOnline()
     */

    public ArrayList<Integer> endOnlineTour() {
        ArrayList<Integer> result = GameEngine.getInstance().nextTourOnline();
        System.out.println("PlayerList size: " + GameEngine.getInstance().getPlayerList().size());
        GameTurnState gameTurnState = new GameTurnState(GameEngine.getInstance().getCurrentClientID());
        ArrayList<State> states = new ArrayList<>();
        states.add(gameTurnState);
        NetworkHandler.getInstance().handleSendData(states);
        //TO-DO Update action points

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

    public void notifyOtherClientsForFinalScoring() {
        //TODO: notify other clients for final scoring


    }
}
