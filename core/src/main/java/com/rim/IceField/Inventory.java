package com.rim.IceField;

import java.util.ArrayList;


public class Inventory {


    protected ArrayList<ItemBase> items = new ArrayList<ItemBase>(); //items in the inventory of each player

    //We check if players collected the parts of the gun, it hasn't to be one player.
    public static int countGunItems = 0; //how many items are in the inventory

    public boolean addItem(ItemBase it) {


        try {

            // Flare, charge and gun cannot be used separately
            if (!(it.tag.equals("Flare") || it.tag.equals("Charge") || it.tag.equals("Gun"))) {


                //all items can be activated after adding them to the inventory except the parts of the gun
                it.active = true;

            }else{
                countGunItems++;
            }
            it.obtained = true;
            items.add(it);


        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }



    public boolean deleteItem(int index) {
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

    public ItemBase getItem(String s){
        for (ItemBase item: items) {
            if(item.tag.equals(s)){
                return item;
            }
        }
        return null;
    }




}
