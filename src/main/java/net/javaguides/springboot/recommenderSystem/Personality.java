package net.javaguides.springboot.recommenderSystem;

public class Personality {
    private int warm;
    private int gentle;
    private int calm;
    private int extrovert;
    private int adaptable;
    private int elegant;
    private int optimistic;
    private int creative;
    private int sophisticated;
    private int adventurous;

    public Personality() {
    }

    public Personality(int warm, int gentle, int calm, int extrovert, int adaptable, int elegant, int optimistic, int creative, int sophisticated, int adventurous) {
        this.warm = warm;
        this.gentle = gentle;
        this.calm = calm;
        this.extrovert = extrovert;
        this.adaptable = adaptable;
        this.elegant = elegant;
        this.optimistic = optimistic;
        this.creative = creative;
        this.sophisticated = sophisticated;
        this.adventurous = adventurous;
    }

    public int getWarm() {
        return warm;
    }

    public void setWarm(int warm) {
        this.warm = warm;
    }

    public int getGentle() {
        return gentle;
    }

    public void setGentle(int gentle) {
        this.gentle = gentle;
    }

    public int getCalm() {
        return calm;
    }

    public void setCalm(int calm) {
        this.calm = calm;
    }

    public int getExtrovert() {
        return extrovert;
    }

    public void setExtrovert(int extrovert) {
        this.extrovert = extrovert;
    }

    public int getAdaptable() {
        return adaptable;
    }

    public void setAdaptable(int adaptable) {
        this.adaptable = adaptable;
    }

    public int getElegant() {
        return elegant;
    }

    public void setElegant(int elegant) {
        this.elegant = elegant;
    }

    public int getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(int optimistic) {
        this.optimistic = optimistic;
    }

    public int getCreative() {
        return creative;
    }

    public void setCreative(int creative) {
        this.creative = creative;
    }

    public int getSophisticated() {
        return sophisticated;
    }

    public void setSophisticated(int sophisticated) {
        this.sophisticated = sophisticated;
    }

    public int getAdventurous() {
        return adventurous;
    }

    public void setAdventurous(int adventurous) {
        this.adventurous = adventurous;
    }
}