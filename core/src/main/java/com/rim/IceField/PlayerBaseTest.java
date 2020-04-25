package com.rim.IceField;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerBaseTest {

    @Test
    void removeSnowByHand() {
        PlayerBase  p1 = new PolarExplorer();
        Iceberg ice = new Iceberg(true,1,"stable", 4, false,5,null);
        ice.Add_currentPlayers(p1);
        p1.removeSnow();
        assertEquals(4, ice.getAmountOfSnow());
        p1.removeSnow();
        assertEquals(3, ice.getAmountOfSnow());

    }

    @Test
    void removeSnowByShovel() {
        PlayerBase  p1 = new PolarExplorer();
        Shovel shovel = new Shovel();
        p1.inventory.addItem(shovel);
        Iceberg ice1 = new Iceberg(true,1,"stable", 4, false,4, null);
        ice1.Add_currentPlayers(p1);
        p1.useItem(shovel);
        assertEquals(2, ice1.getAmountOfSnow());
        p1.useItem(shovel);
        assertEquals(0, ice1.getAmountOfSnow());
        p1.useItem(shovel);
        assertEquals(0, ice1.getAmountOfSnow());

        PlayerBase  p2 = new PolarExplorer();
        Iceberg ice2 = new Iceberg(true,1,"stable", 2, false,2, null);
        ice2.Add_currentPlayers(p2);
        Inventory i = p2.getInventory();
        for (ItemBase item: i.getItems()){
            System.out.println(item);
        }
        p2.useItem(shovel);
        assertEquals(2, ice2.getAmountOfSnow());
    }

    //Modified player in the water to a more logical way
    //1. Player without diving dies
//    @Test
//    void fallWithoutDS() {
//        PlayerBase  p1 = new PolarExplorer();
//        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
//        playersList.add(p1);
//        assertEquals(4,p1.heatLevel);
//        Game game = new Game();
//        game.newGame(playersList);
//        game.getMap().Icebergs[1][1].Add_currentPlayers(p1);
//        try {
//            p1.move("east", game.getMap());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        assertEquals(true,p1.isDrowning);
//        assertEquals(3 ,p1.heatLevel);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        assertEquals(0,p1.heatLevel);
//        assertEquals(true,p1.isDead);
//        assertEquals(true, Game.isGameLost());
//        assertEquals(true, Game.GameOver());
//    }
//
//
//
//    @Test
//    void fallWithDS() {
//        PlayerBase  p1 = new PolarExplorer();
//        PlayerBase  p2 = new PolarExplorer();
//        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
//        playersList.add(p1);
//        playersList.add(p2);
//        assertEquals(4,p1.heatLevel);
//        Game game = new Game();
//        game.newGame(playersList);
//        game.getMap().Icebergs[1][1].Add_currentPlayers(p1);
//        game.getMap().Icebergs[1][0].Add_currentPlayers(p1);
//        DivingSuit ds = new DivingSuit();
//        p1.inventory.addItem(ds);
//        try {
//            ds.useItem(p1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            p1.move("east", game.getMap());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        assertEquals(true,p1.isWearingDSuit);
//        assertEquals(true,p1.isDrowning);
//        assertEquals(4,p1.heatLevel);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        assertEquals(4,p1.heatLevel);
//
//        try {
//            p2.move("east", game.getMap());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        p2.SavePlayer(p1);
//        assertEquals(false,p1.isDrowning);
//    }


    @Test
    void pickItem() {
        PlayerBase  p1 = new PolarExplorer();
        Food food = new Food();
        Iceberg ice1 = new Iceberg(true,1,"stable", 4, false,1, food);
        ice1.Add_currentPlayers(p1);

        //Test-case 13
        try {
            p1.pickItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(0, p1.inventory.getItems().size());

        // Test-case 11
        p1.removeSnow();
        try {
            p1.pickItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, p1.inventory.getItems().size());

        //Test case 13
        PlayerBase  p2 = new PolarExplorer();
        Iceberg ice2 = new Iceberg(true,1,"stable", 4, false,1, null);
        ice2.Add_currentPlayers(p1);
        try {
            p2.pickItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(0, p2.inventory.getItems().size());




    }
}