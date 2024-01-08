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
        // updates the game engine
        ArrayList<Player> playerArrayList = gameEngineState.getPlayerArrayList();
        ArrayList<Player> existingPlayers = GameEngine.getInstance().getPlayerList();
        ArrayList<Player> newPlayers = new ArrayList<>();
        Player currentPlayer = null;

        for(Player existingPlayer: existingPlayers){
            for(Player player : playerArrayList){
                if(player.getId() == currentPlayer.getId()){
                    currentPlayer = existingPlayer;
                }
                else if(existingPlayer.getId() == player.getId()){
                    newPlayers.add(player);
                }
                else {
                    newPlayers.add(existingPlayer);
                }
            }
        }

        if(currentPlayer != null && currentPlayer != GameEngine.getInstance().getCurrentPlayer()){

        }

        else if(currentPlayer == null){
            GameEngine.getInstance().setCurrentPlayer(newPlayers.get(0));
        }
        
        for(Player player : playerArrayList){
            if(player.getId() == currentPlayer.getId()){
                currentPlayer = player;
            }
        }

        GameEngine.getInstance().setPlayerList(newPlayers);
        System.out.println("GameEngineState updated");
    }

    public void updateBoard(BoardState boardState) {
        Board.getInstance().setPublishedTheoriesList(boardState.getPublishedTheoriesList());
    }



}
