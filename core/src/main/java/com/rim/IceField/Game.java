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

    // I thing that we have to do GameOver and NewGame static because we don't need the instance of the class, it's the only one

    public  static boolean GameOver(ArrayList<PlayerBase> players)
    {
        boolean end = false;
        boolean playersCheck = false;
        boolean flareGunCheck = false;

        for (Iceberg ice: map.getIcebergs() ){
            if (ice.getCurrentPlayers().size() == players.size())
                playersCheck = true;
        }
        for (PlayerBase player: players ) {
            if (player.Inv.isFlareGunAssembled())
                flareGunCheck = true;
            if (player.isDead) {
                System.out.println("Game lost!");
                end = true;
            }
        }


        if (playersCheck && flareGunCheck) {
            System.out.println("Game Over! You Win");
            end = true;
        }

        return end;


    }

    public static void newGame(ArrayList<PlayerBase> players){

        map.generateItemsOnMap();
        System.out.println("Game started!");

        while (true) {
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

        }


    }


}
