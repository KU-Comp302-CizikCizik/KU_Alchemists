package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.BuyArtifactService;

import java.util.List;

/**
 * This class is responsible for handling buy artifact requests.
 */
public class BuyArtifactHandler {

    private final BuyArtifactService buyArtifactService;

    private static BuyArtifactHandler INSTANCE;

    public BuyArtifactHandler() {
        this.buyArtifactService = new BuyArtifactService();
    }



    public static BuyArtifactHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BuyArtifactHandler();
        }
        return INSTANCE;
    }


    /**
     * Handles a request to buy an artifact.
     *
     * @param artifactName The name of the artifact to buy.
     * @return A string message indicating the result of the transaction.
     */
    public boolean handleBuyArtifactRequest(String artifactName) {
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        boolean success = buyArtifactService.buyArtifact(currentPlayer, artifactName);
        return success;
    }


    /**
     * Sends a list of bought artifact(s)
     * @return ArrayList <String>
     */
    //bu method değişebilir mahmutla kontakta kal!!!!!!!!!!!!!!!!!!!
    public List<String> handleGetArtifacts(){ //This method prevents user to buy an artifact that already have been bought.

        return buyArtifactService.getArtifacts() ;
    }
    public List<String> handleBoughtArtifacts(){ //This method prevents user to buy an artifact that already have been bought.

        return buyArtifactService.getBoughtArtifacts();
    }


}
