package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.UseArtifactService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UseArtifactHandler {
    private final UseArtifactService useArtifactService;

    private static ArrayList<String> usedArtifacts = new ArrayList<>();
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

    //new:: returns used artifacts list.
    public List<String> handleUsedArtifacts(){

//        return useArtifactService.getUsedArtifacts();

        return usedArtifacts;
    }
    public List<String> handleGetAllArtifacts(){
        return Arrays.asList("elixir_of_insight", "philosophers_compass", "magic_mortar", "printing_press", "wisdom_idol");
    }
    public void handleRemoveArtifact(String name){
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        useArtifactService.removeArtifactFromStorage(name, currentPlayer);
        usedArtifacts.add(name);
    }
    public List <String> handleStorageArtifact(){
        //return buyArtifactService.getBoughtArtifacts();
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        return useArtifactService.getStorageArtifacts(currentPlayer);
    }
}
