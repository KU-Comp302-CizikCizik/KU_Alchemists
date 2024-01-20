package com.KUAlchemists.backend.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private int port;
    //private ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    CopyOnWriteArraySet<ClientHandler> handlers = new CopyOnWriteArraySet<>();

    private ServerSocket serverSocket;

    private ExecutorService pool; // For handling multiple client connections


    private static final AtomicInteger numberOfPlayers = new AtomicInteger(1);

    public Server(int port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
        pool = java.util.concurrent.Executors.newFixedThreadPool(10);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            handlers.add(clientHandler);
            pool.execute(clientHandler);
            System.out.println("New client connected: " + clientSocket.getInetAddress());
        }
    }

    public void broadcast(Object message) {
        for (ClientHandler clientHandler : handlers) {
            try {
                System.out.println("Sending message to client: " + clientHandler);
                clientHandler.send(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcast(Object message,ClientHandler filter) {
        for (ClientHandler clientHandler : handlers) {
            try {
                if (filter != clientHandler) {
                    System.out.println("Sending message to client: " + clientHandler);
                    clientHandler.send(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeClientHandler(ClientHandler clientHandler) {
        handlers.remove(clientHandler);
        System.out.println("Client disconnected: " + clientHandler.getClientSocket().getInetAddress());
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers.get();
    }

    public static int incrementNumberOfPlayers() {
        return numberOfPlayers.incrementAndGet();
    }


}
