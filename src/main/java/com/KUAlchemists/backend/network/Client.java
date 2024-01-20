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
import com.KUAlchemists.ui.SceneLoader;

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

    public void connect() {
        // Connect to the server
        try {
            socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to the server at " + serverAddress + ":" + serverPort);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            SceneLoader.getInstance().loadGenericPopUp("Error: Could not connect to the server.");
            throw new RuntimeException(e);
        }

    }

    public void send(Object data) throws IOException {
        if (socket.isConnected()) {
            outputStream.writeObject(data);
            outputStream.flush();
            outputStream.reset();
            System.out.println("Data sent to the server: " + data);
        }
    }

    public Object receive() throws IOException, ClassNotFoundException {
        if (socket.isConnected()) {
            System.out.println("Data recieved from the server:");
            Object data = inputStream.readObject();
            return data;
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
                    GameUpdateHandler.getInstance().handleUpdateGame((List<State>) data);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
