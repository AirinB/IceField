package com.rim.IceField;

public class Food extends ItemBase {


    public Food() {
        super();
        tag = "food";
    }

    @Override
    public boolean useItem(PlayerBase player){

        try {
            //When the player used the item food
            // this would increase the heat level by 1
            player.increaseHeatLevel();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
