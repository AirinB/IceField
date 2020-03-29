package com.rim.IceField;

public class Food extends ItemBase {


    public Food() {
        super();
        tag = "Food";
    }

    @Override
    public boolean useItem(PlayerBase player) throws Exception {
        super.useItem(player) ;
        try{
            player.increaseHeatLevel();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
