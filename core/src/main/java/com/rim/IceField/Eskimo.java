package com.rim.IceField;

import java.util.Timer;

//Eskimo class
public class Eskimo extends PlayerBase {
    //Boolean for checking if Eskimo has built the Igloo
    private boolean usedIgloo;

    //Constructor
    public Eskimo() {
        super();
        this.tag = "Eskimo";    //Setting the name
        this.heatLevel = 5;     //Setting number of heat units

    }

    @Override
    public void run() {
        decreaseHeatLevel();
    }

    //Method useSkill performs the ability of Eskimo to construct the Igloo
    @Override
    public void useSkill(Iceberg ice) {

        System.out.println("useSkill() : Eskimo");

        if (ice.getHasIgloo() == false) {
            System.out.println("An Igloo has been created!");
            ice.setHasIgloo(true);
        }
        else {
            System.out.println("There is an igloo on this iceberg already!");
        }

    }

}
