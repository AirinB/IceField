package com.rim.IceField;

public class Shovel extends ItemBase {
    public Shovel() {
        super();
        tag = "Shovel";
    }

    @Override
    public boolean useItem(PlayerBase player) throws Exception {
        super.useItem(player);
        try {

            //we get the current amount of snow and set it -2
            // because with shovel we can remove 2 units of snow
            int currentAmountOfSnow = player.getCurrentIceberg().getAmountOfSnow();
            player.getCurrentIceberg().setAmountOfSnow(currentAmountOfSnow - 2);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true; //return true if it worked
    }
}
