package com.rim.IceField;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Game game = new Game(playersList);
        for (PlayerBase player:playersList) {
            player.setGame(game);
        }
        game.getMap().Icebergs[2][3].Add_currentPlayers(e1);
        game.getMap().Icebergs[2][3].Add_currentPlayers(e2);


        Assertions.assertEquals(3, e1.getCurrentIceberg().getAmountOfSnow());
        Assertions.assertEquals(3, e2.getCurrentIceberg().getAmountOfSnow());

        Blizzard.blow(playersList, game.getMap().getIcebergs());
        Assertions.assertEquals(4,e1.heatLevel);
        //Assertions.assertEquals(4,e1.getCurrentIceberg().getAmountOfSnow());
        Assertions.assertEquals(4,e2.heatLevel);
        //Assertions.assertEquals(4,e2.getCurrentIceberg().getAmountOfSnow());

        /**
         * Test-case 16: Eskimo skill
         * Constructing the igloo and check if the heat unit doesn't decrease
         */
        e1.useSkill("east");
        e1.move("east");

        Blizzard.blow(playersList, game.getMap().getIcebergs());
        Assertions.assertEquals(4,e1.heatLevel);
       // Assertions.assertEquals(4,e1.getCurrentIceberg().getAmountOfSnow());
        Assertions.assertEquals(3,e2.heatLevel);
        //Assertions.assertEquals(3,e2.getCurrentIceberg().getAmountOfSnow());

        /**
         * Test-case 18: Heat level reaches 0
         * Testing the death of a character due to 0 heat units
         */
        for (int i = 0; i < 2; i++){
            Blizzard.blow(playersList, game.getMap().getIcebergs());
        }

        Assertions.assertEquals(4,e1.heatLevel);
        Assertions.assertEquals(1,e2.heatLevel);

        e2.useSkill("east");
        e2.move("east");

        assertEquals(2, e1.getCurrentIceberg().getCurrentPlayers().size());
        Blizzard.blow(playersList, game.getMap().getIcebergs());

        Assertions.assertEquals(1,e2.heatLevel);


        e2.move("west");
        Blizzard.blow(playersList, game.getMap().getIcebergs());
        Assertions.assertEquals(0,e2.heatLevel);

        assertTrue(e2.isDead);
        /**
         * Test-case 20: Lose scenario of the game because of Blizzard
         */
        assertTrue(game.isGameLost());
        assertTrue(game.GameOver());


    }


}

