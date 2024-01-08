package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;

public class StateUpdater {
    public void updatePlayer(PlayerState playerState) {
        // updates the player
        int playerID = playerState.getId();
        Player player = findPlayer(playerID);
        player.setGold(playerState.getGold());
    }

    public Player findPlayer(int id) throws NullPointerException{
        ArrayList<Player> players = GameEngine.getInstance().getPlayerList();
        for (Player player : players){
            if (player.getId() == id){
                return player;
            }
            else {
                throw new NullPointerException();
            }
        }
        return null;
    }

    public void updateGameEngine(GameEngineState gameEngineState) {

    }

    public void updateBoard(BoardState boardState) {
        Board.getInstance().setPublishedTheoriesList(boardState.getPublishedTheoriesList());
    }

    public void updatePlayerInit(PlayerInitState playerInitState) {


    }


}
