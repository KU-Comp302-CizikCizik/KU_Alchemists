package com.KUAlchemists.backend.states;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.handlers.BoardHandler;
import com.KUAlchemists.backend.managers.EventManager;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Deck;
import com.KUAlchemists.backend.models.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class StateUpdater implements Serializable {
    public void updatePlayer(PlayerState playerState) {
        Player p = null;
        for(int i =0; i < GameEngine.getInstance().getPlayerList().size();i++){
            if(playerState.getId() == GameEngine.getInstance().getPlayerList().get(i).getId()){
                p = GameEngine.getInstance().getPlayerList().get(i);
                break;
            }
        }
        p.setGold(playerState.getGold());
        p.setSicknessLevel(playerState.getSicknessLevel());
        p.setStatus(playerState.getStatus());
        p.setReputation(playerState.getReputation());
        p.setActionPoints(playerState.getActionPoints());
        p.setPublishedTheories(playerState.getPublishedTheories());
        p.setTheorySeals(playerState.getTheorySeals());
        p.setDeductionBoard(playerState.getDeductionBoard());
        p.setActionPoints(playerState.getActionPoints());
        p.setScore(playerState.getScore());
        p.setActivatedArtifacts(playerState.getActivatedArtifacts());

        EventManager.getInstance().onUpdateOnlinePlayers();

    }

    public void updateGameEngine(GameEngineState gameEngineState) {
        // updates the game engine
        ArrayList<Player> playerArrayList = gameEngineState.getPlayerArrayList();
        if(playerArrayList.size() == 0 || gameEngineState == null) return;
        int currPlayerIndex = GameEngine.getInstance().getCurrentPlayerIndex();
        String avatar = GameEngine.getInstance().getCurrentPlayer().getAvatar();
        GameEngine.getInstance().setPlayerList(new ArrayList<>(playerArrayList));

        if(GameEngine.getInstance().getCurrentPlayer().getUserType() == UserType.CLIENT && currPlayerIndex == 0){
            for(int i =0;i <GameEngine.getInstance().getPlayerList().size();i++){
                if(GameEngine.getInstance().getPlayerList().get(i).getAvatar().equals(avatar)){
                    currPlayerIndex = i;
                    break;
                }
            }
        }
        GameEngine.getInstance().setCurrentPlayer(currPlayerIndex);
        GameEngine.getInstance().setCurrentPlayerIndex(currPlayerIndex);
        EventManager.getInstance().onUpdateOnlinePlayers();

    }

    public void updateBoard(BoardState boardState) {
        Board.getInstance().setPublishedTheoriesList(boardState.getPublishedTheoriesList());
        Board.getInstance().setIngredientStorages(boardState.getIngredientStorages());
        Board.getInstance().setPotionStorages(boardState.getPotionStorages());
        Board.getInstance().setArtifactStorages(boardState.getArtifactStorages());
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
        System.out.println("Turn id : " + gameTurnState.getGameTurn());
        EventManager.getInstance().onGameTurnChanged(gameTurnState.getGameTurn());
    }

    public void updateDeck(DeckState deckState) {
        Deck.getInstance().setIngredientList(deckState.getIngredientList());
    }
}
