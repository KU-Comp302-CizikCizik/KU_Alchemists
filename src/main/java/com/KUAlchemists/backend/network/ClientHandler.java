package com.KUAlchemists.backend.network;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.states.GameEngineState;
import com.KUAlchemists.backend.states.PlayerInitState;
import com.KUAlchemists.backend.states.State;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Server server;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.clientSocket = socket;
        this.server = server;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (clientSocket.isConnected()) {
                Object data = inputStream.readObject();
                // Handle the message, broadcast to other clients
                System.out.println("Received message from client: " + data);
                CopyOnWriteArrayList<State> newStates = (CopyOnWriteArrayList<State>) GameUpdateHandler.getInstance().handleUpdateGame((List<State>) data);
                server.broadcast(newStates,this);

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            server.removeClientHandler(this);
            closeConnections();
        }
    }

    public void send(Object message) throws IOException {
        System.out.println("Sending message to client: " + message);
        outputStream.writeObject(message);
        outputStream.flush();
        outputStream.reset();

    }

    private void closeConnections() {
        try {
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
