package com.KUAlchemists.backend.managers;

import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.backend.observer.GameStateObserver;
import com.KUAlchemists.ui.SceneLoader;
import javafx.scene.Parent;

public class SceneManager implements GameStateObserver {

    // Singleton
    private static SceneManager Instance;
    private Gamestate currentState = Gamestate.LOGIN;

    private Parent root;

    /**
     * @return the instance
     */
    public static SceneManager getInstance() {
        if(Instance == null) {
            Instance = new SceneManager();
        }
        return Instance;
    }
    private SceneManager() {

    }

    @Override
    public void onGameStateChange(Gamestate to) {
        if(currentState == to){
            return;
        }
        currentState = to;

        switch (to) {
            case PUBLICATION:
                SceneLoader.getInstance().loadPublicationTrack();
            case LOGIN:
                SceneLoader.getInstance().loadLogin();
                break;
            case MENU:
                SceneLoader.getInstance().loadMenu();
                break;
            case BOARD:
                SceneLoader.getInstance().loadBoard();
                break;
            case DEDUCTION:
                SceneLoader.getInstance().loadDeductionBoard();
                break;
            default:
                break;

        }
    }

}
