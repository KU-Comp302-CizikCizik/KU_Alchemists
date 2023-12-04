package com.KUAlchemists;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.engine.GameInitializer;
import com.KUAlchemists.backend.enums.Gamestate;
import com.KUAlchemists.ui.MainApplicationUI;
import javafx.application.Application;

public class Main {

        public static void main(String[] args) {
            GameInitializer gameInitializer = new GameInitializer();
            Application.launch(MainApplicationUI.class);

        }
}
