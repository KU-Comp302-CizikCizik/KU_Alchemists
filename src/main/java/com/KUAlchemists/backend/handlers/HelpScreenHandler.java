package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.services.HelpScreenService;

/**
 * This class is responsible for handling the help screen.
 */
public class HelpScreenHandler {

    /**
     * The singleton instance of HelpScreenHandler.
     */
    public static HelpScreenHandler INSTANCE;
    /**
     * The service for handling the help screen.
     */
    private HelpScreenService service;

    /**
     * Gets the singleton instance of HelpScreenHandler.
     *
     * @return The singleton instance of HelpScreenHandler.
     */
    public static HelpScreenHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new HelpScreenHandler();
        }
        return INSTANCE;
    }

    /**
     * Constructor for HelpScreenHandler
     */
    private HelpScreenHandler() {
        this.service = new HelpScreenService();
    }

    /**
     * Gets the content of the help screen.
     *
     * @return The content of the help screen.
     */
    public String handleGetHelpContent() {
        return service.getHelpContent();
    }
}
