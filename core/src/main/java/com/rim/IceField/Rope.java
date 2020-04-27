package com.rim.IceField;

public class Rope extends ItemBase {
    public Rope() {
        super();
        tag = "rope";
    }

    /**
     *
     * @param player that wants to use the item
     * @return false
     * @throws Exception if no item found in player's
     * inventory
     */
    @Override
    public boolean useItem(PlayerBase player){
        super.useItem(player);
        //we just return false, because we have the method save player that does this functionaity for the rope
        return false;
    }
}
