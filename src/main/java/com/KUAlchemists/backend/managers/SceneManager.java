package com.KUAlchemists.backend.managers;

import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.ui.SceneLoader;
import javafx.scene.Parent;

public class SceneManager {

    // Singleton
    private static SceneManager Instance;

    private SceneLoader sceneLoader;

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
        sceneLoader = new SceneLoader();
    }

    public void changeScene(Gamestate to) {
        switch (to) {
            case DEDUCTION:
                sceneLoader.loadDeductionBoard();
            case LOGIN:
                sceneLoader.loadLogin();
                break;
            case MENU:
                sceneLoader.loadMenu();
                break;

            default:
                break;
        }

    }



}
