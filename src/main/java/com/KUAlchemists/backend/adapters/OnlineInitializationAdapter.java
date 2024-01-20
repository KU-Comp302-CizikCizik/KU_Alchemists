package com.KUAlchemists.backend.adapters;

import com.KUAlchemists.backend.enums.UserType;
import com.KUAlchemists.backend.network.NetworkHandler;
import com.KUAlchemists.backend.initializers.OfflineInitializer;
import com.KUAlchemists.backend.initializers.OnlineInitializer;

public class OnlineInitializationAdapter implements OfflineInitializer {

    private OnlineInitializer onlineInitializer;

    private int port;

    private String ipAddress;

    private UserType userType;


    public OnlineInitializationAdapter(OnlineInitializer onlineInitializer){
        this.onlineInitializer = onlineInitializer;
        this.port = NetworkHandler.DEFAULT_PORT;
        this.ipAddress = NetworkHandler.DEFAULT_IP;
    }

    @Override
    public void offlineInitialize() {
        onlineInitializer.onlineInitialize(port,ipAddress, userType);
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
