package com.rim.IceField;

public abstract class ItemBase {
    protected String tag; // name of the item
    protected static int itemNumber = 0; // give an ID
    protected int id;
    protected int posX, posY;
    protected boolean obtained; // the item is owned by a player
    protected boolean active; //the item can be used or not

    public ItemBase() {
        System.out.println("ItemBase()");
        id = itemNumber;
        itemNumber++;
//        this.posX = posX;
//        this.posY = posY;
        this.obtained = false; //in the beginning the item does not belong to anyone
        this.active = false;  //the item that does not belong to the inventory, cannot be used
    }

    public  boolean useItem(PlayerBase player) throws Exception {
        System.out.println("useItem()");
        try {
            //an item cannot be used if its not in the inventory or its not active
            if(!obtained || !active) throw new Exception("the Item is now owned or not active");
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public String getTag() {
        System.out.println("getTag()");
        return tag;
    }

    public int getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isObtained() {
        return obtained;
    }

    public boolean isActive() {
        return active;
    }



}
