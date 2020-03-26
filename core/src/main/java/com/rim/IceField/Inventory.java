package com.rim.IceField;

import java.util.ArrayList;

public class Inventory {
    protected ArrayList<ItemBase> items; //items in the inventory of each player

    public boolean addItem(ItemBase it){
        try {

            if(!(it.tag.equals("Flare") || it.tag.equals("Charge") || it.tag.equals("Gun"))){
                // Flare, charge and gun cannot be used separately

                it.active = true;
            }
            it.obtained = true;
            items.add(it);

            if(isFlareGunAssembled()){
                //   ItemBase flareGun = new ItemBase("FlareGun");
                System.out.println("The flare gun is collected");
            }

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

    public boolean isFlareGunAssembled(){
        int count = 0;
        for (ItemBase item: items) {
            if(item.tag.equals("Flare") || item.tag.equals("Charge") || item.tag.equals("Gun")){
                count++;
            }
        }
        return count == 3; // if the count is 3 than all the parts of the flare gun are collected

    }

}
