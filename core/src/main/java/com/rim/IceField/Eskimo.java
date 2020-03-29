package com.rim.IceField;

public class Eskimo extends PlayerBase {
    private boolean usedIgloo;

    public Eskimo() {
        super();
        this.tag = "Eskimo";
        this.heatLevel = 5;

    }

    @Override
    public void useSkill(Iceberg ice) {

        if (ice.getHasIgloo() == false) {
            System.out.println("An Igloo has been created!");
            ice.setHasIgloo(true);
        }
        else
            System.out.println("There is an igloo on this iceberg already!");

    }
}
