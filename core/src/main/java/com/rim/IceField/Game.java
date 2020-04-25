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
    public static void newGame(ArrayList<PlayerBase> players) {

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
}
