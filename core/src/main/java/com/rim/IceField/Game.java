package com.rim.IceField;

import java.util.ArrayList;

public class Game {
    private Map map;

    public Game(){
        this.map = new Map();
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public static void GameOver()
    {
        System.out.println("Game Over!");
    }

    public void newGame(ArrayList<PlayerBase> players){
        //map.generateItemsOnMap();
        //finish

        System.out.println("Game started!");
    }


}
