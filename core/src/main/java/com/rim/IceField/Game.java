package com.rim.IceField;

import java.util.ArrayList;
import java.util.Random;

//Class Game
public class Game {

    //Instance of Map, shared between the classes
    private static Map map;
    private static ArrayList<PlayerBase> players; // the players belong to the game
    private static final int maxRounds = 10;
    private static int currentRound;
    private static boolean[] randomBlow; //if the array element is true, the wind would blow


    //Constructor
    public Game() {
        map = new Map();
        currentRound += 1;
        randomBlow = new boolean[maxRounds];

        Random objGenerator = new Random(1);
        for (int i = 1; i < 10; i++) {
            randomBlow[i] = objGenerator.nextBoolean();
        }
    }

    public static boolean processInput() {
        return false;
    }

    public static boolean processOutput() {
        return false;
    }

    //Getter for map
    public Map getMap() {
        return map;
    }


    //Static method GameOver for finishing the game.
    public static boolean GameOver() {


        if (isGameLost()) return true;

        //Checking if all the conditions are preserved for winning the game
        if (isWin()) {
            System.out.println("Game Over! You Win");
            return true;
        }

        return false;
    }

    public static boolean isGameLost() {
        //Checking if any of the player died, then the game is lost
        for (PlayerBase player : players) {
            if (player.isDead) {
                System.out.println("Game lost!");
                return true;
            }
        }
        return false;
    }


    public static boolean isWin() {
        //Checking if all the conditions are preserved for winning the game

        boolean playersCheck = false;    //Boolean for check if all the players stay on the same iceberg
        //boolean flareGunCheck = false;   //Boolean to check if all the parts of flare gun are collected

        //Checking if all the players stand on the same iceberg and it's not a hole
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map.Icebergs[i][j].getCurrentPlayers().size() == players.size() && !map.Icebergs[i][j].getType().equals("hole"))
                    playersCheck = true;
            }
        }

        //Checking if flare gun was collected
        if (playersCheck) {
            if (Inventory.countGunItems == 3) {
                System.out.println("The flare gun is collected");
                return true;
            }
        }
        return false;
    }


    //Static method for starting a new game.
    public void newGame(ArrayList<PlayerBase> players) {

        map.generateItemsOnMap();           //Generating items on map
        System.out.println("Game started!");

        //there can be a limited number of rounds
        if (currentRound > maxRounds) GameOver();

    }


    public static void Turn(PlayerBase player) throws Exception {
        if (!player.isTurn()) throw new Exception("It's not this player's turn");
        int round = 0;
        while (round < 4) {
            try {
                if (processInput()) {// the round increases only if the action was successful
                    round++;
                }
            } catch (Exception e) {
                //end of turn
            }
        }
    }

    //Will be continuously called in the game loop.
    public void UserInteraction(ArrayList<String> text, PlayerBase player)
    {
        if(text.get(0).equals("move ")) {
            if (text.get(1).equals("NORTH")) {

                try {
                    player.move("north", this.getMap());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (text.get(1).equals("SOUTH")) {
                try {
                    player.move("south", this.getMap());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (text.get(0).equals("LEFT")) {
                try {
                    player.move("west", this.getMap());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (text.get(0).equals("RIGHT")) {
                try {
                    player.move("east", this.getMap());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        else if(text.get(0).equals("use")) {
            if (text.get(1).equals("food")) {
                player.useItem("Food");
            } else if (text.get(1).equals("shovel")) {
                player.useItem("Shovel");
            } else if (text.get(1).equals("diving suit")) {
                player.useItem("Diving Suit");
            }
        }
        else if(text.get(0).equals("pick"))
        {
            try {
                player.pickItem();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if(text.get(0).equals("apply skill"))
        {
            if(text.get(1).equals("NORTH"))
            {
                try {
                    player.useSkill(this.getMap(), "north");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(text.get(1).equals("SOUTH"))
            {
                try {
                    player.useSkill(this.getMap(), "south");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(text.get(1).equals("WEST"))
            {
                try {
                    player.useSkill(this.getMap(), "west");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(text.get(1).equals("EAST"))
            {
                try {
                    player.useSkill(this.getMap(), "east");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        //Will distinguish between players based on their unique ID
        else if(text.get(0).equals("save"))
        {
                player.SavePlayer(text.get(1), players);
        }

        else if(text.get(0).equals("remove snow"))
        {
            player.removeSnow();
        }

        else if(text.get(0).equals("fire"))
        {
            GameOver();
        }

        else if(text.get(0).equals("inv"))
        {
            for(int i = 0 ; i < player.getInventory().getItems().size(); i++)
            {
                System.out.println(player.getInventory().getItemAt(i));
            }
        }

        else if(text.get(0).equals("help"))
        {
            // Show all possible inputs
            System.out.println("Move NORTH / SOUTH / LEFT / RIGHT - will move the player in the corresponding direction.");
            System.out.println("use food / shovel / diving suit - will make use of the corresponding item if the user has it in the inventory.");
            System.out.println("pick - will pick the item from the iceberg that the player is on.");
            System.out.println("apply skill NORTH / SOUTH / WEST / EAST - will apply apply current player's skill in the corresponding direction");
            System.out.println("save (player ID) - will save the player with the given id if they are in trouble and the conditions for saving them are met.");
            System.out.println("remove snow - will remove one unit of snow from the current iceberg.");
            System.out.println("fire - will fire the gun if all conditions for firing it are met.");
            System.out.println("inv - will display all items in current player's inventory.");
            System.out.println("info - will display current player's heat level, number of moves left and the number of gun parts collected by the team.");

        }

        else if(text.get(0).equals("info"))
        {
            System.out.println("Heat level: " + player.getHeatLevel());
            System.out.println("Moves left: ");
            System.out.print( 4 - player.numOfMoves);
            int partsCollected = 0;
            //for every player
            for(int i = 0; i<players.size(); i++)
            {
                //we check every item in their inventory if it is a Gun, flare or charge
                for(int j = 0; j < players.get(i).getInventory().getItems().size(); j++)
                {
                    if(players.get(i).getInventory().getItems().get(j).getTag() == "Gun" ||
                            players.get(i).getInventory().getItems().get(j).getTag() == "Flare"||
                            players.get(i).getInventory().getItems().get(j).getTag() == "Charge")
                    {
                        partsCollected++;
                        //break;
                    }


                }
            }
            System.out.println("Parts collected:" + partsCollected);

        }
    }
}
