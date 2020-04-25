package com.rim.IceField;

import java.util.ArrayList;

//Blizzard class
public class Blizzard {

    //Number of times the blizzard was blowing
    public static int numOfRounds; //Blizzard blows

    //Method performing Blow functionality.
    public static void blow(ArrayList<PlayerBase> players, Map map) {

        for (PlayerBase player : players) {
            if (!(player.currentIceberg.getHasIgloo()))  //Checking if Eskimo hasn't Igloo (Igloo protects him from decrease of heat units)
                player.decreaseHeatLevel();  //Decreasing heatLevel of players
        }
        //Covering icebergs with snow
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j < 9; j++) {
                int snow = map.Icebergs[i][j].getAmountOfSnow();
                map.Icebergs[i][j].setAmountOfSnow(snow + 1);
            }
        }
    }

    //Getter for numOfRounds
    public static int getNumOfRounds() {
        return numOfRounds;
    }
}
