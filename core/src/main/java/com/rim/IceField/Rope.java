package com.rim.IceField;

public class Rope extends ItemBase {
    public Rope() {
        super();
        System.out.println("Rope()");
        tag = "Rope";
    }

    @Override
    public boolean useItem(PlayerBase player) throws Exception {

        System.out.println("UseItem()");
        super.useItem(player);
        //we just return false, because we have the method save player that does this functionaity for the rope
        return false;
    }
}
