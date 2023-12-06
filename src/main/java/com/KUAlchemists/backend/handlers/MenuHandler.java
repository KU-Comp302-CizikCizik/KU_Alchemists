package com.KUAlchemists.backend.handlers;


import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.managers.StateManager;
import com.KUAlchemists.backend.services.MenuService;

public class MenuHandler {


    private static MenuHandler INSTANCE;


    private MenuService menuService;

    private MenuHandler() {
        menuService = new MenuService();
    }

    public static MenuHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MenuHandler();
        }
        return INSTANCE;
    }

    public void openRules() {
    }

    public void openSettings() {
    }

    public void startGame() {
        menuService.startGame();
        StateManager.getInstance().updateGameState(Gamestate.BOARD);
    }
}
