package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.PlayerSeal;
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
    //This method crucial for establishing an unique communication channel with each client. Keep it explicit before refactoring
    //Only host executes this method
    public List<State> initClientIDs(List<State> states) {
        GameEngineState gameEngineState;

        ArrayList<State> result = new ArrayList<>();

        PlayerInitState playerState = null;

        for (State s : states){
            if (s instanceof PlayerInitState){
                playerState = (PlayerInitState) s;
                break;
            }
        }

        Player player = playerState.getPlayer();
        player.setPlayerID(Server.incrementNumberOfPlayers()-1);
        player.setPlayerSeal(PlayerSeal.getRandomSeal());
        player.setIDInitializedbyHost(true);
        GameEngine.getInstance().addPlayer(player);
        gameEngineState = new GameEngineState(GameEngine.getInstance().getPlayerList());
        result.add(gameEngineState);

        return result;

    }
}
