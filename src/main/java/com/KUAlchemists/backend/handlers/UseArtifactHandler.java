package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.services.UseArtifactService;

import java.util.ArrayList;
import java.util.List;

public class UseArtifactHandler {
    private UseArtifactService useArtifactService;
    private static UseArtifactHandler INSTANCE;
    private UseArtifactHandler(UseArtifactService useArtifactService) {
        this.useArtifactService = useArtifactService;
    }

    public static UseArtifactHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseArtifactHandler(new UseArtifactService());
        }
        return INSTANCE;
    }

    // when use elixir of insight butto clicked this handler method will be called
    public ArrayList<String> handlePeekTopThree(){
        return useArtifactService.peekTopThree();
    }

    //  Then after the user rearranged the top three and clicked rearrange button, this handler method will be called
    public void useElixirOfInsight(List<String> arrangedTopThree) {
        useArtifactService.useElixirOfInsight(GameEngine.getInstance().getCurrentPlayer(), arrangedTopThree);
    }



}
