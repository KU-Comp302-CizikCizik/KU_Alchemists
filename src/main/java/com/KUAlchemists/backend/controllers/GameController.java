package com.KUAlchemists.backend.controllers;

import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.ui.LoginPageUI;
import com.KUAlchemists.ui.MainGameUI;
import javafx.application.Application;

public class GameController{

    public static GameController Instance;


    //TO-DO: Add switching player mechanism
    public GameController(){
        Gamestate.gamestate = Gamestate.LOGIN;
        update();
    }

    private void update() {
        switch (Gamestate.gamestate){
            case LOGIN:
                Application.launch(LoginPageUI.class);
                break;
            case GAME:
                Application.launch(MainGameUI.class);
                break;
            case DASHBOARD:
                break;
            case INVENTORY:
                break;
            case MIXING_INGREDIENT:
                break;

            case PUBLICATION:
                break;
            case DEDUCTION:
                break;
            case DEBUNK:
                break;
            case GAME_LOG:
                break;
            case ENDGAME:
                break;

        }
    }

    public static GameController getInstance(){
        if(Instance == null){
            Instance = new GameController();
        }
        return Instance;
    }


}
