package com.KUAlchemists.backend.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private int port;
    private Set<ClientHandler> clientHandlers = new HashSet<>();
    private ServerSocket serverSocket;

    private static final AtomicInteger numberOfPlayers = new AtomicInteger(1);

    public Server(int port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            clientHandlers.add(clientHandler);
            new Thread(clientHandler).start();
            System.out.println("New client connected: " + clientSocket.getInetAddress());
        }
    }

    public void broadcast(Object message) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                clientHandler.send(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeClientHandler(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
        System.out.println("Client disconnected: " + clientHandler.getClientSocket().getInetAddress());
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers.get();
    }

    public static int incrementNumberOfPlayers() {
        return numberOfPlayers.incrementAndGet();
    }


}
