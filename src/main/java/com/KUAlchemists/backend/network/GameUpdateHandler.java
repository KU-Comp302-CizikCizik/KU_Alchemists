package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.network.State;
import java.util.List;

public class GameUpdateHandler {
    private static GameUpdateHandler instance;
    private GameUpdateService service;

    private static boolean isClientIDInitializationDone;

    private GameUpdateHandler(){
        this.service = new GameUpdateService();
        isClientIDInitializationDone = true;
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
        if(!isClientIDInitializationDone){
            isClientIDInitializationDone = true;
            service.initClientIDs(states);
        }
        else{
            service.update(states);
        }

    }
}
