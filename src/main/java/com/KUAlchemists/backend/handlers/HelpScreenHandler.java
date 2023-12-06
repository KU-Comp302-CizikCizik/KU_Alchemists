package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.services.HelpScreenService;

public class HelpScreenHandler {

    public static HelpScreenHandler INSTANCE;
    private HelpScreenService service;

    public static HelpScreenHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new HelpScreenHandler();
        }
        return INSTANCE;
    }

    private HelpScreenHandler() {
        this.service = new HelpScreenService();
    }

    public String handleGetHelpContent() {
        return service.getHelpContent();
    }
}
