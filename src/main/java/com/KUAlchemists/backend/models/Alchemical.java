package com.KUAlchemists.backend.models;

public class Alchemical {
    private Atom redAtom;
    private Atom blueAtom;
    private Atom greenAtom;

    public Alchemical (Atom redAtom, Atom blueAtom, Atom greenAtom){
        this.redAtom = redAtom;
        this.blueAtom = blueAtom;
        this.greenAtom = greenAtom;
    }

    public String combineWith(Alchemical other) {
        // TODO: Implement this method

        return "No Potion"; // In case there is no match
    }




}
