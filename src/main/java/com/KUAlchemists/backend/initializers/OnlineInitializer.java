package com.KUAlchemists.backend.initializers;

import com.KUAlchemists.backend.enums.UserType;

public interface OnlineInitializer {

    void onlineInitialize(int port, String ipAddress, UserType userType);

}
