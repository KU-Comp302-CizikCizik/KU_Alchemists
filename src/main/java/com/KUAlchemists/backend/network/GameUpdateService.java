package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;

import java.util.ArrayList;
import java.util.List;

public class GameUpdateService {


    public GameUpdateService(){
    }


    public void update(List<State> states){
        for (State s : states){
            updateGame(s);
        }
    }

    public void updateGame(State state){
        if (state instanceof PlayerState){
            updatePlayer((PlayerState) state);
        }
        else if (state instanceof BoardState){
            updateBoard((BoardState) state);
        }
    }

    public void updatePlayer(PlayerState state){
        // updates the player
        int playerID = state.getId();
        Player player = findPlayer(playerID);
        player.updateState(state);
    }

    public void updateBoard(BoardState state){
        // updates the board
        Board.getInstance().updateState(state);
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

    public void initClientIDs(List<State> states) {

        for (State s : states){
            if (s instanceof PlayerState){
                PlayerState playerState = (PlayerState) s;


            }
        }

    }
}
