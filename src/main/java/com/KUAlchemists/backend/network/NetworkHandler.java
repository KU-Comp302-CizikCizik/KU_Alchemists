package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.states.State;

import java.util.ArrayList;
import java.util.List;

public class NetworkHandler {
    private static NetworkHandler instance;
    private NetworkService service;

    public static final String DEFAULT_IP = "localhost";
    public static final int DEFAULT_PORT = 7777;


    private NetworkHandler()
    {
        this.service = new NetworkService();
    }

    public static NetworkHandler getInstance(){
        if (instance == null){
            instance = new NetworkHandler();
        }
        return instance;
    }

    public void handleConnect(String ip, int port){
        service.connectToServer(ip, port);
        GameEngine.getInstance().setUserTypeOfCurrentPlayer(UserType.CLIENT);

    }

    public void handleStartServer(int port){
        service.startServer(port);
        GameEngine.getInstance().setUserTypeOfCurrentPlayer(UserType.HOST);
    }

    /**
     * This method will be called by UI to send data
     */
    public void handleSendData(){
        service.sendData();
    }

    public void handleSendData(List<State> state){
        service.sendData(state);

    }


    public void handleSendDataWith(ArrayList<State> states) {
        service.sendDataWith(states);
    }
}
