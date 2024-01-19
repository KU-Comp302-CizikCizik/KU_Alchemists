package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.services.PublishTheoryService;

import java.util.List;

public class PublishTheoryHandler {
    private final PublishTheoryService publishTheoryService;
    private static PublishTheoryHandler INSTANCE;
    private String selectedIngredientName;
    private String predictedRedAspectString;  // New: Predicted red aspect
    private String predictedGreenAspectString;  // New: Predicted green aspect
    private String predictedBlueAspectString;  // New: Predicted blue aspect
    private List<String> selectedTheorySeals;


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

    // New Setter for predicted red aspect
    public void setPredictedRedAspectString(String predictedRedAspectString) {
        this.predictedRedAspectString = predictedRedAspectString;
    }

    // New Setter for predicted green aspect
    public void setPredictedGreenAspectString(String predictedGreenAspectString) {
        this.predictedGreenAspectString = predictedGreenAspectString;
    }

    // New Setter for predicted blue aspect
    public void setPredictedBlueAspectString(String predictedBlueAspectString) {
        this.predictedBlueAspectString = predictedBlueAspectString;
    }
    // New Setter for selectedTheorySeals represented as strings
    public void setSelectedTheorySeals(List<String> selectedTheorySeals) {
        this.selectedTheorySeals = selectedTheorySeals;
    }


    public String handlePublishTheoryRequest() {
        if (GameEngine.getInstance().getCurrentPlayer().getActionPoints() < 1) {
            return "You don't have enough action points to publish a theory.";
        }
        boolean success = publishTheoryService.publishTheory(
                selectedIngredientName,
                predictedRedAspectString,
                predictedGreenAspectString,
                predictedBlueAspectString,
                selectedTheorySeals);

        if (success) {
            return selectedIngredientName + " " + predictedRedAspectString + predictedGreenAspectString + predictedBlueAspectString ;
        } else {
            return "Failed to publish theory.";
        }
    }
}