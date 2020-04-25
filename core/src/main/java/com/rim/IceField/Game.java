package com.rim.IceField;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
        //MODIFIED TO 2 FOR TESTING PURPOSES
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
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
            try{
                player.move(text.get(1), this.getMap());
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }

        else if(text.get(0).equals("use")) {

                player.useItem(text.get(1));
            System.out.println("Action accepted!");

        }
        else if(text.get(0).equals("pick"))
        {
            System.out.println("Would you like to pick " + player.currentIceberg.getItem() + "press 1 for yes, 2 for no");
            Scanner input = new Scanner(System.in);
            int y = input.nextInt();
            if(y == 1) {
                try {
                    player.pickItem();
                    System.out.println("Action accepted!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        else if(text.get(0).equals("apply skill"))
        {

                try {
                    player.useSkill(this.getMap(), text.get(1));
                    System.out.println("Action accepted!");
                } catch (Exception e) {
                    e.printStackTrace();
                }


        }
        //Will distinguish between players based on their unique ID
        else if(text.get(0).equals("save"))
        {
                player.SavePlayer(text.get(1), players);
            System.out.println("Action accepted!");
        }

        else if(text.get(0).equals("remove snow"))
        {
            player.removeSnow();
            System.out.println("Action accepted!");
        }

        else if(text.get(0).equals("fire"))
        {
            GameOver();
            System.out.println("Action accepted!");
            if(GameOver() == false)
            {
                System.out.println("You can't fire the gun, work more!");
            }
        }

        else if(text.get(0).equals("inv"))
        {
            System.out.println("Action accepted!");
            int numFood = 0;
            int charge =0;
            int DivingSuit = 0;
            int Flare = 0;
            int Gun = 0;
            int rope =0;
            int shovel = 0;
            for(int i = 0 ; i < player.getInventory().getItems().size(); i++)
            {
                if(player.getInventory().getItemAt(i).tag == "Food")
                {
                    numFood++;
                }
                else if(player.getInventory().getItemAt(i).tag == "Charge")
                {
                    charge++;
                }
                else if(player.getInventory().getItemAt(i).tag == "Flare")
                {
                    Flare++;
                }
                else if(player.getInventory().getItemAt(i).tag == "DivingSuit")
                {
                    DivingSuit++;
                }
                else if(player.getInventory().getItemAt(i).tag == "Gun")
                {
                    Gun++;
                }
                else if(player.getInventory().getItemAt(i).tag == "rope")
                {
                    rope++;
                }
                else if(player.getInventory().getItemAt(i).tag == "shovel")
                {
                    shovel++;
                }
                System.out.println("You have: " + "Food - " + numFood + "Charge - " + charge + "Flare - " + Flare + "Diving suit - "
                + DivingSuit + "Gun - " + Gun + "Rope - "+ rope + "shovel" + shovel);
            }
        }

        else if(text.get(0).equals("help"))
        {
            System.out.println("Action accepted!");
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
            System.out.println("Action accepted!");
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
        else
        {
            System.out.println("There is no such command. Enter 'help' to see available actions");
        }
    }
}
