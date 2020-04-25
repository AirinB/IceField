
package com.rim.IceField;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlizzardTest {

    @Test
    void blow() {
        //Test-case 10: Blizzard Blowing
        Eskimo e1 = new Eskimo();
        Eskimo e2 = new Eskimo();
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
        playersList.add(e1);
        playersList.add(e2);
        Game game = new Game();
        game.newGame(playersList);
        game.getMap().Icebergs[0][0].Add_currentPlayers(e1);
        game.getMap().Icebergs[1][0].Add_currentPlayers(e2);


        assertEquals(1,game.getMap().Icebergs[0][0].getAmountOfSnow());
        assertEquals(1,game.getMap().Icebergs[1][0].getAmountOfSnow());

        Blizzard.blow(playersList, game.getMap());
        assertEquals(4,e1.heatLevel);
        assertEquals(2,game.getMap().Icebergs[0][0].getAmountOfSnow());

        assertEquals(4,e2.heatLevel);
        assertEquals(2,game.getMap().Icebergs[1][0].getAmountOfSnow());

        //Test-case 16: Polar explorer skill
        e1.useSkill(game.getMap(),"east");
        try {
            e1.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Blizzard.blow(playersList, game.getMap());
        assertEquals(4,e1.heatLevel);
        assertEquals(3,game.getMap().Icebergs[0][1].getAmountOfSnow());
        assertEquals(3,e2.heatLevel);
        assertEquals(3,game.getMap().Icebergs[1][0].getAmountOfSnow());

        //Test-case 18: Heat level reaches 0
        Blizzard.blow(playersList, game.getMap());
        Blizzard.blow(playersList, game.getMap());
        Blizzard.blow(playersList, game.getMap());
        assertEquals(4,e1.heatLevel);
        assertEquals(0,e2.heatLevel);
        assertEquals(true,e2.isDead);
        //Test-case 20: Lose scenario of the game because of Blizzard
        //assertEquals(true, game.isGameLost());


    }
}

