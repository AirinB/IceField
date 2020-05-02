package com.rim.IceField;

import com.badlogic.gdx.graphics.Texture;

public class DivingSuit extends ItemBase {
    Texture tex = new Texture("mini2Dx.png");


    public DivingSuit() {
        super();
        tag = "diving suit";
    }

    /**
     * @param player that uses diving suit item
     * @return true if item was used successfully
     * @throws Exception if there is now such item
     * in the player's inventory
     * If player uses this item he won't die in the water
     */
    @Override
    public boolean useItem(PlayerBase player){
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
