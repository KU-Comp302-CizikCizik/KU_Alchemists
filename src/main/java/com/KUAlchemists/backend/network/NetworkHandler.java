package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;

public class NetworkHandler {
    private static NetworkHandler instance;
    private NetworkService service;

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
        GameEngine.getInstance().setUserType("client");

    }

    public void handleStartServer(int port){
        service.startServer(port);
        GameEngine.getInstance().setUserType("host");
    }

    /**
     * This method will be called by UI to send data
     */
    public void handleSendData(){
        String userType = GameEngine.getInstance().getUserType();
        if (userType.equals("host")){
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
