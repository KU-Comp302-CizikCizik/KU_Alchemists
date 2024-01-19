package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Artifact;
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

    public boolean deduceActionPoint(){
        if(GameEngine.getInstance().getCurrentPlayer().getActionPoints() < 1)
        {
            return false;
        }
        useArtifactService.decreaseActionPoint(GameEngine.getInstance().getCurrentPlayer());
        return true;
    }
    public void activateWisdomIdol() {
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        wisdomIdolService.activateWisdomIdol(currentPlayer);
        currentPlayer.activateArtifact(WISDOM_IDOL);
    }
    public void activatePrintingPress(){
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        PrintingPressHandler.getInstance().handleActivatePrintingPress();
        currentPlayer.activateArtifact(PRINTING_PRESS);
    }
    public void activateMagicMortar(){
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        MagicMortarHandler.getInstance().handleActivateMagicMortar();
        currentPlayer.activateArtifact(MAGIC_MORTAR);
    }
    public List<String> getActivatedArtifacts(){
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        return currentPlayer.getActivatedArtifacts();
    }



}
