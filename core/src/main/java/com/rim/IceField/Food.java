package com.rim.IceField;

public class Food extends ItemBase {


    public Food() {
        super();
        tag = "food";
    }

    /**
     * @param player that uses food item
     * @return true if item was used successfully
     * @throws Exception if there is now such item
     * in the player's inventory
     * Increases player's heat level by 1
     */
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
