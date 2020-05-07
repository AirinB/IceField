package com.rim.IceField;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


class BlizzardTest {
    /**
     * Test-case 10: Blizzard Blowing
     * Testing the functionality of the blow method
     */
    @Test
    void blow() throws Exception {
        Eskimo e1 = new Eskimo();
        Eskimo e2 = new Eskimo();
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
        playersList.add(e1);
        playersList.add(e2);
        Map map = new Map();
        Game game = new Game(playersList);
        map.generateMap();
        map.Icebergs[0][0].Add_currentPlayers(e1);
        map.Icebergs[1][0].Add_currentPlayers(e2);


        Assertions.assertEquals(1,map.Icebergs[0][0].getAmountOfSnow());
        Assertions.assertEquals(1,map.Icebergs[1][0].getAmountOfSnow());

     //   Blizzard.blow(playersList, map);
        Assertions.assertEquals(4,e1.heatLevel);
        Assertions.assertEquals(2,map.Icebergs[0][0].getAmountOfSnow());

        Assertions.assertEquals(4,e2.heatLevel);
        Assertions.assertEquals(2,map.Icebergs[1][0].getAmountOfSnow());

        /**
         * Test-case 16: Eskimo skill
         * Constructing the igloo and check if the heat unit doesn't decrease
         */
        e1.useSkill(map,"east");
        try {
            e1.move("east", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Blizzard.blow(playersList, map);
        Assertions.assertEquals(4,e1.heatLevel);
        Assertions.assertEquals(4, map.Icebergs[0][1].getAmountOfSnow());
        Assertions.assertEquals(3,e2.heatLevel);
        Assertions.assertEquals(3, map.Icebergs[1][0].getAmountOfSnow());

        /**
         * Test-case 18: Heat level reaches 0
         * Testing the death of a character due to 0 heat units
         */
      //  Blizzard.blow(playersList,  map);
     //   Blizzard.blow(playersList,  map);
      //  Blizzard.blow(playersList,  map);
        Assertions.assertEquals(4,e1.heatLevel);
        Assertions.assertEquals(0,e2.heatLevel);
        assertTrue(e2.isDead);
        /**
         * Test-case 20: Lose scenario of the game because of Blizzard
         */
        assertTrue(game.isGameLost());


    }


}

