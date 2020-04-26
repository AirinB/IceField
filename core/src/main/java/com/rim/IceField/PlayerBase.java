package com.rim.IceField;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

//Abstract PlayerBase class
public abstract class PlayerBase extends TimerTask {
    protected Iceberg currentIceberg;             //Iceberg the player stands on
    protected String tag;                         //Type of the player: Eskimo, PolarExplorer
    protected int ID;                             //ID of the player
    protected boolean isWearingDSuit = false;     //Boolean for checking if diving suit is on
    protected int heatLevel;                      //Heat level of the player: Eskimo - 5, PolarExplorer - 4
    protected boolean isDead = false;             //Boolean if the player has died
    protected int numOfMoves;                     //Number of moves of each player
    protected Inventory inventory;                //Instance of Inventory class (contains list of items)
    protected boolean isDrowning = false;         //Boolean for checking if the player is in the water and drowning
    Timer timer = new Timer();                    //Experiments with timer
    protected boolean isTurn = false;            //Check if is the players turn

    public boolean isTurn() {
        return isTurn;
    }
    public int getID()
    {
        return ID;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setWearingDSuit(boolean wearing) {
        isWearingDSuit = wearing;
    }

    public Iceberg getCurrentIceberg() {
        return currentIceberg;
    }

    public void setCurrentIceberg(Iceberg iceberg) {
        currentIceberg = iceberg;
    }


    //The constructor for the PlayerBase instantiates inventory.
    public PlayerBase() {
        this.tag = "PlayerBase";
        inventory = new Inventory();
    }

public boolean checkDir(String str, Map map) {
    if ("north".equals(str)) { //Up
        currentIceberg.Remove_currentPlayers(this);
        if (currentIceberg.y - 1 < 0) {
            return false;
        } else {
            return true;
        }
    } else if ("south".equals(str)) {

        if (currentIceberg.y + 1 > 9) {
            return false;
        } else {
            return true;
        }

    } else if ("west".equals(str)) {

        if (currentIceberg.x - 1 < 0) {
            return false;
        } else {
            return true;
        }
    } else if ("east".equals(str)) {

        if (currentIceberg.x + 1 > 9) {
            return false;
        } else {
            return true;
        }

    }

    return true;
}



    public String getTag() {
        return tag;
    }


    //Move method implements the movement of a player on the map. Takes as the parameter the direction of the move(up,left,down,right).
    public boolean move(String str, Map map) throws Exception {


        if (checkDir(str, map) && "north".equals(str)) {
            currentIceberg.Remove_currentPlayers(this);
            currentIceberg = map.Icebergs[currentIceberg.y - 1][currentIceberg.x];
            currentIceberg.Add_currentPlayers(this);
            this.currentIceberg = currentIceberg;
        } else if (checkDir(str, map) && "south".equals(str)) {
            currentIceberg.Remove_currentPlayers(this);
            currentIceberg = map.Icebergs[currentIceberg.y + 1][currentIceberg.x];
            currentIceberg.Add_currentPlayers(this);
            this.currentIceberg = currentIceberg;
        } else if (checkDir(str, map) && "west".equals(str)) {
            currentIceberg.Remove_currentPlayers(this);
            currentIceberg = map.Icebergs[currentIceberg.y][currentIceberg.x - 1];
            currentIceberg.Add_currentPlayers(this);
            this.currentIceberg = currentIceberg;
        } else if (checkDir(str, map) && "east".equals(str)) {
            currentIceberg.Remove_currentPlayers(this);
            currentIceberg = map.Icebergs[currentIceberg.y][currentIceberg.x + 1];
            currentIceberg.Add_currentPlayers(this);
            this.currentIceberg = currentIceberg;
        } else {
            System.out.println("Sorry, you are on the edge of the map, no way to move up");
            return false;
        }

        if (currentIceberg.getType().equals("hole")) this.fall();
        else if (currentIceberg.getType().equals("instable") && currentIceberg.getMaxNumOfPlayers() < currentIceberg.getCurrentPlayers().size()) {
            System.out.println("Too many people on this iceberg! It has cracked.");
            for (PlayerBase p1 : this.currentIceberg.getCurrentPlayers()) {
                p1.fall();
                currentIceberg.Add_drowningPlayers(p1);
            }
            this.getCurrentIceberg().Add_drowningPlayers(this);
            this.fall();
            throw new Exception("This iceberg falls...");


        }
        return true;
    }

    //Method for saving the player.
    public boolean SavePlayer(String playerID, String dir, Map map) {
        //Check every item in the inventory to see if there's a rope.
            if(!ContainsItem("Rope"))
            {
                System.out.println("You don't have a rope!");
                return false;
            }

            ArrayList<PlayerBase> playerBases = new ArrayList<PlayerBase>();

            if(dir.equals("north")){
               playerBases = map.Icebergs[currentIceberg.y - 1][currentIceberg.x].getCurrentPlayers();
            }else if(dir.equals("south")){
                playerBases = map.Icebergs[currentIceberg.y + 1][currentIceberg.x].getCurrentPlayers();
            }else if(dir.equals("west")){
                playerBases = map.Icebergs[currentIceberg.y][currentIceberg.x - 1].getCurrentPlayers();
            }else if(dir.equals("east")){
                playerBases = map.Icebergs[currentIceberg.y][currentIceberg.x + 1].getCurrentPlayers();
            }


        if (playerBases != null) {
            for (PlayerBase player : playerBases) {
                //Find the drowning player in the list of all players.
                if (player.getID() == Integer.parseInt(playerID)) {
                    //Check if the player is drowning
                    if (player.isDrowning) {

                            System.out.println(player.tag + " has been saved!");
                            player.isDrowning = false;  //player is saved , so it's not drowning anymore
                            player.timer.cancel();
                            player.currentIceberg = this.currentIceberg;
                            this.currentIceberg.Add_currentPlayers(player);
                            break;

                    } else {
                        System.out.println("You are trying to save a player that is not in the water!");

                    }

                }
            }
            return true;
        }


        return false;

    }

    //Checks if current player is on neighboring iceberg in savable distance from player in need.
    public boolean checkSavableDistance(PlayerBase drowningPlayer)
    {
        int diffY = Math.abs(this.getCurrentIceberg().getY() - drowningPlayer.getCurrentIceberg().getY());
        int diffX = Math.abs(this.getCurrentIceberg().getX() - drowningPlayer.getCurrentIceberg().getX());
        return diffY <= 1 && diffX <= 1;

    }

    //UseSkill method.It is overridden in Eskimo and PolarExplorer classes.
    public boolean useSkill(Map map, String str) throws Exception {
        return true;
    }

    //Increases the heat level of the player.
    public void increaseHeatLevel() {
        this.heatLevel++;
    }

    //Decreases the heat level of the player.
    public void decreaseHeatLevel() {
        this.heatLevel--;
        if (this.heatLevel == 0) {
            this.timer.cancel();
            this.die();
        }
    }

    //Use the item specified in the parameter.
    public void useItem(String item) {

        if (ContainsItem(item)) {
            try {
                for(int i = 0; i<inventory.items.size(); i++) {
                    if(inventory.items.get(i).tag.equals(item)) {
                        inventory.items.get(i).useItem(this);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (item.equals("Food") || item.equals("Diving Suit")) {
                for (int i = 0; i < inventory.items.size(); i++) {
                    if (item.equals(inventory.items.get(i).tag)) {
                        inventory.deleteItem(i);
                        return;
                    }
                }
            }
        }
        else
        {
            System.out.println("Impossible to use the item or no such item exists!");
        }
    }

    private boolean ContainsItem(String item) {

        for( ItemBase itemBase: inventory.items){
            if(itemBase.tag.equals(item))return true;
        }
        return false;
    }


    //Player dies
    public void die() {
        isDead = true;
        System.out.println("You have died. RIP ):");
    }


    //Player falls into water
    public void fall() {
        currentIceberg.setType("hole");
        isDrowning = true;
        if (!isWearingDSuit) {    //check if the player hasn't his diving suit on
            System.out.println("You fell in the water. Ask for help to get back!");
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    decreaseHeatLevel();
                }

                ;
            };

            timer.scheduleAtFixedRate(tt, 0, 1000);

        }
    }

    //Player's turn to make actions.
    public void turn() {
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
        if (currentIceberg.getAmountOfSnow() == 0) {
            if(currentIceberg.getItem() != null) {
                inventory.items.add(currentIceberg.getItem());
                currentIceberg.DeletePickedItem(); // Will delete the item from the iceberg after it was picked
                String tagString = currentIceberg.getItem().getTag();
                try {
                    System.out.println(tagString + " was added to your inventory.");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println(currentIceberg.getItem().tag + " was added to your inventory.");
                return currentIceberg.getItem();
            }
            else {System.out.println("Impossible to pick, no such item on the iceberg.");}
        } else {
            System.out.println("Level of snow on iceberg is not 0!");
            throw new Exception("There is snow on the iceberg");
        }
       return null;
    }

    //Removes snow from the iceberg
    public void removeSnow() {
        if (currentIceberg.getAmountOfSnow() <= 0) return;
        int currentSnow = currentIceberg.getAmountOfSnow();
        currentIceberg.setAmountOfSnow(currentSnow - 1);     //amount of snow is decreased by 1
    }

    //Getter for heatLevel attribute
    public int getHeatLevel() {
        return heatLevel;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }
}


