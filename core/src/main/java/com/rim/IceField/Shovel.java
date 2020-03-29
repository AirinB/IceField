package com.rim.IceField;

public class Shovel extends ItemBase {
    public Shovel() {
        super();
        tag = "Shovel";
    }

    @Override
    public boolean useItem(PlayerBase player) {
        try{
            int currentAmountOfSnow =  player.getIceberg().getAmountOfSnow( );
            player.getIceberg().setAmountOfSnow( currentAmountOfSnow - 2);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
