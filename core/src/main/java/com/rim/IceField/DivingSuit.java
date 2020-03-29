package com.rim.IceField;

public class DivingSuit extends ItemBase {
    public DivingSuit() {
        super();
        tag = "Diving Suit";
    }

    @Override
    public boolean useItem(PlayerBase player) {
        return false;
    }
}
