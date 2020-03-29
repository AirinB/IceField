package com.rim.IceField;

import java.net.SocketTimeoutException;
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
    public PlayerBase()
    {
       this.tag = "PlayerBase";
       inventory = new Inventory();
    }

    public void move()
    {

    }

    public void SavePlayer(PlayerBase player)
    {
		System.out.println("Player has been saved!");
    }
  
    public void useSkill(Iceberg ice)
    {
       System.out.println("Empty Skill.");
    }


    public void increaseHeatLevel() {
        this.heatLevel++;
    }
    public void decreseHeatLevel() {
        this.heatLevel--;
    }

    public int getHeatLevel() {
        return heatLevel;
    }

    public void useItem()
    {
        String in;
        //Not sure how I would check a specific item in the inventory here.
        for (int i = 0; i < inventory.items.size(); i++) {
            System.out.print(inventory.items.toString() + " ");
        }
        Scanner sc = new Scanner(System.in);
        in = sc.nextLine();
    }

    public void die()
    {
		  System.out.println("You have died. RIP ):");
    }

    /** In the fall() method we decrement the current heatLevel by 1, and that will be updated every second,
     * Meaning eskimos get 5 seconds to live if they fall, and Polar expolorers get 4 seconds.
     */
    public void fall()
    {
		  System.out.println("Ouch! You've fallen into some water");
		  heatLevel -= 1;
    }

    public void turn()
    {
        System.out.println("It's your turn " + tag);

    }

    public void checkDrowning()
    {

    }

    public void pickItem()
    {

    }
}
