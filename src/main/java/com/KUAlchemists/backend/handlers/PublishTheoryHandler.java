package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.services.PublishTheoryService;

public class PublishTheoryHandler {
    private final PublishTheoryService publishTheoryService;

    private static PublishTheoryHandler INSTANCE;

    private String selectedIngredientName;

    public PublishTheoryHandler() {
        this.publishTheoryService = new PublishTheoryService();
    }

    public static PublishTheoryHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PublishTheoryHandler();
        }
        return INSTANCE;
    }
    public void setSelectedIngredientName(String selectedIngredientName) {
        this.selectedIngredientName = selectedIngredientName;
    }

    public String getSelectedIngredientName() {
        return selectedIngredientName;
    }


    public String handlePublishTheoryRequest(
                                             String predictedRedAspectString,
                                             String predictedGreenAspectString,
                                             String predictedBlueAspectString) {
        boolean success = publishTheoryService.publishTheory(
                selectedIngredientName,
                predictedRedAspectString,
                predictedGreenAspectString,
                predictedBlueAspectString);

        if (success) {
            return selectedIngredientName + " " + predictedRedAspectString + predictedGreenAspectString+ predictedBlueAspectString ;
        } else {
            return "Failed to publish theory.";
        }
    }
}