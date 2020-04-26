package com.rim.IceField;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

   @Test
   void newPlayers() {
       //Test-case 1 : Create players
       ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
       Eskimo e1 = new Eskimo();
       Eskimo e2 = new Eskimo();
       //Map map = new Map();
       Game game = new Game(playersList);
       playersList.add(e1);
       playersList.add(e2);
       PolarExplorer p1 = new PolarExplorer();
       PolarExplorer p2 = new PolarExplorer();
       PolarExplorer p3 = new PolarExplorer();
       playersList.add(p1);
       playersList.add(p2);
       playersList.add(p3);
       try {
           game.newGame();
       } catch (Exception e) {
           e.printStackTrace();
       }
       assertEquals(5,e1.heatLevel);
       assertEquals(4,p1.heatLevel);
       assertEquals(5, playersList.size());

    }
}