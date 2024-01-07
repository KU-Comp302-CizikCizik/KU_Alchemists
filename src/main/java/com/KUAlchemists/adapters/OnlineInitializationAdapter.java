package com.KUAlchemists.adapters;

import com.KUAlchemists.backend.initializers.OfflineInitializer;
import com.KUAlchemists.backend.initializers.OnlineInitializer;

public class OnlineInitializationAdapter implements OfflineInitializer {

    private OnlineInitializer onlineInitializer;

    public OnlineInitializationAdapter(OnlineInitializer onlineInitializer){
        this.onlineInitializer = onlineInitializer;
    }

    @Override
    public void offlineInitialize() {
        onlineInitializer.onlineInitialize();
    }
}
