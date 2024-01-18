package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.managers.EventManager;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class StateUpdater implements Serializable {
    public void updatePlayer(PlayerState playerState) {

    }

    public void updateGameEngine(GameEngineState gameEngineState) {
        // updates the game engine
        ArrayList<Player> playerArrayList = gameEngineState.getPlayerArrayList();
        if(playerArrayList.size() == 0 || gameEngineState == null) return;

        int currPlayerIndex = GameEngine.getInstance().getCurrentPlayerIndex();
        if(GameEngine.getInstance().getCurrentPlayer().getUserType() == UserType.CLIENT && GameEngine.getInstance().getCurrentPlayerIndex() == 0){
            currPlayerIndex = playerArrayList.size()-1;
        }

        GameEngine.getInstance().setPlayerList(playerArrayList);
        GameEngine.getInstance().setCurrentPlayer(currPlayerIndex);
        GameEngine.getInstance().setCurrentPlayerIndex(currPlayerIndex);
    }

    public void updateBoard(BoardState boardState) {
        Board.getInstance().setPublishedTheoriesList(boardState.getPublishedTheoriesList());
    }


    public void updatePlayerInit(PlayerInitState playerInitState) {


    }

    public void updateGameStatus(GameStatusState state){
        if(GameEngine.getInstance().getUserType() == UserType.HOST){
            return;
        }
        System.out.println("Game status changed to " + state.getStatus());
        EventManager.getInstance().onGameStatusChanged(state.getStatus());

    }

    public void updateGameTurn(GameTurnState gameTurnState) {
        GameEngine.getInstance().setCurrentClientID(gameTurnState.getGameTurn());
        EventManager.getInstance().onGameTurnChanged(gameTurnState.getGameTurn());
    }

}
