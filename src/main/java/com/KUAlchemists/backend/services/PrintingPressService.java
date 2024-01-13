package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;

public class PrintingPressService {

        public PrintingPressService() {
        }
        /**
        * Activates the Printing Press for the given player.
        *
        * @param player The player who is activating the Printing Press.
        */
        public void activatePrintingPress(Player player) {
            if (Board.getInstance().getArtifactStorage(player).getArtifactByName("printing_press") != null) {
                Board.getInstance().getArtifactStorage(player).getArtifactByName("printing_press").setActivated(true);
            }
        }
        // printing press is used to not pay gold when you publish a theory.


}
