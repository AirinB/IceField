package com.rim.IceField;

public class DivingSuit extends ItemBase {

    public DivingSuit() {
        super();
        tag = "Diving Suit";
    }

    @Override
    public boolean useItem(PlayerBase player) throws Exception {
        super.useItem(player);
        try {

            //when the player wears a diving suit, we set
            // the value that indicate that he wears it to true
            player.setWearingDSuit(true);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
