package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.handlers.NetworkHandler;
import com.KUAlchemists.backend.states.GameEngineState;
import com.KUAlchemists.backend.states.PlayerInitState;
import com.KUAlchemists.backend.states.State;
import java.util.List;

public class GameUpdateHandler {
    private static GameUpdateHandler instance;
    private GameUpdateService service;


    private GameUpdateHandler(){
        this.service = new GameUpdateService();
    }

    public static GameUpdateHandler getInstance(){
        if (instance == null){
            instance = new GameUpdateHandler();
        }
        return instance;
    }

    /**
     * This method will be called when updated game came from server or client.
     */
    public void handleUpdateGame(List<State> states){
        if(GameEngine.getInstance().getUserType() == UserType.HOST){
            handleInitializeClientIDS(states);
        }
        service.update(states);

    }

    private void handleInitializeClientIDS(List<State> states){
        initializeClientIDS(states);

    }
    private void initializeClientIDS(List<State> states){
        for (State s : states){
            if( s instanceof PlayerInitState){
                service.initClientIDs(states);
                return;
            }
        }
    }
}
