package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.services.HelpScreenService;

public class HelpScreenHandler {

    private HelpScreenService service;

    public HelpScreenHandler() {
        this.service = new HelpScreenService();
    }

    public String handleGetHelpContent() {
        return service.getHelpContent();
    }
}
