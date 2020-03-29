package com.rim.IceField;

import java.util.Scanner;

public abstract class PlayerBase {
    protected Iceberg currentIceberg;
    protected String tag;
    protected int ID;
    protected boolean isWearingDSuit = false;
    protected int heatLevel;
    protected boolean isDead = false;
    protected int numOfMoves;
    protected Inventory inventory;
    protected boolean isDrowning = false;

    public Inventory getInventory()
    {
        return inventory;
    }

   public void setWearingDSuit(boolean wearing)
   {
       isWearingDSuit = wearing;
   }
    public Iceberg getCurrentIceberg()
    {
        return currentIceberg;
    }
public void setCurrentIceberg(Iceberg iceberg)
{
    currentIceberg = iceberg;
}
    /**
     * The constructor for the playerbase just instantiates an inventory.
     */
    public PlayerBase() {
        this.tag = "PlayerBase";
        inventory = new Inventory();
    }

    public void move(char dir) {
        switch (dir) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
        }
    }

    public void SavePlayer(PlayerBase player) {
        System.out.println(player.tag + " has been saved!");
        player.isDrowning = false;
    }

    public void useSkill(Iceberg ice) {
        System.out.println("Empty Skill.");
    }

    public void increaseHeatLevel() {
        this.heatLevel++;
    }

    public void decreseHeatLevel() {
        this.heatLevel--;
    }

    public void useItem(ItemBase item) {
        if (inventory.items.contains(item)) {
            try {
                item.useItem(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void die()
    {
        isDead = true;
		  System.out.println("You have died. RIP ):");
    }

    /**
     * In the fall() method we decrement the current heatLevel by 1, and that will be updated every second,
     * Meaning eskimos get 5 seconds to live if they fall, and Polar expolorers get 4 seconds.
     */
    public void fall() {
        isDrowning = true;
        System.out.println("Ouch! You've fallen into some water");
        decreseHeatLevel();
    }

    public void turn() {
        System.out.println("It's your turn " + tag);
    }

    public boolean checkDrowning() {
        System.out.println(isDrowning);
        return isDrowning;
    }

    public void pickItem() {
        if (currentIceberg.getAmountOfSnow() == 0) {
            inventory.items.add(currentIceberg.getItem());
            System.out.println(currentIceberg.getItem().tag + " was added to your inventory.");
        } else {
            System.out.println("Level of snow on iceberg is not 0!");
        }
    }





    public int getHeatLevel() {
        return heatLevel;
    }


}
