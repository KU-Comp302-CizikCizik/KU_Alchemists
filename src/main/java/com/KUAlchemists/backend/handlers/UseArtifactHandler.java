package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.services.UseArtifactService;

import java.util.ArrayList;
import java.util.List;

public class UseArtifactHandler {
    private final UseArtifactService useArtifactService;
    private static UseArtifactHandler INSTANCE;
    private UseArtifactHandler() {
        this.useArtifactService = new UseArtifactService();
    }

    public static UseArtifactHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseArtifactHandler();
        }
        return INSTANCE;
    }

    // when use elixir of insight button clicked this handler method will be called
    public ArrayList<String> handlePeekTopThree(){
        return useArtifactService.peekTopThree();
    }

    //  Then after the user rearranged the top three and clicked rearrange button, this handler method will be called
    public void useElixirOfInsight(List<String> arrangedTopThree) {
        useArtifactService.useElixirOfInsight(GameEngine.getInstance().getCurrentPlayer(), arrangedTopThree);
    }



    /**
     * Sends a list of bought artifact(s)
     * @return ArrayList <String>
     */
    public List<String> getBoughtArtifacts(){ //This method prevents user to buy an artifact that already have been bought.
        List<String> boughtArtifacts = new ArrayList<>();
        boughtArtifacts.add("elixir_of_insight"); //Test case, can be deleted
        //You add which artifacts are bought here.
        //elixir_of_insight
        //philosophers_compass
        //hard_bargain
        //Make sure you send these 3
        return boughtArtifacts;
    }




}
