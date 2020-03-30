package com.rim.IceField;

import java.util.ArrayList;

//Class Game
public class Game {

    //Instance of Map, shared between the classes
    private static Map map;

    //Constructor
    public Game() {
        map = new Map();
    }

    //Getter for map
    public Map getMap() {
        System.out.println("getMap()");
        return map;
    }

    //Static method GameOver for finishing the game.
    public static boolean GameOver(ArrayList<PlayerBase> players) {

        System.out.println("GameOver()");

        boolean end = false;             //Boolean for check the end of the game
        boolean playersCheck = false;    //Boolean for check if all the players stay on the same iceberg
        boolean flareGunCheck = false;   //Boolean to check if all the parts of flare gun are collected

        //Checking if any of the player died, then the game is lost
        for (PlayerBase player : players) {
            if (player.isDead) {
                System.out.println("Game lost!");
                end = true;
            }
        }

        //Checking if all the players stand on the same iceberg and it's not a hole
        for (Iceberg ice : map.getIcebergs()) {
            if (ice.getCurrentPlayers().size() == players.size() && ice.getType() != "hole")
                playersCheck = true;
        }

        //Checking if flare gun was collected
        if (playersCheck) {
            for (PlayerBase player : players) {
                if (Inventory.countGunItems == 3) {
                    flareGunCheck = true;
                    System.out.println("The flare gun is collected");
                }
                if (!flareGunCheck) {
                    Inventory.countGunItems = 0;
                }
            }
        }

        //Checking if all the conditions are preserved for winning the game
        if (playersCheck && flareGunCheck) {
            System.out.println("Game Over! You Win");
            end = true;
        }


        return end;

    }


    //Static method for starting a new game.
    public static void newGame(ArrayList<PlayerBase> players) {

        System.out.println("newGame()");

        map.generateItemsOnMap();           //Generating items on map
        System.out.println("Game started!");

      /*  while (true) {
            try {
                for (PlayerBase player : players) {

                    player.turn();

                    if (GameOver(players)) {
                        break;
                    }

                }
            }
            catch (Exception e){
                break;
            }

        }*/


    }


}
