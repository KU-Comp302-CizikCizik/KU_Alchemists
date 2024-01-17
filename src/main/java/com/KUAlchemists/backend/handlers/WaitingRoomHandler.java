package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.enums.GameStatus;
import com.KUAlchemists.backend.network.GameUpdateHandler;
import com.KUAlchemists.backend.network.NetworkHandler;
import com.KUAlchemists.backend.states.GameStatusState;

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
        NetworkHandler.getInstance().handleSendData(new GameStatusState(GameStatus.START_GAME)); // send the game status to all players
    }
}
