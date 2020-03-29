package com.rim.IceField;

import java.net.SocketTimeoutException;

public abstract class PlayerBase {
    protected String tag;
    protected int ID;
    protected boolean isWearingDSuit;
    protected int heatLevel;
    protected boolean isDead;
    protected int numOfMoves;
    public Inventory Inv;
    public Iceberg currentIceberg;

    public void setCurrentIceberg(Iceberg currentIceberg) {
        this.currentIceberg = currentIceberg;
    }
    public Iceberg getCurrentIceberg()
    {
        return this.currentIceberg;
    }

    public PlayerBase()
    {
       this.tag = "PlayerBase";
    }

    public void move()
    {

    }

    public void SavePlayer()
    {
		System.out.println("Player has been saved!");
    }

    //Had to make this method boolean in order to be able to check if the Eskimo constructed the igloo
    public boolean useSkill()
    {
		if (this.tag.equals("Eskimo"))
		{
			System.out.println("An Igloo has been created!");
		} else if (this.tag.equals("PolarExplorer"))
		{
			//Not sure if this is correct
			System.out.println("Neighboring icebergs have been checked!");
		}

		return true;
    }

    public void useItem()
    {


    }

    public void die()
    {
		System.out.println("You have died. RIP ):");
    }

    public void fall()
    {
		System.out.println("Ouch! You've fallen into some water");
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
