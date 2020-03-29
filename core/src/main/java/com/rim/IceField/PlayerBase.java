package com.rim.IceField;
public abstract class PlayerBase {
    protected String tag;
    protected int ID;
    protected boolean isWearingDSuit;
    protected int heatLevel;
    protected boolean isDead;
    protected int numOfMoves;
    public Inventory Inv;

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

    public void useSkill()
    {
		if (this.tag.equals("Eskimo"))
<<<<<<< HEAD
            System.out.println("An Igloo has been created!");
        else if (this.tag.equals("PolarExplorer"))
                System.out.println("Neighboring icebergs have been checked!");
=======
		{
			System.out.println("An Igloo has been created!");
		} else if (this.tag.equals("PolarExplorer"))
		{
			//Not sure if this is correct
			System.out.println("Neighboring icebergs have been checked!");
		}
>>>>>>> Rain
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

    }

    public void checkDrowning()
    {

    }

    public void pickItem()
    {

    }
}
