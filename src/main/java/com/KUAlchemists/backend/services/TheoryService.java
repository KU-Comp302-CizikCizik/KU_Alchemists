package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Theory;
import java.util.List;

public class TheoryService {

    private List<Theory> theories; // This would be your data store of theories

    public TheoryService(List<Theory> theories) {
        this.theories = theories;
    }

    /**
     * Finds a theory by its string ID.
     *
     * @param theoryIdStr The string ID of the theory to find.
     * @return The found Theory or null if no theory with the given ID exists.
     */
    public Theory findTheoryById(String theoryIdStr) {
        return theories.stream()
                .filter(theory -> theoryIdStr.equals(theory.getId()))
                .findFirst()
                .orElse(null); // Return null if no theory matches the given ID
    }

    // Additional methods...
}
