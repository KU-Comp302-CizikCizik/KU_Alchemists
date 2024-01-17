package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.ApplicationMode;

/**
 * This class is responsible for handling application mode requests.
 * This class is a singleton.
 */
public class ApplicationModeHandler {

    /**
     * The singleton instance of ApplicationModeHandler.
     */
    private static ApplicationModeHandler INSTANCE;


    /**
     * Gets the singleton instance of ApplicationModeHandler.
     *
     * @return The singleton instance of ApplicationModeHandler.
     */
    public static ApplicationModeHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ApplicationModeHandler();
        }
        return INSTANCE;
    }

    /**
     * Constructor for ApplicationModeHandler
     */
    private ApplicationModeHandler() {
        setApplicationMode(ApplicationMode.OFFLINE);
    }

    /**
     * Sets the application mode.
     *
     * @param mode The application mode.
     */
    public void setApplicationMode(ApplicationMode mode) {
        GameEngine.getInstance().setApplicationMode(mode);
    }

    /**
     * Gets the application mode.
     *
     * @return The application mode.
     */

    public ApplicationMode getApplicationMode() {
        return GameEngine.getInstance().getApplicationMode();
    }

}
