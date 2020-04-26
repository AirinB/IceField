package com.rim.IceField;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


class BlizzardTest {

    @Test
    void blow() throws Exception {
        //Test-case 10: Blizzard Blowing
        Eskimo e1 = new Eskimo();
        Eskimo e2 = new Eskimo();
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
        playersList.add(e1);
        playersList.add(e2);
        Game game = new Game(playersList);
        game.getMap().generateItemsOnMap();
        game.getMap().Icebergs[0][0].Add_currentPlayers(e1);
        game.getMap().Icebergs[1][0].Add_currentPlayers(e2);


        Assertions.assertEquals(1,game.getMap().Icebergs[0][0].getAmountOfSnow());
        Assertions.assertEquals(1,game.getMap().Icebergs[1][0].getAmountOfSnow());

        Blizzard.blow(playersList, game.getMap());
        Assertions.assertEquals(4,e1.heatLevel);
        Assertions.assertEquals(2,game.getMap().Icebergs[0][0].getAmountOfSnow());

        Assertions.assertEquals(4,e2.heatLevel);
        Assertions.assertEquals(2,game.getMap().Icebergs[1][0].getAmountOfSnow());

        //Test-case 16: Polar explorer skill
        e1.useSkill(game.getMap(),"east");
        try {
            e1.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Blizzard.blow(playersList, game.getMap());
        Assertions.assertEquals(4,e1.heatLevel);
        Assertions.assertEquals(3,game.getMap().Icebergs[0][1].getAmountOfSnow());
        Assertions.assertEquals(3,e2.heatLevel);
        Assertions.assertEquals(3,game.getMap().Icebergs[1][0].getAmountOfSnow());

        //Test-case 18: Heat level reaches 0
        Blizzard.blow(playersList, game.getMap());
        Blizzard.blow(playersList, game.getMap());
        Blizzard.blow(playersList, game.getMap());
        Assertions.assertEquals(4,e1.heatLevel);
        Assertions.assertEquals(0,e2.heatLevel);
        assertTrue(e2.isDead);
        //Test-case 20: Lose scenario of the game because of Blizzard
        assertTrue(game.isGameLost());


    }


}

