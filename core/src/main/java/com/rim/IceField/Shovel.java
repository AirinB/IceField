package com.rim.IceField;

public class Shovel extends ItemBase {
    public Shovel(String tag) {
        super(tag);
    }

    @Override
    public boolean useItem(PlayerBase player) {
        return false;
    }
}
