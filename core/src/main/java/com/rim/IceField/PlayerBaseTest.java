package com.rim.IceField;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerBaseTest {

    @Test
    void removeSnowByHand() {
        //Test-case 8: Remove snow by hand
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
        //Test case 9: Remove snow by shovel
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


    @Test
    void fallWithoutDS() {
        //Test-case 5: PLayer in the water without diving suit
        PlayerBase  p1 = new PolarExplorer();
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
        playersList.add(p1);
        assertEquals(4,p1.heatLevel);
        Game game = new Game();
        game.newGame(playersList);
        game.getMap().Icebergs[1][1].Add_currentPlayers(p1);
        try {
            p1.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(true,p1.isDrowning);
        assertEquals(3 ,p1.heatLevel);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Test-case 18: Heat level reaches 0
        assertEquals(0,p1.heatLevel);
        assertEquals(true,p1.isDead);
        //assertEquals(true, Game.isGameLost());
        //assertEquals(true, Game.GameOver());
    }



    @Test
    void fallWithDS() {
        //Test-case 5: PLayer in the water with diving suit
        PlayerBase  p1 = new PolarExplorer();
        PlayerBase  p2 = new PolarExplorer();
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
        playersList.add(p1);
        playersList.add(p2);
        assertEquals(4,p1.heatLevel);
        Game game = new Game();
        game.newGame(playersList);
        game.getMap().Icebergs[1][1].Add_currentPlayers(p1);
        game.getMap().Icebergs[1][0].Add_currentPlayers(p1);
        DivingSuit ds = new DivingSuit();
        p1.inventory.addItem(ds);
        try {
            ds.useItem(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            p1.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(true,p1.isWearingDSuit);
        assertEquals(true,p1.isDrowning);
        assertEquals(4,p1.heatLevel);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(4,p1.heatLevel);

        try {
            p2.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Wait for the SavePlayer modification
        //p2.SavePlayer(p1);
        //assertEquals(false,p1.isDrowning);
    }


    @Test
    void pickItem() {
        PlayerBase  p1 = new PolarExplorer();
        Food food = new Food();
        Iceberg ice1 = new Iceberg(true,1,"stable", 4, false,1, food);
        ice1.Add_currentPlayers(p1);

        //Test-case 13: Impossible to pick the item if there is snow
        try {
            p1.pickItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(0, p1.inventory.getItems().size());

        // Test-case 11 : Picking the item
        p1.removeSnow();
        try {
            p1.pickItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, p1.inventory.getItems().size());

        //Test case 12: Impossible to pick the item because it doesn't exists
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

    @Test
    void stepHole(){
        //Test-case 3: Step on a hole
        PlayerBase  p1 = new PolarExplorer();
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
        playersList.add(p1);
        Game game = new Game();
        game.newGame(playersList);
        game.getMap().Icebergs[1][1].Add_currentPlayers(p1);
        try {
            p1.move("east", game.getMap());
        } catch (Exception e) {
                e.printStackTrace();
            }
        assertEquals(true, p1.isDrowning);
    }

    @Test
    void stepUnstable(){
        //Test-case 2: Step on an unstable iceberg
        PlayerBase  p1 = new PolarExplorer();
        PlayerBase  p2 = new PolarExplorer();
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
        playersList.add(p1);
        playersList.add(p2);
        Game game = new Game();
        game.newGame(playersList);
        game.getMap().Icebergs[1][1].Add_currentPlayers(p1);
        game.getMap().Icebergs[1][0].Add_currentPlayers(p2);
        try {
            p2.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(true, p2.isDrowning);
        assertEquals(true, p1.isDrowning);
        assertEquals("hole", game.getMap().Icebergs[1][1].getType());
    }

    @Test
    void eatFood() {
        //Test-case 14: Eat food
        PlayerBase  p1 = new PolarExplorer();
        Food food = new Food();
        p1.inventory.addItem(food);
        assertEquals(4,p1.heatLevel);
        try {
        food.useItem(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(5,p1.heatLevel);
     }

     @Test
     void polarSkill(){
        //Test-case 15: Polar explorer skill
         PlayerBase  p1 = new PolarExplorer();
         ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
         playersList.add(p1);
         Game game = new Game();
         game.newGame(playersList);
         game.getMap().Icebergs[1][0].Add_currentPlayers(p1);
         try {
         p1.useSkill(game.getMap(), "east");
         } catch (Exception e) {
             e.printStackTrace();
         }
         assertEquals("instable", game.getMap().Icebergs[1][1].getType());

         try {
             p1.useSkill(game.getMap(), "north");
         } catch (Exception e) {
             e.printStackTrace();
         }
         assertEquals("stable", game.getMap().Icebergs[1][0].getType());

         try {
             p1.move("east",game.getMap());
         } catch (Exception e) {
             e.printStackTrace();
         }

         try {
             p1.useSkill(game.getMap(), "east");
         } catch (Exception e) {
             e.printStackTrace();
         }
         assertEquals("hole", game.getMap().Icebergs[1][2].getType());
     }


}