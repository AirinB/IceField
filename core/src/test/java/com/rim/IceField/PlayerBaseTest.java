
package com.rim.IceField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//BEFORE RUNNING THE TESTS PLEASE GO TO THE PLAYERBASE FALL METHOD AND UNCOMMENT ANOTHER TIMER TIME LINE 355
//-----------------------------------------------------------------------------------------------------------
class PlayerBaseTest {

    PlayerBase p1;
    PlayerBase p2;
    ArrayList<PlayerBase> playersList;
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
        game = new Game(playersList);
        for (PlayerBase player:playersList) {
            player.setGame(game);
        }
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
        Iceberg ice1 = new Iceberg(true, "stable", 1,true, 3, null);
        ice1.Add_currentPlayers(p1);

        p1.useItem("shovel");
        assertEquals(1, ice1.getAmountOfSnow());

        p1.inventory.addItem(shovel);
        p1.useItem("shovel");
        assertEquals(0, ice1.getAmountOfSnow());

        p1.inventory.addItem(shovel);
        p1.useItem("shovel");
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
        game.getMap().Icebergs[4][2].Add_currentPlayers(p1);
        System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());


        p1.move("east");
          System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());

        assertTrue(p1.isDrowning);
        try {
            Thread.sleep(4000);
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
        game.getMap().Icebergs[4][1].Add_currentPlayers(p1);
        game.getMap().Icebergs[4][1].Add_currentPlayers(p2);
        System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
        System.out.println("Player 2: y = " + p2.currentIceberg.getX() + " x = " +  p2.currentIceberg.getY());
        assertEquals("diving suit", game.getMap().Icebergs[4][1].getItem().tag);
        for (int i = 0; i < 3; i++) {
            p1.removeSnow();
        }
        p1.pickItem();
        p1.useItem("diving suit");

        p1.move("east");
          System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());

        assertEquals("stable", p1.getCurrentIceberg().getType());

        p1.move("east");
          System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());

        assertEquals("hole", p1.getCurrentIceberg().getType());

        assertTrue(p1.isWearingDSuit);
        assertTrue(p1.isDrowning);
        assertFalse(p2.isDrowning);
        assertEquals(4, p1.heatLevel);


        p2.move("east");

        assertEquals("stable", p2.getCurrentIceberg().getType());


        /**
         *Test-case 7: couldn't save player - no rope
         */
        //Test-case 7: couldn't save player - no rope
        p2.SavePlayer( "east");
        assertTrue( p1.isDrowning);

        /**
         *Test-case 8: save player - player is saved
         */
        Rope rope = new Rope();
        p2.inventory.addItem(rope);
        System.out.println("rope was added to your inventory");
        p2.useItem("rope");
        p2.SavePlayer("east");
        assertFalse(p1.isDrowning);
    }

    /**
     * Test-case 8: Save player (player is saved)
     */
    @Test
    void savePlayer() throws Exception {

        assertEquals(4, p1.heatLevel);
        game.getMap().Icebergs[4][2].Add_currentPlayers(p1);
        game.getMap().Icebergs[4][8].Add_currentPlayers(p2);
        System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
        System.out.println("Player 2: y = " + p2.currentIceberg.getY() + " x = " + p2.currentIceberg.getX());
        for (int i = 0; i < 3; i++){
            p2.removeSnow();
        }
        assertEquals("rope", p2.getCurrentIceberg().getItem().getTag());
        p2.pickItem();

        for (int i = 0; i < 4; i++){
            p2.move("west");
            System.out.println("Player 2: y = " + p2.currentIceberg.getY() + " x = " + p2.currentIceberg.getX());
        }

        p1.move("east");

        assertFalse(p1.isWearingDSuit);
        assertTrue(p1.isDrowning);
        assertFalse(p2.isDrowning);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(2, p1.heatLevel);

        assertEquals("hole", p1.getCurrentIceberg().getType());
        assertEquals("stable", p2.getCurrentIceberg().getType());
        System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
        System.out.println("Player 2: y = " + p2.currentIceberg.getY() + " x = " + p2.currentIceberg.getX());


        p2.SavePlayer("west");
        assertFalse(p1.isDrowning);
        System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());


        //Second player is drowning
        p2.move("west");
        assertTrue(p2.isDrowning);
        System.out.println("Player 2: y = " + p2.currentIceberg.getY() + " x = " + p2.currentIceberg.getX());


      }


    /**
     *Test-case 13: Impossible to pick the item if there is snow
     */
    @Test
    void pickItem() throws Exception {

        Food food = new Food();
        Iceberg ice1 = new Iceberg(true, "stable", 1,true, 1, food);
        ice1.Add_currentPlayers(p1);

        p1.pickItem();

        assertEquals(0, p1.inventory.getItems().size());

        /**
         *Test-case 11 Impossible to pick the item if there is snow
         */
        p1.removeSnow();
        p1.pickItem();

        assertEquals(1, p1.inventory.getItems().size());
        /**
         *Test case 12: Impossible to pick the item because it doesn't exists
         */
        Iceberg ice2 = new Iceberg(true, "stable", 1,true, 5, null);
        ice2.Add_currentPlayers(p2);

        p2.pickItem();
        assertEquals(0, p2.inventory.getItems().size());
    }


    /**
     * Test-case 3: Stepping on a hole
     */
    @Test
    void stepHole() throws Exception {
        Inventory.countGunItems = 0;
       game.getMap().Icebergs[4][4].Add_currentPlayers(p1);
       game.getMap().Icebergs[4][4].Add_currentPlayers(p2);
       System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
       System.out.println("Player 2: y = " + p2.currentIceberg.getY() + " x = " + p2.currentIceberg.getX());

       assertFalse(game.isWin());

       p1.move("west");
       System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
       assertEquals("hole", p1.getCurrentIceberg().getType());
       assertTrue(p1.isDrowning);

        Gun gun = new Gun();
        p2.inventory.addItem(gun);
        Flare flare = new Flare();
        p2.inventory.addItem(flare);
        Charge charge = new Charge();
        p2.inventory.addItem(charge);

        assertEquals(3, Inventory.countGunItems);
        assertFalse(game.isWin());

        p2.move("west");
        System.out.println("Player 2: y = " + p2.currentIceberg.getY() + " x = " + p2.currentIceberg.getX());
        assertEquals("hole", p2.getCurrentIceberg().getType());
        assertTrue(p2.isDrowning);

       assertFalse(game.isWin());

       Thread.sleep(5000);
       assertTrue(game.isGameLost());
       assertTrue(game.GameOver());

    }

    /**
     * Test-case 3: Stepping on an unstable iceberg
     */
    // I COULD NOT CHECK HERE THE FUNCTIONALITY OF TURN METHOD IF 2 PLAYERS FALL IN THE WATER
    //BUT I CHECKED THAT MANUALLY: 1ST PLAYER : remove snow x3 and move south
    //                             2ND PLAYER : move south
    //AND HERE THE GAME MUST BE LOST, BUT IT DOES NOT
    @Test
    void stepUnstable() throws Exception {
       game.getMap().Icebergs[0][0].Add_currentPlayers(p1);
       game.getMap().Icebergs[1][0].Add_currentPlayers(p2);
       assertEquals("unstable", p2.getCurrentIceberg().getType());
       assertEquals(1, p2.getCurrentIceberg().getMaxNumOfPlayers());
       System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
       System.out.println("Player 2: y = " + p2.currentIceberg.getY() + " x = " + p2.currentIceberg.getX());

       //HAVE TO CHECK
       p1.move("south");
       System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());

        assertTrue(p2.isDrowning);
        assertTrue(p1.isDrowning);

        assertEquals("unstable", p1.getCurrentIceberg().getType());
        assertEquals("unstable", p2.getCurrentIceberg().getType());

        //SMTH SIMILAR AS TURN
        int count = 0;
        for (PlayerBase player: playersList){
            if (player.isDrowning)  count++;
        }
        if (playersList.size() == count) game.GameOver();
        assertTrue(game.isGameLost());
        assertTrue(game.GameOver());
    }

    /**
     * Test-case 14: Eat food
     * Player's heat level must be increased
     */
    @Test
    void eatFood() throws Exception{
        game.getMap().Icebergs[6][2].Add_currentPlayers(p1);
          System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
        assertEquals(4,p1.heatLevel);

        p1.move("east");
          System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());

        for (int i = 0; i < 3; i++){
            p1.removeSnow();
        }
        System.out.println(p1.currentIceberg.getItem().getTag());
        assertEquals("food", p1.getCurrentIceberg().getItem().getTag());
        p1.pickItem();
        p1.useItem("food");
        assertEquals(5,p1.heatLevel);
     }

    /**
     * //Test-case 15: Polar explorer skill
     */
     @Test
     void polarSkill() throws Exception {
       game.getMap().Icebergs[0][0].Add_currentPlayers(p1);
       p1.useSkill( "south");

       assertEquals("instable", game.getMap().Icebergs[1][0].getType());

       assertEquals(1, game.getMap().Icebergs[1][0].getMaxNumOfPlayers());

       p1.useSkill( "east");
       assertEquals("hole", game.getMap().Icebergs[0][1].getType());
       assertEquals(0, game.getMap().Icebergs[0][1].getMaxNumOfPlayers());


       p1.move("south");
       System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());

       p1.useSkill("east");


       assertEquals("stable", game.getMap().Icebergs[1][1].getType());
       assertEquals(100, game.getMap().Icebergs[1][1].getMaxNumOfPlayers());
     }


    /**
     * //Test-case 4: Stuck at the edge of map
     */
    @Test
    public void mapEdge() throws Exception {
        game.getMap().Icebergs[0][0].Add_currentPlayers(p1);
        p1.move("west");

        game.getMap().Icebergs[9][9].Add_currentPlayers(p2);
        p2.move("south");
        p2.move("east");

    }

    /**
     * //Test-case 19: Win scenario
     */
    @Test
    public void winScenario() throws Exception {
        game.getMap().Icebergs[9][9].Add_currentPlayers(p1);
        game.getMap().Icebergs[2][7].Add_currentPlayers(p2);
        System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());
        System.out.println("Player 2: y = " + p2.currentIceberg.getY() + " x = " + p2.currentIceberg.getX());
        for(int i = 0; i < 3; i++) {
         p1.removeSnow();
        }
        assertEquals("gun", p1.getCurrentIceberg().getItem().getTag());
        p1.pickItem();
        assertEquals("gun", p1.getInventory().getItem("gun").getTag());

        for(int i = 0; i < 3; i++) {
            p2.removeSnow();
        }
        assertEquals("charge", p2.getCurrentIceberg().getItem().getTag());
        p2.pickItem();
        assertEquals("charge", p2.getInventory().getItem("charge").getTag());

        for (int i = 0; i < 5; i++){
            p1.move("north");
        }


        for (int i = 0; i < 4; i++){
            p1.move("west");
        }

        for(int i = 0; i < 3; i++) {
            p1.removeSnow();
        }
        assertEquals("flare", p1.getCurrentIceberg().getItem().getTag());
        p1.pickItem();
        assertEquals("flare", p1.getInventory().getItem("flare").getTag());

        System.out.println("Player 1: y = " + p1.currentIceberg.getY() + " x = " + p1.currentIceberg.getX());

       assertEquals(3, Inventory.countGunItems);

        for (int i = 0; i < 2; i++){
            p2.move("south");
        }
       // System.out.println("Player 2: y = " + p2.currentIceberg.getY() + " x = " + p2.currentIceberg.getX());

        for (int i = 0; i < 2; i++){
            p2.move("west");
        }
        System.out.println("Player 2: y = " + p2.currentIceberg.getY() + " x = " + p2.currentIceberg.getX());


        game.GameOver();
        assertTrue(game.isWin());
        assertTrue(game.GameOver());


    }
}
