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

    public String handlePublishRequest(String ingredientName, String predictedRedAspectString, String predictedGreenAspectString, String predictedBlueAspectString) {
        boolean success = publicationService.publishTheory(GameEngine.getInstance().getCurrentPlayer(), ingredientName, predictedRedAspectString,predictedGreenAspectString, predictedBlueAspectString);
        if (success) {
            return "Theory published successfully!";
        } else {
            return "Failed to publish theory.";
        }
    }

    public String handleGetPublishedTheoriesInfo(){
        return publicationService.getPublishedTheoriesInfo(GameEngine.getInstance().getCurrentPlayer());
    }

    // Other handler methods...
}
