package com.KUAlchemists.backend.enums;

import java.io.Serializable;

public enum GameTour implements Serializable {

    FIRST_TOUR(1),
    SECOND_TOUR(2),
    THIRD_TOUR(3);

    /*  Constructor for GameTour enum
        @param tour: the tour number
    */
    public final int tour;


    /*  Constructor for GameTour enum
        @param tour: the tour number
    */
    GameTour(int tour) {
        this.tour = tour;
    }

    public static GameTour getNextTour(GameTour currentTour) {
        switch (currentTour) {
            case FIRST_TOUR:
                return SECOND_TOUR;
            case SECOND_TOUR:
                return THIRD_TOUR;
            default:
                return null;
        }
    }

    /*  Getter for tour
        @return tour: the tour number
    */
    public int getTour() {
        return tour;
    }
}
