package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.services.ApplicationModeService;

public class ApplicationModeHandler {
    private static ApplicationModeHandler instance = null;
    private ApplicationModeService applicationModeService;

    private ApplicationModeHandler() {
        applicationModeService = new ApplicationModeService();
    }

    public static ApplicationModeHandler getInstance() {
        if (instance == null) {
            instance = new ApplicationModeHandler();
        }
        return instance;
    }

    /**
     * @param mode the mode to set
     * 0 = offline
     * 1 = online
     **/
    public void setApplicationMode(Integer mode) {
        applicationModeService.setApplicationMode(mode);
    }

    /**
     * @return the mode
     * 0 = offline
     * 1 = online
     **/
    public Integer getApplicationMode() {
        return applicationModeService.getApplicationMode();
    }
}
