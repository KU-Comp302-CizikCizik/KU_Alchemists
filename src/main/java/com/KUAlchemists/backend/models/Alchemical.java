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


    public Atom getRedAtom() {
        return redAtom;
    }

    public void setRedAtom(Atom redAtom) {
        this.redAtom = redAtom;
    }

    public Atom getBlueAtom() {
        return blueAtom;
    }

    public void setBlueAtom(Atom blueAtom) {
        this.blueAtom = blueAtom;
    }

    public Atom getGreenAtom() {
        return greenAtom;
    }

    public void setGreenAtom(Atom greenAtom) {
        this.greenAtom = greenAtom;
    }
}