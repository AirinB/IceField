package com.rim.IceField;

import java.util.ArrayList;
import java.util.Random;

//Blizzard class
public class Blizzard {

    //Number of times the blizzard was blowing
    public static int numOfRounds; //Blizzard blows

    //Method performing Blow functionality.
    public static void blow(ArrayList<PlayerBase> players, ArrayList<Iceberg> icebergs) {

        for (PlayerBase player : players) {
            //Checking if Eskimo hasn't Igloo
            // (Igloo protects him from decrease of heat units)
            if (!(player.currentIceberg.getHasIgloo()))
                player.decreaseHeatLevel();
        }

        //Covering icebergs with snow
        Random objGenerator = new Random(); //for random covering with snow the icebergs
        for (Iceberg iceberg : icebergs) {

            //if next random is true,
            // it would cover with +1 iceberg layer
            if(objGenerator.nextBoolean()) {
                int snow = iceberg.getAmountOfSnow();
                iceberg.setAmountOfSnow(snow + 1);
            }
        }

    }

    //Getter for numOfRounds
    public static int getNumOfRounds() {
        return numOfRounds;
    }
}
