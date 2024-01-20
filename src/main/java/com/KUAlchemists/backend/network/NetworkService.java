package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.states.State;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for the network related operations.
 */
public class NetworkService {
    private Server server;
    private Client client;

    public NetworkService(){

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
        client.connect();
        client.listenForMessages(); // Start listening for messages from the server
        // Once connected, change UI to the game room
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

    public void sendDataToClient(List<State> state){
        Object data = state;
        server.broadcast(data);
    }

    public void sendDataToServer(List<State> state){
        Object data = state;
        try {
            client.send(data);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<State> getStates(){
        List<State> states = new ArrayList<>();
        if(!GameEngine.getInstance().getCurrentPlayer().isIDInitializedbyHost()){
            states.add(GameEngine.getInstance().getCurrentPlayer().getInitState()); // This is the state that will be sent to the server
            return states;
        }
        //states.add(Board.getInstance().getState());
        states.add(GameEngine.getInstance().getState());
        return states;
    }

    public void sendData() {
        if (GameEngine.getInstance().getUserType() == UserType.HOST) {
            sendDataToClient();

        }else {
            sendDataToServer();
        }
    }

    public void sendData(List<State> state){
        if (GameEngine.getInstance().getUserType() == UserType.HOST) {
            sendDataToClient(state);

        }else {
            sendDataToServer(state);
        }

    }

    public void sendDataWith(ArrayList<State> states) {
        states.addAll(getStates());
        sendData(states);
    }
}
