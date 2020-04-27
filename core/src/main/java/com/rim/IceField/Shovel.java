package com.rim.IceField;

public class Shovel extends ItemBase {
    public Shovel() {
        super();
        tag = "Shovel";
    }

    /**
     *
     * @param player that wants to use the item
     * @return true if item was used successfully
     * @throws Exception no snow on the iceberg or
     * no item in the player's inventory
     */
    @Override
    public boolean useItem(PlayerBase player) throws Exception {
        super.useItem(player);
        try {

            //we get the current amount of snow and set it -2
            // because with shovel we can remove 2 units of snow
            int currentAmountOfSnow =  player.getCurrentIceberg().getAmountOfSnow();
            if (currentAmountOfSnow <= 0 ) return false;
            player.getCurrentIceberg().setAmountOfSnow( currentAmountOfSnow - 2);


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true; //return true if it worked
    }
}
