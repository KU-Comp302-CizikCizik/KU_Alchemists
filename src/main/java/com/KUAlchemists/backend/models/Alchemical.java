package com.KUAlchemists.backend.models;

public class Alchemical {

    private Aspect redAspect;
    private Aspect blueAspect;
    private Aspect greenAspect;

    /**
     * Alchemical
     * @param redAspect, blueAspect, greenAspect
     */
    public Alchemical (Aspect redAspect, Aspect blueAspect, Aspect greenAspect){
        this.redAspect = redAspect;
        this.blueAspect = blueAspect;
        this.greenAspect = greenAspect;
    }
    /**
     * getRedAspect
     * @return redAspect
     */
    public Aspect getRedAspect(){
        return redAspect;
    }
    /**
     * setRedAspect
     * @param redAspect
     */
    public void setRedAspect(Aspect redAspect){
        this.redAspect = redAspect;
    }

    /**
     * getBlueAspect
     * @return blueAspect
     */
    public Aspect getBlueAspect() {
        return blueAspect;
    }

    /**
     * setBlueAspect
     * @param blueAspect
     */
    public void setBlueAspect(Aspect blueAspect) {
        this.blueAspect = blueAspect;
    }

    /**
     * getGreenAspect
     * @return greenAspect
     */
    public Aspect getGreenAspect() {
        return greenAspect;
    }

    /**
     * setGreenAspect
     * @param greenAspect
     */
    public void setGreenAspect(Aspect greenAspect) {
        this.greenAspect = greenAspect;
    }
}