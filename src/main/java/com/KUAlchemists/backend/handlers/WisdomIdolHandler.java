package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.services.WisdomIdolService;

import java.time.chrono.MinguoEra;
import java.util.ArrayList;

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
    public void useWisdomIdol() {
        ArrayList<Object> notfication = BoardHandler.getInstance().getNotificationMap().get(GameEngine.getInstance().getCurrentPlayer());
        int deductedPoint = (int) notfication.get(1);
        wisdomIdolService.applyWizardIdol(GameEngine.getInstance().getCurrentPlayer(),deductedPoint );
        BoardHandler.getInstance().deleteNotification(notfication);
    }
    public String getDebunkIngredientName() {
        String ingredient = BoardHandler.getInstance().getNotificationMap().get(GameEngine.getInstance().getCurrentPlayer()).get(0).toString();
        return ingredient;
    }
}
