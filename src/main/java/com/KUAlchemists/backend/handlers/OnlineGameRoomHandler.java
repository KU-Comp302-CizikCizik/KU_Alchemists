package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.adapters.OnlineInitializationAdapter;
import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.initializers.OnlineGameInitializer;

public class OnlineGameRoomHandler {

    private static OnlineGameRoomHandler INSTANCE;

    private OnlineInitializationAdapter onlineInitializationAdapter;

    private OnlineGameRoomHandler(){
        onlineInitializationAdapter = new OnlineInitializationAdapter(new OnlineGameInitializer());

    }

    public static OnlineGameRoomHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new OnlineGameRoomHandler();
        }
        return INSTANCE;
    }

    public void startAsHost(int port) {
        onlineInitializationAdapter.setUserType(UserType.HOST);
        onlineInitializationAdapter.setPort(port);
        onlineInitializationAdapter.offlineInitialize();
    }

    public void startAsClient(String ipAddress, int port){
        onlineInitializationAdapter.setUserType(UserType.CLIENT);
        onlineInitializationAdapter.setIpAddress(ipAddress);
        onlineInitializationAdapter.setPort(port);
        onlineInitializationAdapter.offlineInitialize();

    }
}
