package com.rim.IceField;

import java.util.ArrayList;

public class Blizzard {

    public static int numOfRounds;


    public static void blow(ArrayList<PlayerBase> players, ArrayList<Iceberg> icebergs){
        numOfRounds++;
        for(PlayerBase player: players) {
            if (!(player.useSkill() && player.tag.equals("Eskimo")))  //Checking if Eskimo has Igloo
                player.heatLevel--;
        }
        //Covering icebergs with snow
        for(Iceberg iceberg: icebergs){
            int snow = iceberg.getAmountOfSnow();
            iceberg.setAmountOfSnow(snow + 1);
        }



    }

    public static int getNumOfRounds() {
        return numOfRounds;
    }
}
