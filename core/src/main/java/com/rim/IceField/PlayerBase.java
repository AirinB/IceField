package com.rim.IceField;

import java.io.IOException;
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
    protected boolean isTurn = false;             //Check if is the players turn
    protected int tileX, tileY;                   //Stores the X and Y position relative to the tiles
    protected int offX = 0, offY = 0;             //Stores the X and Y offset of the player
    protected int posX, posY;                     //Stores the X and Y position of the player (This gets multiplied by the tile size)
     protected Game game;

    public boolean isTurn() {
        return isTurn;
    }

    public int getID() {
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
            return currentIceberg.y - 1 >= 0;
        } else if ("south".equals(str)) {

            return currentIceberg.y + 1 <= 9;

        } else if ("west".equals(str)) {

            return currentIceberg.x - 1 >= 0;
        } else if ("east".equals(str)) {

            return currentIceberg.x + 1 <= 9;

        }

        return true;
    }


    public String getTag() {
        return tag;
    }


    /**
     * @param dir the direction of movement
     * @param map the map of the game
     * @return returns true if the action was succesful
     * @throws Exception if the player falls
     */
    //Move method implements the movement of a player on the map. Takes as the parameter the direction of the move(up,left,down,right).
    public boolean move(String dir, Map map) throws Exception {
        // up-> y--, down-> y++, left--> x--, right--> x++;
        if ("north".equals(dir)) { //Up

            if (currentIceberg.y - 1 < 0) {
                System.out.println("Sorry, you are on the edge of the map, no way to move up");
                return false;
            } else {
                currentIceberg.Remove_currentPlayers(this);
                currentIceberg = map.Icebergs[currentIceberg.y - 1][currentIceberg.x];
                currentIceberg.Add_currentPlayers(this);
                if (currentIceberg.getType().equals("hole")) this.fall();
                else if (currentIceberg.getType().equals("instable") && currentIceberg.getMaxNumOfPlayers() < currentIceberg.getCurrentPlayers().size()) {
                    this.fall();
                    throw new Exception("This iceberg falls...");
                }
            }


        } else if ("south".equals(dir)) {

            if (currentIceberg.y + 1 > 9) {
                System.out.println("Sorry, you are on the edge of the map, no way to move down");
                return false;
            } else {
                currentIceberg.Remove_currentPlayers(this);
                currentIceberg = map.Icebergs[currentIceberg.y + 1][currentIceberg.x];
                currentIceberg.Add_currentPlayers(this);
                if (currentIceberg.getType().equals("hole")) this.fall();
                else if (currentIceberg.getType().equals("instable") && currentIceberg.getMaxNumOfPlayers() < currentIceberg.getCurrentPlayers().size()) {
                    this.fall();
                    throw new Exception("This iceberg falls...");
                }
            }


        } else if ("west".equals(dir)) {

            if (currentIceberg.x - 1 < 0) {
                System.out.println("Sorry, you are on the edge of the map, no way to move left");
                return false;
            } else {
                currentIceberg.Remove_currentPlayers(this);
                currentIceberg = map.Icebergs[currentIceberg.y][currentIceberg.x - 1];
                currentIceberg.Add_currentPlayers(this);
                if (currentIceberg.getType().equals("hole")) this.fall();
                else if (currentIceberg.getType().equals("instable") && currentIceberg.getMaxNumOfPlayers() < currentIceberg.getCurrentPlayers().size()) {
                    this.fall();
                    throw new Exception("This iceberg falls...");
                }
            }


        }


        else if ("east".equals(dir)) {

            if (currentIceberg.x + 1 < 0) {
                System.out.println("Sorry, you are on the edge of the map, no way to move right");
                return false;
            } else {
                currentIceberg.Remove_currentPlayers(this);
                currentIceberg = map.Icebergs[currentIceberg.y][currentIceberg.x + 1];
                currentIceberg.Add_currentPlayers(this);
                if (currentIceberg.getType().equals("hole")) this.fall();
                else if (currentIceberg.getType().equals("instable") && currentIceberg.getMaxNumOfPlayers() < currentIceberg.getCurrentPlayers().size()) {
                    this.fall();
                    throw new Exception("This iceberg falls...");
                }
            }


        }
        return true;
    }

    /**
     * @param dir direction where the player we want to save is located
     * @param map the map of the game
     * @return returns true if the action is succesful
     */
    //Method for saving the player.
    public boolean SavePlayer(String dir, Map map) {
        //Check every item in the inventory to see if there's a rope.
        if (!ContainsItem("rope")) {
            System.out.println("You don't have a rope!");
            return false;
        }

        ArrayList<PlayerBase> playerBases = new ArrayList<PlayerBase>();
        if (!checkDir(dir, map)) return false;

        if (dir.equals("north")) {
            playerBases = map.Icebergs[currentIceberg.y - 1][currentIceberg.x].getCurrentPlayers();
        } else if (dir.equals("south")) {
            playerBases = map.Icebergs[currentIceberg.y + 1][currentIceberg.x].getCurrentPlayers();
        } else if (dir.equals("west")) {
            playerBases = map.Icebergs[currentIceberg.y][currentIceberg.x - 1].getCurrentPlayers();
        } else if (dir.equals("east")) {
            playerBases = map.Icebergs[currentIceberg.y][currentIceberg.x + 1].getCurrentPlayers();
        }


        if (playerBases != null) {
            for (PlayerBase player : playerBases) {
                //Find the drowning player in the list of all players.
                //Check if the player is drowning
                if (player.isDrowning) {

                    System.out.println(player.tag + " has been saved!");
                    player.isDrowning = false;  //player is saved , so it's not drowning anymore
                    player.timer.cancel();
                    player.timer = new Timer();
                    player.currentIceberg = this.currentIceberg;
                    this.currentIceberg.Add_currentPlayers(player);

                } else {
                    System.out.println("You are trying to save a player that is not in the water!");

                }


            }
            return true;
        }


        return false;

    }

    //Checks if current player is on neighboring iceberg in savable distance from player in need.
    public boolean checkSavableDistance(PlayerBase drowningPlayer) {
        int diffY = Math.abs(this.getCurrentIceberg().getY() - drowningPlayer.getCurrentIceberg().getY());
        int diffX = Math.abs(this.getCurrentIceberg().getX() - drowningPlayer.getCurrentIceberg().getX());
        return diffY <= 1 && diffX <= 1;

    }

    /**
     * @param map map of the game
     * @param dir direction
     * @return true if the acction is succesfull
     * @throws Exception
     */
    //UseSkill method.It is overridden in Eskimo and PolarExplorer classes.
    public boolean useSkill(Map map, String dir) throws Exception {
        return true;
    }

    //Increases the heat level of the player.
    //Increases the heat level of the player.
    public void increaseHeatLevel() {
        this.heatLevel++;
    }

    /**
     * is decreasing the
     * heat level of the player
     */
    public void decreaseHeatLevel() throws IOException {
        this.heatLevel--;
        System.out.println("Heat level decreased");
        if (this.heatLevel == 0) {
            this.timer.cancel();
            this.die();
        }
    }

    /**
     * @param item name of the
     *             item that the player wants to use
     * @return true if action is succesful
     */
    //Use the item specified in the parameter.
    public boolean useItem(String item) {

        if (ContainsItem(item)) {
            try {
                for (int i = 0; i < inventory.items.size(); i++) {
                    if (inventory.items.get(i).tag.equals(item)) {
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
                        return true;
                    }
                }
            }
        } else {
            System.out.println("Impossible to use the item or no such item exists!");
            return false;
        }
        return false;
    }

    /**
     * @param item the item that should be checked if
     *             it is present in the inventory
     * @return true if the item is present in the inventory
     */
    private boolean ContainsItem(String item) {

        for (ItemBase itemBase : inventory.items) {
            if (itemBase.tag.equals(item)) return true;
        }
        return false;
    }


    //Player dies
    public void die() throws IOException {
        isDead = true;
        System.out.println("You have died. RIP ):");
        this.game.GameOver();
    }


    /**
     * the player falls in a hole
     * the timer starts and the heat level is declining
     */
    //Player falls into water
    public void fall() {
        currentIceberg.setType("hole");
        isDrowning = true;
        if (!isWearingDSuit) {    //check if the player hasn't his diving suit on
            System.out.println("You fell in the water. Ask for help to get back!");
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    try {
                        decreaseHeatLevel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            timer.scheduleAtFixedRate(tt, 0, 5000);

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

    /**
     * @return the item the player picked from the iceberg
     * @throws Exception if there is snow on the iceberg
     */
    //PickItem method which return the item.
    public boolean pickItem() throws Exception {
        if (currentIceberg.getAmountOfSnow() == 0) {
            if (currentIceberg.getItem() != null) {
                String tagString = currentIceberg.getItem().getTag();
                inventory.items.add(currentIceberg.getItem());
                currentIceberg.DeletePickedItem(); // Will delete the item from the iceberg after it was picked

                try {
                    System.out.println(tagString + " was added to your inventory.");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println(tagString + " was added to your inventory.");
                return true;
            } else {
                System.out.println("Impossible to pick, no such item on the iceberg.");
                return false;
            }
        } else {
            System.out.println("Level of snow on iceberg is not 0!");
            System.out.println("There is snow on the iceberg");
            return false;

        }

    }

    /**
     * remove one unit of snow
     * from the iceberg
     */
    //Removes snow from the iceberg
    public boolean removeSnow() {
        if (currentIceberg.getAmountOfSnow() <= 0) return false;
        int currentSnow = currentIceberg.getAmountOfSnow();
        currentIceberg.setAmountOfSnow(currentSnow - 1);     //amount of snow is decreased by 1
        return true;
    }

    /**
     * @return int from o to 4/5
     */
    //Getter for heatLevel attribute
    public int getHeatLevel() {
        return heatLevel;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }
}


