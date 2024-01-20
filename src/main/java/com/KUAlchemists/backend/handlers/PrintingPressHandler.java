package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;

public class PrintingPressHandler {

        private static PrintingPressHandler INSTANCE;

        public static PrintingPressHandler getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new PrintingPressHandler();
            }
            return INSTANCE;
        }

        private PrintingPressHandler() {
        }
        // This method is called by UI when the player activates the printing press artifact
        public void handleActivatePrintingPress() {
            Player player = GameEngine.getInstance().getCurrentPlayer();
            if (Board.getInstance().getArtifactStorage(player).getArtifactByName("printing_press") != null) {
                Board.getInstance().getArtifactStorage(player).getArtifactByName("printing_press").setActivated(true);
            }
        }
}
