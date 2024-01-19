package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.UseArtifactService;
import com.KUAlchemists.backend.services.WisdomIdolService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UseArtifactHandler {
    private final UseArtifactService useArtifactService;
    private WisdomIdolService wisdomIdolService;

    private static ArrayList<String> usedArtifacts = new ArrayList<>();
    private static UseArtifactHandler INSTANCE;

    private static String PRINTING_PRESS = "printing_press";
    private static String WISDOM_IDOL = "wisdom_idol";
    private static String MAGIC_MORTAR = "magic_mortar";
    private static List<String> activatedArtifacts = new ArrayList<String>();


    private UseArtifactHandler() {
        this.useArtifactService = new UseArtifactService();
        this.wisdomIdolService = new WisdomIdolService();

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
        return Arrays.asList("elixir_of_insight", "magic_mortar", "printing_press", "wisdom_idol");
    }
    public void handleRemoveArtifact(String name){
        useArtifactService.removeArtifactFromStorage(name);
        usedArtifacts.add(name);
    }
    public List <String> handleStorageArtifact(){
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        return useArtifactService.getStorageArtifacts(currentPlayer);
    }

    public void deduceActionPoint(){
        useArtifactService.decreaseActionPoint(GameEngine.getInstance().getCurrentPlayer());
    }
    public void activateWisdomIdol() {
        wisdomIdolService.activateWisdomIdol(GameEngine.getInstance().getCurrentPlayer());
        activatedArtifacts.add(WISDOM_IDOL);
    }
    public void activatePrintingPress(){
        activatedArtifacts.add(PRINTING_PRESS);
    }

    public void activateMagicMortar(){
        activatedArtifacts.add(MAGIC_MORTAR);
    }
    public List<String> getActivatedArtifacts(){
        return activatedArtifacts;
    }

//    public boolean isMagicMortarActivated(){
//        for(String artifact: activatedArtifacts){
//            if (artifact.equals(MAGIC_MORTAR))
//                return true;
//        }
//        return false;
//    }

//    public void deactivateArtifact(String artifactName){
//        activatedArtifacts.remove(artifactName);
//    }
}
