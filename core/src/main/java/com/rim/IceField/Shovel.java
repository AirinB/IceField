package com.rim.IceField;

public class Shovel extends ItemBase {
    public Shovel() {
        super();
        tag = "Shovel";
    }

    @Override
    public boolean useItem(PlayerBase player) throws Exception {
        super.useItem(player) ;
        try{
            int currentAmountOfSnow =  player.getCurrentIceberg().getAmountOfSnow();
            player.getCurrentIceberg().setAmountOfSnow( currentAmountOfSnow - 2);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
