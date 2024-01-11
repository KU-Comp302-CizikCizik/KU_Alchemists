package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.handlers.NetworkHandler;
import com.KUAlchemists.backend.states.GameEngineState;
import com.KUAlchemists.backend.states.PlayerInitState;
import com.KUAlchemists.backend.states.State;

import java.util.ArrayList;
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
    public List<State> handleUpdateGame(List<State> states){
        List<State> newStates = new ArrayList<>(states);
        if(GameEngine.getInstance().getUserType() == UserType.HOST) {
            newStates.clear();
            newStates.addAll(handleInitializeClientIDS(states));
        }
            service.update(newStates);
        return newStates;

    }
    private List<State> handleInitializeClientIDS(List<State> states){
        for (State s : states){
            if( s instanceof PlayerInitState){
                List<State> newStates = service.initClientIDs(states);
                return newStates;
            }
        }
        return states;
    }
}
