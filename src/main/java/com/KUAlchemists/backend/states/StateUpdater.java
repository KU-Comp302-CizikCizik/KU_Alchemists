package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
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
        // updates the game engine
        ArrayList<Player> playerArrayList = gameEngineState.getPlayerArrayList();
        if(playerArrayList.size() == 0 || gameEngineState == null) return;

        GameEngine.getInstance().setPlayerList(playerArrayList);
        int currPlayerIndex = 0;
        if(GameEngine.getInstance().getCurrentPlayer().getUserType() == UserType.CLIENT){
            currPlayerIndex = GameEngine.getInstance().getPlayerList().size()-1;
        }
        GameEngine.getInstance().setCurrentPlayerIndex(currPlayerIndex);
        GameEngine.getInstance().setCurrentPlayer(GameEngine.getInstance().getPlayer(currPlayerIndex));
    }

    public void updateBoard(BoardState boardState) {
        Board.getInstance().setPublishedTheoriesList(boardState.getPublishedTheoriesList());
    }


    public void updatePlayerInit(PlayerInitState playerInitState) {


    }
}
