package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.models.Alchemical;
import com.KUAlchemists.backend.models.Player;

public class PhilosopherCompassService {

    // once per round çok kırık mı olur?
    // once per round yapılacaksa round izleme mekanizması nasıl olacak?

    /**
     * Swaps two different aspects within a single Alchemical.
     *
     * @param player The player using the Philosophers Compass.
     * @param alchemical The alchemical whose aspects are being swapped.
     * @param color1 The first aspect to swap.
     * @param color2 The second aspect to swap.
     * @return true if the swap was successful, false otherwise.
     */
    public boolean swapAspects(Player player, Alchemical alchemical, String color1, String color2) {
        GameEngine gameEngine = GameEngine.getInstance();

        // compass bu roundda kullanıldı mı checkle ????
        if (player.hasUsedPhilosophersCompassThisRound()) {
            return false;
        }

        // swap işlemi
        Aspect temp = alchemical.getAspectByColor(color1);
        alchemical.setAspectByColor(color1, alchemical.getAspectByColor(color2));
        alchemical.setAspectByColor(color2, temp);


        // kullanıldıktan sonra marklanması gerekiyor ????
        player.setUsedPhilosophersCompassThisRound(true);
        gameEngine.registerCompassUseForRound(player);

        return true;
    }
}
