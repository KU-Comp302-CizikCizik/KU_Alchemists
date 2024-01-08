package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.network.Client;
import com.KUAlchemists.backend.network.Server;
import com.KUAlchemists.backend.states.GameEngineState;
import com.KUAlchemists.backend.states.State;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for the network related operations.
 */
public class NetworkService {
    private Server server;
    private Client client;
    private static NetworkService instance;

    private NetworkService(){}

    public static NetworkService getInstance(){
        if (instance == null){
            instance = new NetworkService();
        }
        return instance;
    }

    /**
     * This method starts the server with the given port
     * @param port
     */
    public void startServer(int port){
        // Start the server in a new thread so it doesn't block the UI
        new Thread(() -> {
            server = new Server(port); // Use an appropriate port for your game
            try {
                server.startServer();
            } catch (IOException e) {
                e.printStackTrace(); // Handle this exception properly
            }
        }).start();
    }

    public void connectToServer(String ip, int port){
        client = new Client(ip, port);
        try {
            client.connect();
            client.listenForMessages(); // Start listening for messages from the server
            // Once connected, change UI to the game room
        } catch (IOException e) {
            e.printStackTrace(); // Handle this exception properly
        }
    }

    public void sendDataToServer(){
        Object data = getStates();
        try {
            client.send(data);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public  void sendDataToClient(){
        Object data = getStates();
        server.broadcast(data);
    }

    public List<State> getStates(){
        List<State> states = new ArrayList<>();
        states.add(Board.getInstance().getState());
        states.add(GameEngine.getInstance().getCurrentPlayer().getState());
        states.add(GameEngine.getInstance().getState());
        return states;
    }

}
