package com.rim.IceField;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void scenario() throws Exception{
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
        Eskimo e1 = new Eskimo();
        Eskimo e2 = new Eskimo();
        PolarExplorer p1 = new PolarExplorer();
        PolarExplorer p2 = new PolarExplorer();
        playersList.add(e1);
        playersList.add(e2);
        playersList.add(p1);
        playersList.add(p2);
        Game game = new Game(playersList);
        for (PlayerBase player:playersList) {
            player.setGame(game);
        }

        //DIDN'T CONTINUE BECAUSE IT MUST BE CHECKED IN TURN, METHODS SEEM TO WORKS OK
        game.getMap().Icebergs[0][0].Add_currentPlayers(e1);
        game.getMap().Icebergs[0][0].Add_currentPlayers(e2);
        game.getMap().Icebergs[0][0].Add_currentPlayers(p1);
        game.getMap().Icebergs[0][0].Add_currentPlayers(p2);

        System.out.println("Eskimo 1: y = " + e1.currentIceberg.getY() + " x = " + e1.currentIceberg.getX());
        System.out.println("Eskimo 2: y = " + e2.currentIceberg.getY() + " x = " +  e2.currentIceberg.getX());
        System.out.println("Polar 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
        System.out.println("Polar 2: y = " + p2.currentIceberg.getY() + " x = " +  p2.currentIceberg.getX());
        System.out.println("______________________________________________________________________________");

        e1.move("south");
        System.out.println("Eskimo 1: y = " + e1.currentIceberg.getY() + " x = " + e1.currentIceberg.getX());
        e1.move("east");
        System.out.println("Eskimo 1: y = " + e1.currentIceberg.getY() + " x = " + e1.currentIceberg.getX());
        e1.removeSnow();
        e1.useSkill("south");
        e1.move("south");
        System.out.println("Eskimo 1: y = " + e1.currentIceberg.getY() + " x = " + e1.currentIceberg.getX());
        assertTrue(e1.currentIceberg.getHasIgloo());
        assertEquals(5,e1.heatLevel);
        System.out.println("______________________________________________________________________________");

        e2.move("north");
        System.out.println("Eskimo 2: y = " + e2.currentIceberg.getY() + " x = " +  e2.currentIceberg.getX());
        e2.move("east");
        System.out.println("Eskimo 2: y = " + e2.currentIceberg.getY() + " x = " +  e2.currentIceberg.getX());
        assertTrue(e2.isDrowning);
        assertFalse(e2.isDead);
        System.out.println("______________________________________________________________________________");

        p1.move("south");
        System.out.println("Polar 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
        p1.move("east");
        System.out.println("Polar 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
        p1.removeSnow();
        p1.removeSnow();
        assertEquals(0, p1.currentIceberg.getAmountOfSnow());
        p1.useSkill( "south");
        System.out.println("______________________________________________________________________________");

        Rope rope = new Rope();
        p2.inventory.addItem(rope);
        p2.SavePlayer("east");
        p2.move("south");
        System.out.println("Polar 2: y = " + p2.currentIceberg.getY() + " x = " +  p2.currentIceberg.getX());
        p2.SavePlayer("south");
        assertFalse(e2.isDrowning);
        assertFalse(e2.isDead);
        p2.move("east");
        System.out.println("Polar 2: y = " + p2.currentIceberg.getY() + " x = " +  p2.currentIceberg.getX());
        p2.pickItem();
        assertEquals(2,p2.getInventory().getItems().size());
        System.out.println("______________________________________________________________________________");

        System.out.println("Eskimo 1: y = " + e1.currentIceberg.getY() + " x = " + e1.currentIceberg.getX());
        System.out.println("Eskimo 2: y = " + e2.currentIceberg.getY() + " x = " +  e2.currentIceberg.getX());
        System.out.println("Polar 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
        System.out.println("Polar 2: y = " + p2.currentIceberg.getY() + " x = " +  p2.currentIceberg.getX());
        Blizzard.blow(playersList, game.getMap().getIcebergs());
        assertEquals(5, e1.heatLevel);
        assertEquals(3, e2.heatLevel);
        assertEquals(3, p1.heatLevel);
        assertEquals(3, p1.heatLevel);
        System.out.println("______________________________________________________________________________");

        e1.move("south");
        System.out.println("Eskimo 1: y = " + e1.currentIceberg.getY() + " x = " + e1.currentIceberg.getX());
        e1.move("south");
        System.out.println("Eskimo 1: y = " + e1.currentIceberg.getY() + " x = " + e1.currentIceberg.getX());
        e1.move("east");
        System.out.println("Eskimo 1: y = " + e1.currentIceberg.getY() + " x = " + e1.currentIceberg.getX());
        e1.pickItem();
        System.out.println("______________________________________________________________________________");


        e2.move("south");
        System.out.println("Eskimo 2: y = " + e2.currentIceberg.getY() + " x = " +  e2.currentIceberg.getX());
        e2.move("east");
        System.out.println("Eskimo 2: y = " + e2.currentIceberg.getY() + " x = " +  e2.currentIceberg.getX());
        e2.move("south");
        System.out.println("Eskimo 2: y = " + e2.currentIceberg.getY() + " x = " +  e2.currentIceberg.getX());
        e2.move("south");
        System.out.println("Eskimo 2: y = " + e2.currentIceberg.getY() + " x = " +  e2.currentIceberg.getX());
        e2.move("east");
        System.out.println("Eskimo 2: y = " + e2.currentIceberg.getY() + " x = " +  e2.currentIceberg.getX());
        e2.pickItem();
        e2.removeSnow();
        e2.removeSnow();
        e2.removeSnow();
        e2.removeSnow();
        assertEquals("food", e2.getCurrentIceberg().getItem().getTag());
        e2.pickItem();
        e2.useItem("food");
        assertEquals(4, e2.heatLevel);

        System.out.println("______________________________________________________________________________");
    }

   @Test
   void newPlayers() {
       /**
        * Test-case 1: Create player
        * Testing the right list size
        */
       ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
       Eskimo e1 = new Eskimo();
       Eskimo e2 = new Eskimo();
       Game game = new Game(playersList);
       playersList.add(e1);
       playersList.add(e2);
       PolarExplorer p1 = new PolarExplorer();
       PolarExplorer p2 = new PolarExplorer();
       PolarExplorer p3 = new PolarExplorer();
       playersList.add(p1);
       playersList.add(p2);
       playersList.add(p3);
       assertEquals(5,e1.heatLevel);
       assertEquals(4,p1.heatLevel);
       assertEquals(5, playersList.size());

    }
}