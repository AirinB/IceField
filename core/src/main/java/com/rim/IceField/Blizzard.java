package com.rim.IceField;

import java.util.ArrayList;
import java.util.Timer;
import java.util.Random;

//Blizzard class
public class Blizzard {

    //Number of times the blizzard was blowing
    public static int numOfRounds; //Blizzzard blows

    //Method performing Blow functionality.
    public static void blow(ArrayList<PlayerBase> players, ArrayList<Iceberg> icebergs) {

        System.out.println("blow()");


        for (PlayerBase player : players) {
            if (!(player.currentIceberg.getHasIgloo()))  //Checking if Eskimo hasn't Igloo (Igloo protects him from decrease of heat units)
                player.decreaseHeatLevel();  //Decreasing heatLevel of players
        }
        //Covering icebergs with snow
        for (Iceberg iceberg : icebergs) {
            int snow = iceberg.getAmountOfSnow();
            iceberg.setAmountOfSnow(snow + 1);
        }

    }

    //Getter for numOfRounds
    public static int getNumOfRounds() {
        System.out.println("getNumOfRounds");
        return numOfRounds;
    }
}
