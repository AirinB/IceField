package com.rim.IceField;

public class Eskimo extends PlayerBase {
    private boolean usedIgloo;

    public Eskimo()
    {
        super();
        this.tag = "Eskimo";
        this.heatLevel = 5;

    }

    @Override
    public void useSkill(Iceberg ice) {
        System.out.println("An Igloo has been created!");
        usedIgloo = true;
    }
}
