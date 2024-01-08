package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.states.*;

import java.util.ArrayList;
import java.util.List;

public class GameUpdateService {


    StateUpdater stateUpdater;
    public GameUpdateService(){
        stateUpdater = new StateUpdater();
    }


    public void update(List<State> states){
        for (State s : states){
            s.update(stateUpdater);
        }
    }

    /**
     * This method will be called only once when the game starts.
     * @param states
     */
    //This method crucial for establishing an unique communication channel with each client.
    public void initClientIDs(List<State> states) {
        ArrayList<State> states1 = new ArrayList<>(states);
        GameEngineState gameEngineState = null;
        for (State s : states1){
            if (s instanceof PlayerState){
                PlayerState playerState = (PlayerState) s;
                Player player = playerState.getPlayer();
                player.setPlayerID(Server.incrementNumberOfPlayers()-1);
                GameEngine.getInstance().addPlayer(player);
                gameEngineState = new GameEngineState(GameEngine.getInstance().getPlayerList());
            }
        }

        for (State s : states1){
            if (s instanceof GameEngineState){
                states.set(states1.indexOf(s), gameEngineState);
            }
        }
    }
}
