package com.rim.IceField;

import java.util.ArrayList;

public class Game {
    private static Map map;

    public Game(){
        map = new Map();
    }

    public Map getMap() {
        return map;
    }


    public  static boolean GameOver(ArrayList<PlayerBase> players)
    {
        boolean end = false;
        boolean playersCheck = false;
        boolean flareGunCheck = false;

        //Checking if any of the player died
        for (PlayerBase player : players) {
            if (player.isDead) {
                System.out.println("Game lost!");
                end = true;
            }
        }

        //Checking if all the players stand on the same iceberg and it's not a hole
        for (Iceberg ice: map.getIcebergs() ){
            if (ice.getCurrentPlayers().size() == players.size() && ice.getType() != "hole")
                playersCheck = true;
        }

        //Checking if flare gun was collected
        if (playersCheck) {
            for (PlayerBase player : players) {
                if (player.Inv.count == 3 ) {
                    flareGunCheck = true;
                    System.out.println("The flare gun is collected");
                }
                if (!flareGunCheck) {
                        player.Inv.count = 0;
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

    public static void newGame(ArrayList<PlayerBase> players){

        map.generateItemsOnMap();
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
