package com.rim.IceField;

public class Rope extends ItemBase {
    public Rope() {
        super();
        tag = "Rope";
    }

    /**
     *
     * @param player that wants to use the item
     * @return false
     * @throws Exception if no item found in player's
     * inventory
     */
    @Override
    public boolean useItem(PlayerBase player) throws Exception {
        super.useItem(player);
        //we just return false, because we have the method save player that does this functionaity for the rope
        return false;
    }
}
