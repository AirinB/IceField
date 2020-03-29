package com.rim.IceField;
public abstract class PlayerBase {
    protected String tag;
    protected int ID;
    protected boolean isWearingDSuit = false;
    protected int heatLevel;
    protected boolean isDead = false;
    protected int numOfMoves;
    public Inventory Inv;

    /** 
     * The constructor for the playerbase just instantiates an inventory.
     */
    public PlayerBase()
    {
       this.tag = "PlayerBase";
       Inv = new Inventory();
    }

    public void move()
    {

    }

    public void SavePlayer(PlayerBase player)
    {
		System.out.println("Player has been saved!");
    }
  
    public void useSkill()
    {
       System.out.println("Empty Skill.");
		}


    public void useItem()
    {
        String in;
        //Not sure how I would check a specific item in the inventory here.
        for (int i = 0; i < Inv.items.size(); i++) {
            System.out.print(Inv.items.toString() + " ");
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

    }

    public void checkDrowning()
    {

    }

    public void pickItem()
    {

    }
}
