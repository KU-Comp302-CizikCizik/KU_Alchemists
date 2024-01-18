package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.states.GameEngineState;
import com.KUAlchemists.backend.states.PlayerInitState;
import com.KUAlchemists.backend.states.State;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameUpdateHandler {
    private static GameUpdateHandler instance;
    private GameUpdateService service;

    private boolean shouldIDInit;


    private GameUpdateHandler(){
        this.service = new GameUpdateService();

        if(GameEngine.getInstance().getCurrentPlayer().getUserType() == UserType.HOST)
            shouldIDInit = true;
        else
            shouldIDInit = false;
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
        CopyOnWriteArrayList<State> newStates = new CopyOnWriteArrayList<>(states);

        if(GameEngine.getInstance().getUserType() == UserType.HOST && shouldIDInit){
            newStates.clear();
            newStates.addAll(handleInitializeClientIDS(states));
        }
        service.update(newStates);
        return newStates;

    }

    private List<State> handleInitializeClientIDS(List<State> states){
        for (State s : states){
            if( s instanceof PlayerInitState){
                CopyOnWriteArrayList<State> newStates = service.initClientIDs(states);
                return newStates;
            }
        }
        return states;
    }

    public void setShouldIDInit(boolean shouldIDInit){
        this.shouldIDInit = shouldIDInit;
    }

    public boolean getShouldIDInit() {
        return shouldIDInit;
    }
}
