package com.KUAlchemists.adapters;

import com.KUAlchemists.backend.handlers.NetworkHandler;
import com.KUAlchemists.backend.initializers.OfflineInitializer;
import com.KUAlchemists.backend.initializers.OnlineInitializer;

public class OnlineInitializationAdapter implements OfflineInitializer {

    private OnlineInitializer onlineInitializer;

    private int port;

    private String ipAddress;


    public OnlineInitializationAdapter(OnlineInitializer onlineInitializer){
        this.onlineInitializer = onlineInitializer;
        this.port = NetworkHandler.DEFAULT_PORT;
        this.ipAddress = NetworkHandler.DEFAULT_IP;
    }

    @Override
    public void offlineInitialize() {
        onlineInitializer.onlineInitialize(port,ipAddress);
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
