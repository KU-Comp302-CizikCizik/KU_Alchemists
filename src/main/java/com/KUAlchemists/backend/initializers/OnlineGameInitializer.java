package com.KUAlchemists.backend.initializers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.handlers.NetworkHandler;

public class OnlineGameInitializer implements OnlineInitializer{

    @Override
    public void onlineInitialize() {
        UserType userType = GameEngine.getInstance().getUserType();

        if(userType == UserType.HOST){
            startServer();
        }
        else if(userType == UserType.CLIENT){
            connectServer();
        }
        else{
            System.err.println("Error: Invalid user type");
        }
    }

    public void startServer(){
        NetworkHandler.getInstance().handleStartServer(NetworkHandler.PORT);
    }

    public void connectServer(){
        NetworkHandler.getInstance().handleConnect(NetworkHandler.IP,NetworkHandler.PORT);

    }

}
