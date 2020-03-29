package com.rim.IceField;

public abstract class ItemBase {
    protected String tag; // name of the item
    protected static int itemNumber = 0;
    protected int id;
    protected int posX, posY;
    protected boolean obtained; // the item is owned by a player
    protected boolean active; //the item can be used or not

    public ItemBase() {
        id = itemNumber;
        itemNumber++;
//        this.posX = posX;
//        this.posY = posY;
        this.obtained = false; //in the begining the item does not belong to anyone
        this.active = false;  //the item that does not belog to the inventory, cannnot be used
    }

    public abstract boolean useItem(PlayerBase player);

    public String getTag() {
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
