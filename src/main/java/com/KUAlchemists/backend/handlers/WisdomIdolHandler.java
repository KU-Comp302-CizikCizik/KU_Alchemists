package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.WisdomIdolService;

public class WisdomIdolHandler {

    private static WisdomIdolHandler instance = null;

    private WisdomIdolService wisdomIdolService;

    private String debunkIngredientName;

    private WisdomIdolHandler() {
        this.wisdomIdolService = new WisdomIdolService();
        debunkIngredientName = "";
    }

    public static WisdomIdolHandler getInstance() {
        if (instance == null) {
            instance = new WisdomIdolHandler();
        }
        return instance;
    }

    // activating the wisdom idol
    public void handleActivateWisdomIdol() {
        Player currentPlayer = GameEngine.getInstance().getCurrentPlayer();
        wisdomIdolService.activateWisdomIdol(currentPlayer);
    }
}
