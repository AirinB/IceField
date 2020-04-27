package com.rim.IceField;

import java.util.ArrayList;


public class Inventory {

    //items in the inventory of each player
    protected ArrayList<ItemBase> items = new ArrayList<ItemBase>();
    //We check if players collected the parts of the gun, it hasn't to be one player.
    public static int countGunItems = 0; //how many items are in the inventory


    /**
     * @param it the item added to the
     * player's inventory
     * @return true if the item was
     * added to the inventory
     */
    public boolean addItem(ItemBase it) {
        try {

            // Flare, charge and gun cannot be used separately
            if (!(it.tag.equals("Flare") || it.tag.equals("Charge") || it.tag.equals("Gun"))) {


                //all items can be activated after adding them to the inventory except the parts of the gun
                it.active = true;

            } else {
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


    /**
     * @param index the id of the item
     * to be removes from the inventory
     * @return boolean if the item was
     * removed from the inventory
     */
    public boolean deleteItem(int index) {
        try {
            items.remove(index);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @return list of items
     */
    public ArrayList<ItemBase> getItems() {
        return items;
    }

    /**
     * @param s tag of the item
     * @return the item selected
     */
    public ItemBase getItem(String s) {
        for (ItemBase item : items) {
            if (item.tag.equals(s)) {
                return item;
            }
        }
        return null;
    }

    /**
     * @param index id of the item
     * @return the item selected
     */
    public ItemBase getItemAt(int index)
    {
        return items.get(index);
    }

    /**
     * shows items in the inventory
     */
    public void showItems() {
        for (ItemBase item : items) {
            System.out.println(item.tag + " ");
        }
    }

}
