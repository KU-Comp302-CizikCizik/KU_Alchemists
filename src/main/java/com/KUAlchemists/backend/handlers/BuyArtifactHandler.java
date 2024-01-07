package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.BuyArtifactService;

import java.util.Arrays;
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
    public void handleBuyArtifactRequest(String artifactName) {
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        buyArtifactService.buyArtifact(currentPlayer, artifactName);
    }
    /**
     * Sends a list of bought artifact(s)
     * @return ArrayList <String>
     */

    public List<String> handleGetArtifacts(){ //This method prevents user to buy an artifact that already have been bought.

        return buyArtifactService.getArtifacts() ;
    }

    /**
     * Returns all the artifacts in the game. It does not mather if any artifact is bought or not.
     * @return List <String>
     */
    public List<String> handleGetAllArtifacts(){
        return Arrays.asList("elixir_of_insight", "philosophers_compass", "magic_mortar", "printing_press", "wisdom_idol");
    }

    /**
     * Returns the artifact's price
     * @param artifactName (String)
     * @return price (Integer)
     */
    public int handleGetPrice(String artifactName){
        System.out.println(artifactName);
        int price = 1; //Test case
        return price;
    }
    public List<String> handleBoughtArtifacts(){ //This method prevents user to buy an artifact that already have been bought.

        return buyArtifactService.getBoughtArtifacts();

    }


}
