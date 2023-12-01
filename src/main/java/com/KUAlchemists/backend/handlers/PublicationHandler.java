package com.KUAlchemists.backend.handlers;
import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;
import com.KUAlchemists.backend.services.PublicationService;
public class PublicationHandler {
    private final PublicationService publicationService;

    public PublicationHandler(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    public String handlePublishRequest(String ingredientName, String predictedAlchemical) {
        boolean success = publicationService.publishTheory(GameEngine.getCurrentPlayer(), ingredientName, predictedAlchemical);
        if (success) {
            return "Theory published successfully!";
        } else {
            return "Failed to publish theory.";
        }
    }

    // Other handler methods...
}
