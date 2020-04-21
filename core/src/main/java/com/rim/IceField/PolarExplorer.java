package com.rim.IceField;

import java.util.Timer;

//Po;arExplorer class
public class PolarExplorer extends PlayerBase {

    //Constructor
    public PolarExplorer() {
        super();
        this.tag = "PolarExplorer";    //Setting the name
        this.heatLevel = 4;            //Setting number of heat units
    }

    @Override
    public void run() {
        decreaseHeatLevel();
    }

    //Method useSkill performs the ability of PolarExplorer to get the max number of players that could stand on the current iceberg.
    @Override
    public void useSkill(Iceberg ice) {

        System.out.println("useSkill() : PolarExplorer");
        System.out.println("Max num. of players on neighbor: " + this.getCurrentIceberg().getNeighborIcebergs().get(0).getMaxNumOfPlayers());
    }

}
