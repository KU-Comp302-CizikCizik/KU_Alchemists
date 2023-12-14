package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.services.PublishTheoryService;

public class PublishTheoryHandler {
    private final PublishTheoryService publishTheoryService;

    private static PublishTheoryHandler INSTANCE;

    public PublishTheoryHandler() {
        this.publishTheoryService = new PublishTheoryService();
    }

    public static PublishTheoryHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PublishTheoryHandler();
        }
        return INSTANCE;
    }

    public String handlePublishTheoryRequest(String ingredientName,
                                             String predictedRedAspectString,
                                             String predictedGreenAspectString,
                                             String predictedBlueAspectString) {
        boolean success = publishTheoryService.publishTheory(
                ingredientName,
                predictedRedAspectString,
                predictedGreenAspectString,
                predictedBlueAspectString);
        if (success) {
            return "Theory published successfully!";
        } else {
            return "Failed to publish theory.";
        }
    }
}