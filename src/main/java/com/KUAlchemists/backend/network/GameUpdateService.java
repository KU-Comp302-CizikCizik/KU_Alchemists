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
    public State initClientIDs(List<State> states) {
        for (State s : states){
            if (s instanceof PlayerState){
                PlayerState playerState = (PlayerState) s;
                UserType currentUserType = GameEngine.getInstance().getUserType();
                if(currentUserType == UserType.CLIENT) return null;
                Player player = playerState.getPlayer();
                player.setPlayerID(Server.incrementNumberOfPlayers()-1);
                GameEngine.getInstance().addPlayer(player);
                GameEngineState gameEngineState = new GameEngineState(GameEngine.getInstance().getPlayerList());
                return gameEngineState;
            }
        }
            return null;
    }
}
