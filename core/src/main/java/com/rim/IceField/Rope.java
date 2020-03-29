package com.rim.IceField;

public class Rope extends ItemBase {
    public Rope() {
        super();
        tag = "Rope";
    }

    @Override
    public boolean useItem(PlayerBase player) throws Exception {
        super.useItem(player) ;
        return false;
    }
}
