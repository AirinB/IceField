
package com.rim.IceField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerBaseTest {

    PlayerBase p1;
    PlayerBase p2;
    ArrayList<PlayerBase> playersList;
    Map map;
    Game game;

    /**
     * Initial setup for the tests: player's ,
     * game, map creation
     */

    @BeforeEach
    public void setup() {
        p1 = new PolarExplorer();
        p2 = new PolarExplorer();
        playersList = new ArrayList<PlayerBase>();
        playersList.add(p1);
        playersList.add(p2);
        map = new Map();
        game = new Game(playersList);
      //  map.generateItemsOnMap();
    }


    /**
     * Test-case 8: Remove snow by hand
     */
    @Test
    void removeSnowByHand() {
        Iceberg ice = new Iceberg(true, "stable", 1,true, 5, null);
        ice.Add_currentPlayers(p1);
        p1.removeSnow();
        assertEquals(4, ice.getAmountOfSnow());
        p1.removeSnow();
        assertEquals(3, ice.getAmountOfSnow());
    }

    /**
     * Test case 9: Remove snow by shovel
     * @throws Exception if there is no shovel
     * in the player's inventory
     */
    @Test
    void removeSnowByShovel() throws Exception{
        Shovel shovel = new Shovel();
        p1.inventory.addItem(shovel);
        Iceberg ice1 = new Iceberg(true, "stable", 1,true, 4, null);
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


        Iceberg ice2 = new Iceberg(true, "stable", 1,true, 2, null);
        ice2.Add_currentPlayers(p2);
        Inventory i = p2.getInventory();
        for (ItemBase item : i.getItems()) {
            System.out.println(item);
        }
        p2.useItem("shovel");
        assertEquals(2, ice2.getAmountOfSnow());
    }


    /**
     * Test-case 5: PLayer in the water without diving suit
     * @throws Exception if there is no shovel
     * no iceberg the player moves to
     */
    @Test
    void fallWithoutDS() throws Exception {
        assertEquals(4, p1.heatLevel);
        map.Icebergs[1][1].Add_currentPlayers(p1);
        try {
            p1.move("east",  map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(p1.isDrowning);
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * Test-case 18: Heat level reaches 0
         * due to the player being in the water
         */
        assertEquals(0, p1.heatLevel);
        assertTrue(p1.isDead);
        /**
         * Test-case 20: Lose scenario of the game because
         * of a drowning player
         */
        assertTrue(game.isGameLost());
        assertTrue(game.GameOver());
    }


    /**
     * Test-case 6: PLayer in the water with diving suit
     * @throws Exception if there is no shovel
     * no iceberg the player moves to
     */
    @Test
    void fallWithDS() throws Exception {
        assertEquals(4, p1.heatLevel);
        map.Icebergs[1][0].Add_currentPlayers(p1);
        map.Icebergs[1][0].Add_currentPlayers(p2);
        DivingSuit ds = new DivingSuit();
        p1.inventory.addItem(ds);
        try {
            ds.useItem(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            p1.move("east", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("instable", p1.getCurrentIceberg().getType());
        try {
            p1.move("east", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("hole", p1.getCurrentIceberg().getType());

        assertTrue(p1.isWearingDSuit);
        assertTrue(p1.isDrowning);
        assertFalse(p2.isDrowning);
        assertEquals(4, p1.heatLevel);

        try {
            p2.move("east", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("instable", p2.getCurrentIceberg().getType());


        /**
         *Test-case 7: couldn't save player - no rope
         */
        //Test-case 7: couldn't save player - no rope
        p2.SavePlayer( "east", map);
        assertTrue( p1.isDrowning);

        /**
         *Test-case 8: save player - player is saved
         */
        Rope rope = new Rope();
        p2.inventory.addItem(rope);
        p2.SavePlayer("east", map);
        assertFalse(p1.isDrowning);
    }

    /**
     * Test-case 8: Save player (player is saved)
     */
    @Test
    void savePlayer()  {

        assertEquals(4, p1.heatLevel);
        map.Icebergs[1][1].Add_currentPlayers(p1);
        map.Icebergs[1][1].Add_currentPlayers(p2);
        try {
            p1.move("east", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        p2.SavePlayer("east", map);
        assertFalse(p1.isDrowning);
      }


    /**
     *Test-case 13: Impossible to pick the item if there is snow
     */
    @Test
    void pickItem() throws Exception {

        Food food = new Food();
        Iceberg ice1 = new Iceberg(true, "stable", 1,true, 1, food);
        ice1.Add_currentPlayers(p1);
        try {
            p1.pickItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(0, p1.inventory.getItems().size());

        /**
         *Test-case 11 Impossible to pick the item if there is snow
         */
        p1.removeSnow();
        try {
            p1.pickItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, p1.inventory.getItems().size());
        /**
         *Test case 12: Impossible to pick the item because it doesn't exists
         */
        Iceberg ice2 = new Iceberg(true, "stable", 1,true, 5, null);
        ice2.Add_currentPlayers(p1);
        try {
            p2.pickItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(0, p2.inventory.getItems().size());
    }


    /**
     * Test-case 3: Stepping on a hole
     */
    @Test
    void stepHole() throws Exception {
        map.Icebergs[1][1].Add_currentPlayers(p1);
        try {
            p1.move("east", map);
        } catch (Exception e) {
                e.printStackTrace();
            }
        assertTrue(p1.isDrowning);
    }

    /**
     * Test-case 3: Stepping on an unstable iceberg
     */
    @Test
    void stepUnstable() throws Exception {
        map.Icebergs[1][1].Add_currentPlayers(p1);
        map.Icebergs[1][0].Add_currentPlayers(p2);
        try {
            p2.move("east", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(p2.isDrowning);
        assertTrue(p1.isDrowning);
        assertEquals("hole", map.Icebergs[1][1].getType());
    }

    /**
     * Test-case 14: Eat food
     * Player's heat level must be increased
     */
    @Test
    void eatFood() {
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

    /**
     * //Test-case 15: Polar explorer skill
     */
     @Test
     void polarSkill() throws Exception {
         map.Icebergs[1][0].Add_currentPlayers(p1);
         try {
         p1.useSkill(map, "east");
         } catch (Exception e) {
             e.printStackTrace();
         }
         assertEquals("instable", map.Icebergs[1][1].getType());

         try {
             p1.useSkill(map, "north");
         } catch (Exception e) {
             e.printStackTrace();
         }
         assertEquals("stable", map.Icebergs[1][0].getType());

         try {
             p1.move("east",map);
         } catch (Exception e) {
             e.printStackTrace();
         }

         try {
             p1.useSkill(map, "east");
         } catch (Exception e) {
             e.printStackTrace();
         }
         assertEquals("hole", map.Icebergs[1][2].getType());
     }


    /**
     * //Test-case 4: Stuck at the edge of map
     */
    @Test
    public void mapEdge() throws Exception {
        map.Icebergs[1][0].Add_currentPlayers(p1);
        try {
            p1.move("west",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * //Test-case 19: Win scenario
     */
    @Test
    public void winScenario() throws Exception {
        map.Icebergs[0][0].Add_currentPlayers(p1);
        map.Icebergs[0][1].Add_currentPlayers(p2);
        Charge charge = new Charge();
        Gun gun = new Gun();
        Flare flare = new Flare();
        p1.inventory.addItem(charge);
        p1.inventory.addItem(gun);
        p2.inventory.addItem(flare);
        try {
            p1.move("east",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(game.isWinForTest());
        assertTrue(game.GameOverForTest());


    }



}
