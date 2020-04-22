package com.rim.IceField;

import java.sql.Time;
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
    public void move(String str, Map map) throws Exception {
        // up-> y--, down-> y++, left--> x--, right--> x++;
        if ("north".equals(str)) { //Up
            currentIceberg.Remove_currentPlayers(this);
            if (currentIceberg.y - 1 < 0) {
                System.out.println("Sorry, you are on the edge of the map, no way to move up");
            } else {
                currentIceberg = map.Icebergs[currentIceberg.y - 1][currentIceberg.x];
                currentIceberg.Add_currentPlayers(this);
            }

            if (currentIceberg.getType() == "hole") this.fall();
            else if (currentIceberg.getType() == "instable" && currentIceberg.getMaxNumOfPlayers() < currentIceberg.getCurrentPlayers().size()) {
                this.fall();
                throw new Exception("This iceberg falls...");
            }
        } else if ("south".equals(str)) {
            currentIceberg.Remove_currentPlayers(this);
            if (currentIceberg.y + 1 > 9) {
                System.out.println("Sorry, you are on the edge of the map, no way to move up");
            } else {
                currentIceberg = map.Icebergs[currentIceberg.y + 1][currentIceberg.x];
                currentIceberg.Add_currentPlayers(this);
            }

            if (currentIceberg.getType() == "hole") this.fall();
            else if (currentIceberg.getType() == "instable" && currentIceberg.getMaxNumOfPlayers() < currentIceberg.getCurrentPlayers().size()) {
                this.fall();
                throw new Exception("This iceberg falls...");
            }
        } else if ("west".equals(str)) {
            currentIceberg.Remove_currentPlayers(this);
            if (currentIceberg.x - 1 < 0) {
                System.out.println("Sorry, you are on the edge of the map, no way to move up");
            } else {
                currentIceberg = map.Icebergs[currentIceberg.y][currentIceberg.x - 1];
                currentIceberg.Add_currentPlayers(this);
            }

            if (currentIceberg.getType() == "hole") this.fall();
            else if (currentIceberg.getType() == "instable" && currentIceberg.getMaxNumOfPlayers() < currentIceberg.getCurrentPlayers().size()) {
                this.fall();
                throw new Exception("This iceberg falls...");
            }
        } else if ("east".equals(str)) {
            currentIceberg.Remove_currentPlayers(this);
            if (currentIceberg.x + 1 > 9) {
                System.out.println("Sorry, you are on the edge of the map, no way to move up");
            } else {
                currentIceberg = map.Icebergs[currentIceberg.y][currentIceberg.x + 1];
                currentIceberg.Add_currentPlayers(this);
            }

            if (currentIceberg.getType() == "hole") this.fall();
            else if (currentIceberg.getType() == "instable" && currentIceberg.getMaxNumOfPlayers() < currentIceberg.getCurrentPlayers().size()) {
                this.fall();
                throw new Exception("This iceberg falls...");
            }
        }
    }
            //Method for saving the player.
            public void SavePlayer (PlayerBase player){
                System.out.println("SavePlayer");
                System.out.println(player.tag + " has been saved!");
                player.isDrowning = false;  //player is saved , so it's not drowning anymore
                player.timer.cancel();
            }

            //UseSkill method.It is overridden in Eskimo and PolarExplorer classes.
            public void useSkill (Map map, String dir) throws Exception {
                System.out.println("Empty Skill.");
            }

            //Increases the heat level of the player.
            public void increaseHeatLevel () {
                System.out.println("increaseHeatLevel()");
                this.heatLevel++;
            }

            //Decreases the heat level of the player.
            public void decreaseHeatLevel () {
                System.out.println("decreaseHeatLevel()");
                this.heatLevel--;
                if (this.heatLevel == 0) {
                    this.timer.cancel();
                    this.die();
                }
            }

            //Use the item specified in the parameter.
            public void useItem (ItemBase item){

                if (inventory.items.contains(item)) {
                    try {
                        item.useItem(this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (item.tag == "Food" || item.tag == "Diving Suit") {
                        for (int i = 0; i < inventory.items.size(); i++) {
                            if (item.tag == inventory.items.get(i).tag) {
                                inventory.deleteItem(i);
                                return;
                            }
                        }
                    }
                }
            }


            //Player dies
            public void die () {
                System.out.println("die()");
                isDead = true;
                System.out.println("You have died. RIP ):");
            }



            //Player falls into water
            public void fall () {

                System.out.println("fall()");
                currentIceberg.setType("hole");
                if (!isWearingDSuit) {    //check if the player hasn't his diving suit on
                    isDrowning = true;
                    System.out.println("Ouch! You've fallen into some water");
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
            public void turn () {
                System.out.println("turn()");
                System.out.println("It's your turn " + tag);
            }

            //Checking if the player is drowning
            public boolean checkDrowning () {
                System.out.println("checkDrowning()");
                System.out.println(isDrowning);
                return isDrowning;
            }

            //PickItem method which return the item.
            public ItemBase pickItem () throws Exception {
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
            public void removeSnow () {
                System.out.println("removeSnow()");
                if (currentIceberg.getAmountOfSnow() <= 0) return;
                int currentSnow = currentIceberg.getAmountOfSnow();
                currentIceberg.setAmountOfSnow(currentSnow - 1);     //amount of snow is decreased by 1
            }

            //Getter for heatLevel attribute
            public int getHeatLevel () {
                System.out.println("getHeatLevel()");
                return heatLevel;
            }

            public int getNumOfMoves () {
                System.out.println("getNumOfMoves()");
                return numOfMoves;
            }
        }


