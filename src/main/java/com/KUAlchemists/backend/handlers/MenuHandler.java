package com.KUAlchemists.backend.handlers;


import com.KUAlchemists.backend.services.MenuService;

public class MenuHandler {


    private static MenuHandler instance;


    private MenuService menuService;

    private MenuHandler() {
        menuService = new MenuService();
    }

    public static MenuHandler getInstance() {
        if (instance == null) {
            instance = new MenuHandler();
        }
        return instance;
    }

    public void openRules() {
    }

    public void openSettings() {
    }

    public void startGame() {
        menuService.startGame();
    }
}
