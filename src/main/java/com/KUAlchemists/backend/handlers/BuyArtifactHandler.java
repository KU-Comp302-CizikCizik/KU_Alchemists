package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.BuyArtifactService;

import java.util.ArrayList;
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
    public String handleBuyArtifactRequest(String artifactName) {
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        if (currentPlayer == null) {
            return "No current player available to buy an artifact.";
        }

        boolean success = buyArtifactService.buyArtifact(currentPlayer, artifactName);

        if (success) {
            return "Artifact purchased successfully!";
        } else {
            return "Failed to purchase artifact. Not enough gold or artifact not found.";
        }
    }


    /**
     * Sends a list of bought artifact(s)
     * @return ArrayList <String>
     */

    //bu method değişebilir mahmutla kontakta kal!!!!!!!!!!!!!!!!!!!

    public List<String> getBoughtArtifacts(){ //This method prevents user to buy an artifact that already have been bought.
        List<String> boughtArtifacts = new ArrayList<>();
        boughtArtifacts.add("philosophers_compass"); //Test case, can be deleted
        boughtArtifacts.add("hard_bargain");
        //You add which artifacts are bought here.
        //elixir_of_insight
        //philosophers_compass
        //hard_bargain
        //Make sure you send these 3
        return boughtArtifacts;
    }

}
