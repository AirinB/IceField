package com.rim.IceField;

import java.util.ArrayList;



public class Inventory {


    protected  ArrayList<ItemBase> items; //items in the inventory of each player
    //We check if players collected the parts of the gun, it hasn't to be one player.
    public static int  count = 0;

    public boolean addItem(ItemBase it){
        try {

            if(!(it.tag.equals("Flare") || it.tag.equals("Charge") || it.tag.equals("Gun"))){
                // Flare, charge and gun cannot be used separately

                it.active = true;
            }
            it.obtained = true;
            items.add(it);

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteItem(int index){
        try {
            items.remove(index);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<ItemBase> getItems() {
        return items;
    }



//    public  void isFlareGunAssembled(){
//
//        for (ItemBase item: items) {
//            if(item.tag.equals("Flare") || item.tag.equals("Charge") || item.tag.equals("Gun")){
//                count++;
//            }
//        }
//    }


}
