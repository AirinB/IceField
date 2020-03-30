package com.rim.IceField;

public class PolarExplorer extends PlayerBase {

    public PolarExplorer() {
        super();
        this.tag = "PolarExplorer";
        this.heatLevel = 4;
    }

    @Override
    public void useSkill(Iceberg ice) {
        System.out.println("Max num. of players on neighbor: " + this.getCurrentIceberg().getNeighborIcebergs().get(0).getMaxNumOfPlayers());
    }

}
