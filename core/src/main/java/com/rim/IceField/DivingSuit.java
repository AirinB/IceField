package com.rim.IceField;

public class DivingSuit extends ItemBase {
    public DivingSuit() {
        super();
        tag = "Diving Suit";
    }

    @Override
    public boolean useItem(PlayerBase player) {
        try{
            player.setWearingDSuit(true);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
