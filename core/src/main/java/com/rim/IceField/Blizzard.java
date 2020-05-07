package com.rim.IceField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//Blizzard class
public class Blizzard {

    //Number of times the blizzard was blowing
    public static int numOfRounds; //Blizzard blows
    /**
     * @param players all the players in the game
    // * @param map     the map of the game
     *                The Blizzard randomply blows and also randomly
     *                covers some icebergs with snow
     */
    //Method performing Blow functionality.
    public  static void blow(ArrayList<PlayerBase> players,Iceberg[][] icebergs) throws IOException {
        System.out.println("The blizzard is blowing, some icebergs are covered in snow and some people will lose body heat.");

        for (PlayerBase player : players) {
            if (!(player.currentIceberg.getHasIgloo()))  //Checking if Eskimo hasn't Igloo (Igloo protects him from decrease of heat units)
                player.decreaseHeatLevel();  //Decreasing heatLevel of players
            System.out.println(player + "Your body temperature has decreased.");
        }
        //Covering icebergs with snow
        //I set it to 2 *2 right now because we don't have 9*9 icebergs yet ( I can test the app this way)
        Random objGenerator = new Random();
        for (int i =0;i<icebergs.length;i++)
        {
            for(int j = 0;j<icebergs.length;j++) {
                if (objGenerator.nextBoolean()) {
                    int snow = icebergs[i][j].getAmountOfSnow();
                    icebergs[i][j].setAmountOfSnow(snow + 1);
                }
            }
        }
    }

    //Getter for numOfRounds
    public static int getNumOfRounds() {
        return numOfRounds;
    }
}
