package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.enums.GameStatus;
import com.KUAlchemists.backend.network.GameUpdateHandler;
import com.KUAlchemists.backend.network.NetworkHandler;
import com.KUAlchemists.backend.states.GameStatusState;
import com.KUAlchemists.backend.states.GameTurnState;
import com.KUAlchemists.backend.states.State;

import java.util.ArrayList;
import java.util.List;

public class WaitingRoomHandler {

    private static WaitingRoomHandler INSTANCE;

    private WaitingRoomHandler(){

    }

    public static WaitingRoomHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new WaitingRoomHandler();
        }
        return INSTANCE;
    }


    public void startGameForAllPlayers() {
        GameUpdateHandler.getInstance().setShouldIDInit(false);
        NetworkHandler.getInstance().handleSendData(); //send the most recent data to all players
        GameStatusState gameStatusState = new GameStatusState(GameStatus.START_GAME);
        GameTurnState gameTurnState = new GameTurnState(0);
        List<State> states = new ArrayList<>();
        states.add(gameStatusState);
        states.add(gameTurnState);
        NetworkHandler.getInstance().handleSendData(states); // send the game status to all players
    }
}
