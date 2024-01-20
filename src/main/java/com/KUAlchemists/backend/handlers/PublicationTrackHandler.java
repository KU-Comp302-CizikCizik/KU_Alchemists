package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.managers.EventManager;
import com.KUAlchemists.backend.models.Theory;
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

    //Call this method when you click the endorse button on the theory which gives the information to the EndorseHandler
    public void selectTheoryToEndorse(String ingredientName){
        Theory theory = publicationTrackService.getTheoryByIngredientName(ingredientName);
        EventManager.getInstance().onTheorySelectedPerformed(theory);
    }

    public void selectTheoryToDebunk(String ingredientName){
        Theory theory = publicationTrackService.getTheoryByIngredientName(ingredientName);
        EventManager.getInstance().onTheorySelectedPerformed(theory);
    }

    // Other methods as needed...
}
