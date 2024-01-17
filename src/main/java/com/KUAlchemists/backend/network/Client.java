package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.KUAlchemists.backend.states.GameEngineState;
import com.KUAlchemists.backend.states.State;

public class Client {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private String serverAddress;
    private int serverPort;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect() throws IOException {
        // Connect to the server
        socket = new Socket(serverAddress, serverPort);
        System.out.println("Connected to the server at " + serverAddress + ":" + serverPort);
        // Setup streams
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Object data) throws IOException {
        if (socket.isConnected()) {
            outputStream.writeObject(data);
            outputStream.flush();
            System.out.println("Data sent to the server: " + data);
        }
    }

    public Object receive() throws IOException, ClassNotFoundException {
        if (socket.isConnected()) {
            System.out.println("Data recieved from the server:");
            Object data = inputStream.readObject();
            //
            List<State> temp = getStatesTemp(data);
            ArrayList<Player> list = getPlayerList(temp);
            System.out.println(GameEngine.getInstance().getCurrentPlayer().getUserType() + " has the ");
            System.out.println(GameEngine.getInstance().getCurrentPlayer().getId() + " id");
            for(Player player: list) {
                System.out.println("User type: " + player.getUserType() + " ID:" + player.getId());
            }
            //
            return data;
        }
        return null;
    }

    private ArrayList<Player> getPlayerList(List<State> temp) {
        if(temp != null) {
            for(State s : temp) {
                if(s instanceof GameEngineState) {
                    return ((GameEngineState) s).getPlayerArrayList();
                }
            }
        }
        return null;
    }

    private List<State> getStatesTemp(Object data) {
            if(data instanceof List) {
                return (List<State>) data;
            }
            return null;
    }

    public void closeConnection() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println("Disconnected from the server.");
    }


    // Example method for receiving messages from the server
    public void listenForMessages() {
        new Thread(() -> {
            try {
                while (!socket.isClosed()) {
                    Object data = receive();
                    System.out.println("Received message from server: " + data);
                    handleReceivedMessage((List<State>) data);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Implement how you want to handle the received message
    private void handleReceivedMessage(List<State> states) {
        // Update game state, UI, etc., based on the received message
        GameUpdateHandler.getInstance().handleUpdateGame(states);
    }
}
