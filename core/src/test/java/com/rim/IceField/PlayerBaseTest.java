
package com.rim.IceField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerBaseTest {

    PlayerBase p1;
    PlayerBase p2;
    ArrayList<PlayerBase> playersList;
    //Map map;
    Game game;


    @BeforeEach
    public void setup() {
        p1 = new PolarExplorer();
        p2 = new PolarExplorer();
        playersList = new ArrayList<PlayerBase>();
        playersList.add(p1);
        playersList.add(p2);
        //map = new Map();
        game = new Game(playersList);
        game.getMap().generateItemsOnMap();
    }

    @Test
    void removeSnowByHand() {
        //Test-case 8: Remove snow by hand
        Iceberg ice = new Iceberg(true, 1, "stable", 4, false, 5, null);
        ice.Add_currentPlayers(p1);
        p1.removeSnow();
        assertEquals(4, ice.getAmountOfSnow());
        p1.removeSnow();
        assertEquals(3, ice.getAmountOfSnow());
    }

    @Test
    void removeSnowByShovel() throws Exception{
        //Test case 9: Remove snow by shovel
        Shovel shovel = new Shovel();
        p1.inventory.addItem(shovel);
        Iceberg ice1 = new Iceberg(true, 1, "stable", 4, false, 4, null);
        ice1.Add_currentPlayers(p1);
        try {
            shovel.useItem(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(2, ice1.getAmountOfSnow());

        try {
            shovel.useItem(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(0, ice1.getAmountOfSnow());

        try {
            shovel.useItem(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(0, ice1.getAmountOfSnow());


        Iceberg ice2 = new Iceberg(true, 1, "stable", 2, false, 2, null);
        ice2.Add_currentPlayers(p2);
        Inventory i = p2.getInventory();
        for (ItemBase item : i.getItems()) {
            System.out.println(item);
        }
        p2.useItem("shovel");
        assertEquals(2, ice2.getAmountOfSnow());
    }


    @Test
    void fallWithoutDS() throws Exception {
        //Test-case 5: PLayer in the water without diving suit
        assertEquals(4, p1.heatLevel);
        game.getMap().Icebergs[1][1].Add_currentPlayers(p1);
        try {
            p1.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(p1.isDrowning);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Test-case 18: Heat level reaches 0
        assertEquals(0, p1.heatLevel);
        assertTrue(p1.isDead);
        //Test-case 20: Lose scenario of the game because of a drowning player
        assertTrue(game.isGameLost());
        assertTrue(game.GameOver());
    }


    @Test
    void fallWithDS() throws Exception {
        //Test-case 5: PLayer in the water with diving suit
        assertEquals(4, p1.heatLevel);
        game.getMap().Icebergs[1][0].Add_currentPlayers(p1);
        game.getMap().Icebergs[1][0].Add_currentPlayers(p2);
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
        assertEquals("instable", p1.getCurrentIceberg().getType());
        try {
            p1.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("hole", p1.getCurrentIceberg().getType());

        assertTrue(p1.isWearingDSuit);
        assertTrue(p1.isDrowning);
        assertFalse(p2.isDrowning);
        assertEquals(4, p1.heatLevel);

        try {
            p2.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("instable", p2.getCurrentIceberg().getType());


        //Test-case 7: couldn't save player - no rope
        p2.SavePlayer(String.valueOf(p1.getID()), "east", game.getMap());
        assertTrue( p1.isDrowning);

        Rope rope = new Rope();
        p2.inventory.addItem(rope);
        p2.SavePlayer(String.valueOf(p1.getID()), "east", game.getMap());
        assertFalse(p1.isDrowning);
    }


    @Test
    void savePlayer() throws Exception {
        //Test-case 7: Save player (player is saved)
        assertEquals(4, p1.heatLevel);
        game.getMap().Icebergs[1][1].Add_currentPlayers(p1);
        game.getMap().Icebergs[1][1].Add_currentPlayers(p2);
        try {
            p1.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(p1.currentIceberg.getX());
        //System.out.println(p1.currentIceberg.getY());

        assertFalse(p1.isWearingDSuit);
        assertTrue(p1.isDrowning);
        assertFalse(p2.isDrowning);
       // assertEquals(3, p1.heatLevel);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //assertEquals(2, p1.heatLevel);

        assertEquals("hole", p1.getCurrentIceberg().getType());
        assertEquals("instable", p2.getCurrentIceberg().getType());


        Rope rope = new Rope();
        p2.inventory.addItem(rope);
        //System.out.println(p2.currentIceberg.getX());
        //System.out.println(p2.currentIceberg.getY());
        p2.SavePlayer(String.valueOf(p1.getID()), "east", game.getMap());
        assertFalse(p1.isDrowning);
      }

    @Test
    void pickItem() {

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
    void stepHole() throws Exception {
        //Test-case 3: Step on a hole
        game.getMap().Icebergs[1][1].Add_currentPlayers(p1);
        try {
            p1.move("east", game.getMap());
        } catch (Exception e) {
                e.printStackTrace();
            }
        assertTrue(p1.isDrowning);
    }

    @Test
    void stepUnstable() throws Exception {
        //Test-case 2: Step on an unstable iceberg
        game.getMap().Icebergs[1][1].Add_currentPlayers(p1);
        game.getMap().Icebergs[1][0].Add_currentPlayers(p2);
        try {
            p2.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(p2.isDrowning);
        assertTrue(p1.isDrowning);
        assertEquals("hole", game.getMap().Icebergs[1][1].getType());
    }

    @Test
    void eatFood() {
        //Test-case 14: Eat food
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
     void polarSkill() throws Exception {
        //Test-case 15: Polar explorer skill
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


     //
     @Test
     public void testTurn(){
        //Test-case 17: Player's turn
     }

    @Test
    public void mapEdge() throws Exception {
        //Test-case 4: Stuck at the edge of map
        game.getMap().Icebergs[1][0].Add_currentPlayers(p1);
        try {
            p1.move("west",game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void winScenario() throws Exception {
        //Test-case 19: Win scenario
        game.getMap().Icebergs[0][0].Add_currentPlayers(p1);
        game.getMap().Icebergs[0][1].Add_currentPlayers(p2);
        Charge charge = new Charge();
        Gun gun = new Gun();
        Flare flare = new Flare();
        p1.inventory.addItem(charge);
        p1.inventory.addItem(gun);
        p2.inventory.addItem(flare);
        try {
            p1.move("east",game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(game.isWinForTest());
        assertTrue(game.GameOverForTest());


    }



}
