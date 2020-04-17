package com.rim.IceField;

//Abstract PlayerBase class
public abstract class PlayerBase {
    protected Iceberg currentIceberg;             //Iceberg the player stands on
    protected String tag;                         //Type of the player: Eskimo, PolarExplorer
    protected int ID;                             //ID of the player
    protected boolean isWearingDSuit = false;     //Boolean for checking if diving suit is on
    protected int heatLevel;                      //Heat level of the player: Eskimo - 5, PolarExplorer - 4
    protected boolean isDead = false;             //Boolean if the player has died
    protected int numOfMoves;                     //Number of moves of each player
    protected Inventory inventory;                //Instance of Inventory class (contains list of items)
    protected boolean isDrowning = false;         //Boolean for checking if the player is in the water and drowning

    public Inventory getInventory() {
        System.out.println("getInventory()");
        return inventory;
    }

    public void setWearingDSuit(boolean wearing) {
        System.out.println("setWearing suit()");
        isWearingDSuit = wearing;
    }

    public Iceberg getCurrentIceberg() {
        System.out.println("getCurrentIceberg()");
        return currentIceberg;
    }

    public void setCurrentIceberg(Iceberg iceberg) {
        System.out.println("setCurrentIceberg()");
        currentIceberg = iceberg;
    }


    //The constructor for the PlayerBase instantiates inventory.
    public PlayerBase() {
        this.tag = "PlayerBase";
        inventory = new Inventory();
    }

    public String getTag() {
        System.out.println("getTag()");
        return tag;
    }


    //Move method implements the movement of a player on the map. Takes as the parameter the direction of the move(up,left,down,right).
    public void move(int dir) throws Exception {
        System.out.println("Move()");
        switch (dir) {
            case 1: //Up
                //Add self onto neighboring iceberg, remove from old iceberg, decrease num of moves.
                currentIceberg.Remove_currentPlayers(this);

                //At this point all players will move in one direction, to the1 next Iceberg in the list. More functionality in the future.
                currentIceberg.getNeighborIcebergs().get(0).Add_currentPlayers(this);
                currentIceberg = currentIceberg.getNeighborIcebergs().get(0);
                if(currentIceberg.getType()=="hole") this.fall();
                else if(currentIceberg.getType()=="instable" && currentIceberg.getMaxNumOfPlayers()<currentIceberg.getCurrentPlayers().size()) {
                    this.fall();
                    throw new Exception("This iceberg falls...");
                }
                break;
            case 2://Add self onto neighboring iceberg, remove from old iceberg, decrease num of moves.
                currentIceberg.Remove_currentPlayers(this);

                //At this point all players will move in one direction, to the1 next Iceberg in the list. More functionality in the future.
                currentIceberg.getNeighborIcebergs().get(0).Add_currentPlayers(this);
                currentIceberg = currentIceberg.getNeighborIcebergs().get(0);
                if(currentIceberg.getType()=="hole") this.fall();
                else if(currentIceberg.getType()=="instable" && currentIceberg.getMaxNumOfPlayers()<currentIceberg.getCurrentPlayers().size()) {
                    this.fall();
                    throw new Exception("This iceberg falls...");
                }
            case 3: //Down
                //Add self onto neighboring iceberg, remove from old iceberg, decrease num of moves.
                currentIceberg.Remove_currentPlayers(this);

                //At this point all players will move in one direction, to the1 next Iceberg in the list. More functionality in the future.
                currentIceberg.getNeighborIcebergs().get(0).Add_currentPlayers(this);
                currentIceberg = currentIceberg.getNeighborIcebergs().get(0);
                if(currentIceberg.getType()=="hole") this.fall();
                else if(currentIceberg.getType()=="instable" && currentIceberg.getMaxNumOfPlayers()<currentIceberg.getCurrentPlayers().size()) {
                    this.fall();
                    throw new Exception("This iceberg falls...");
                }
            case 4: //Right
                //Add self onto neighboring iceberg, remove from old iceberg, decrease num of moves.
                currentIceberg.Remove_currentPlayers(this);

                //At this point all players will move in one direction, to the1 next Iceberg in the list. More functionality in the future.
                currentIceberg.getNeighborIcebergs().get(0).Add_currentPlayers(this);
                currentIceberg = currentIceberg.getNeighborIcebergs().get(0);
                if(currentIceberg.getType()=="hole") this.fall();
                else if(currentIceberg.getType()=="instable" && currentIceberg.getMaxNumOfPlayers()<currentIceberg.getCurrentPlayers().size()) {
                    this.fall();
                    throw new Exception("This iceberg falls...");
                }
        }
        numOfMoves--;
    }

    //Method for saving the player.
    public void SavePlayer(PlayerBase player) {
        System.out.println("SavePlayer");
        System.out.println(player.tag + " has been saved!");
        player.isDrowning = false;  //player is saved , so it's not drowning anymore
    }

    //UseSkill method.It is overridden in Eskimo and PolarExplorer classes.
    public void useSkill(Iceberg ice) {
        System.out.println("Empty Skill.");
    }

    //Increases the heat level of the player.
    public void increaseHeatLevel() {
        System.out.println("increaseHeatLevel()");
        this.heatLevel++;
    }

    //Decreases the heat level of the player.
    public void decreaseHeatLevel() {
        System.out.println("decreaseHeatLevel()");
        this.heatLevel--;
        if(this.heatLevel==0)
        {
            this.die();
        }
    }

    //Use the item specified in the parameter.
    public void useItem(ItemBase item) {
        System.out.println("useItem()");
        if (inventory.items.contains(item)) {
            try {
                item.useItem(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //Player dies
    public void die() {
        System.out.println("die()");
        isDead = true;
        System.out.println("You have died. RIP ):");
    }

    /**
     * In the fall() method we decrement the current heatLevel by 1, and that will be updated every second,
     * Meaning eskimos get 5 seconds to live if they fall, and Polar expolorers get 4 seconds.
     */

    //Player falls into water
    public void fall() {

        System.out.println("fall()");
        currentIceberg.setType("hole");
        if (!isWearingDSuit) {    //check if the player hasn't his diving suit on
            isDrowning = true;
            System.out.println("Ouch! You've fallen into some water");
            decreaseHeatLevel(); //Should timer should be set in future!1111
        }
    }

    //Player's turn to make actions.
    public void turn() {
        System.out.println("turn()");
        System.out.println("It's your turn " + tag);
    }

    //Checking if the player is drowning
    public boolean checkDrowning() {
        System.out.println("checkDrowning()");
        System.out.println(isDrowning);
        return isDrowning;
    }

    //PickItem method which return the item.
    public ItemBase pickItem() throws Exception {
        System.out.println("pickItem()");
        if (currentIceberg.getAmountOfSnow() == 0) {
            inventory.items.add(currentIceberg.getItem());
            System.out.println(currentIceberg.getItem().tag + " was added to your inventory.");
            return currentIceberg.getItem();
        } else {
            System.out.println("Level of snow on iceberg is not 0!");
            throw new Exception("There is snow on the iceberg");
        }
    }

    //Removes snow from the iceberg
    public void removeSnow() {
        System.out.println("removeSnow()");
        if (currentIceberg.getAmountOfSnow() <= 0) return;
        int currentSnow = currentIceberg.getAmountOfSnow();
        currentIceberg.setAmountOfSnow(currentSnow - 1);     //amount of snow is decreased by 1
    }

    //Getter for heatLevel attribute
    public int getHeatLevel() {
        System.out.println("getHeatLevel()");
        return heatLevel;
    }

    public int getNumOfMoves(){
        System.out.println("getNumOfMoves()");
        return numOfMoves;
    }
}
