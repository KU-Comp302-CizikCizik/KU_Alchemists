package com.KUAlchemists.backend.initializers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.handlers.NetworkHandler;

public class OnlineGameInitializer implements OnlineInitializer{

    @Override
    public void onlineInitialize(int port, String ipAddress) {
        UserType userType = GameEngine.getInstance().getUserType();

        if(userType == UserType.HOST){
            startServer(port);
        }
        else if(userType == UserType.CLIENT){
            connectServer(port, ipAddress);
        }
        else{
            System.err.println("Error: Invalid user type");
        }
    }

    public void startServer(int port){
        NetworkHandler.getInstance().handleStartServer(port);
    }

    public void connectServer(int port, String ipAddress){
        NetworkHandler.getInstance().handleConnect(ipAddress, port);
        NetworkHandler.getInstance().handleSendDataToServer();

    }

}
