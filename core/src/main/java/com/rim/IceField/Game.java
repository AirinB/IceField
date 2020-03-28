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

    public void setMap(Map map) {
        map = map;
    }

    public static void GameOver()
    {

        //for (Iceberg ice: map.getIcebergs() ){
          //  if ( (ice.getCurrentPlayers().size() == ice.getNum())  &&  (Inventory.isFlareGunAssembled()) )
              //  System.out.println("Game Over!");

       // }
    }

    public void newGame(ArrayList<PlayerBase> players){

        map.generateItemsOnMap(players);
        for (PlayerBase player: players ){
            player.turn();
        }

        System.out.println("Game started!");
    }


}
