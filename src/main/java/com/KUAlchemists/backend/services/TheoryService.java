package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Theory;

public class TheoryService {
    public TheoryService() {
    }
    /**
     * Finds a theory by its string ID.
     *
     * @param theoryIdStr The string ID of the theory to find.
     * @return The found Theory or null if no theory with the given ID exists.
     */
    public Theory findTheoryById(String theoryIdStr) {
        return Board.getInstance().getPublishedTheoriesList().stream()
                .filter(theory -> theoryIdStr.equals(theory.getId()))
                .findFirst()
                .orElse(null); // Return null if no theory matches the given ID
    }

    // Additional methods...
}
