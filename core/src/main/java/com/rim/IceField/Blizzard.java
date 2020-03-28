package com.rim.IceField;

import java.util.ArrayList;

public class Blizzard {

    //I am not sure about the static here , but actually we don't need the instance of the Blizzard, blizzard is shared among the classes, so
    // we can just do Blizzard.blow() when it is required.

    public static int numOfRounds;


    public static void blow(ArrayList<PlayerBase> players){
        numOfRounds++;
        for(PlayerBase player: players){
            player.heatLevel--;
        }

    }

    public static int getNumOfRounds() {
        return numOfRounds;
    }
}
