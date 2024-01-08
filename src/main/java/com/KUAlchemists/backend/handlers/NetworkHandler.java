package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.services.NetworkService;

public class NetworkHandler {
    private static NetworkHandler instance;
    private NetworkService service;

    public static final String DEFAULT_IP = "localhost";
    public static final int DEFAULT_PORT = 7777;


    private NetworkHandler()
    {
        this.service = NetworkService.getInstance();
    }

    public static NetworkHandler getInstance(){
        if (instance == null){
            instance = new NetworkHandler();
        }
        return instance;
    }

    public void handleConnect(String ip, int port){
        service.connectToServer(ip, port);
        GameEngine.getInstance().setUserTypeOfCurrentPlayer(UserType.HOST);

    }

    public void handleStartServer(int port){
        service.startServer(port);
        GameEngine.getInstance().setUserTypeOfCurrentPlayer(UserType.CLIENT);
    }

    /**
     * This method will be called by UI to send data
     */
    public void handleSendData(){
        UserType userType = GameEngine.getInstance().getUserType();
        if (userType == UserType.HOST){
            handleSendDataToClient();
        }
        else{
            handleSendDataToServer();
        }
    }
    public void handleSendDataToServer(){
        service.sendDataToServer();
    }

    public void handleSendDataToClient(){
        service.sendDataToClient();
    }
}
