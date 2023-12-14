package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.services.PublicationTrackService;

public class PublicationTrackHandler {
    private final PublicationTrackService publicationTrackService;

    private static PublicationTrackHandler INSTANCE;

    public PublicationTrackHandler() {
        this.publicationTrackService = new PublicationTrackService();
    }

    public static PublicationTrackHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PublicationTrackHandler();
        }
        return INSTANCE;
    }

    public String handleGetAllPublishedTheoriesInfo() {
        return publicationTrackService.getAllPublishedTheoriesInfo();
    }

    public String handleGetPlayerPublishedTheoriesInfo() {
        return publicationTrackService.getPublishedTheoriesInfo(GameEngine.getInstance().getCurrentPlayer());
    }

    // Other methods as needed...
}
