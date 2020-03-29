package com.rim.IceField;

public class PolarExplorer extends PlayerBase {

    public PolarExplorer() {
        super();
        this.tag = "PolarExplorer";
        this.heatLevel = 4;
    }

    @Override
    public void useSkill() {
        //Not sure if this is correct
        System.out.println("Neighboring icebergs have been checked!");
    }

}
