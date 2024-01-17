package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.managers.EventManager;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;

public class StateUpdater {
    public void updatePlayer(PlayerState playerState) {

    }

    public void updateGameEngine(GameEngineState gameEngineState) {
        // updates the game engine
        ArrayList<Player> playerArrayList = gameEngineState.getPlayerArrayList();
        if(playerArrayList.size() == 0 || gameEngineState == null) return;

        GameEngine.getInstance().setPlayerList(playerArrayList);
        int currPlayerIndex = 0;
        if(GameEngine.getInstance().getCurrentPlayer().getUserType() == UserType.CLIENT && GameEngine.getInstance().getCurrentPlayerIndex() == 0){
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

    public void updateGameStatus(GameStatusState state){
        if(GameEngine.getInstance().getUserType() == UserType.HOST){
            return;
        }
        EventManager.getInstance().onGameStatusChanged(state.getStatus());

    }

    public void updateGameTurn(GameTurnState gameTurnState) {
        GameEngine.getInstance().setCurrentClientID(gameTurnState.getGameTurn());
        EventManager.getInstance().onGameTurnChanged(gameTurnState.getGameTurn());
    }
}
