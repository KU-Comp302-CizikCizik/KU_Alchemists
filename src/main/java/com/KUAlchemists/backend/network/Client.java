package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.KUAlchemists.backend.network.State;

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
            System.out.println("Waiting for data from the server...");
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

    // Example usage of the client to send a message
    public void sendMessage(String text) throws IOException {
        send(new Player(text));
    }

    // Example method for receiving messages from the server
    public void listenForMessages() {
        new Thread(() -> {
            try {
                while (!socket.isClosed()) {
                    Object data = receive();
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
