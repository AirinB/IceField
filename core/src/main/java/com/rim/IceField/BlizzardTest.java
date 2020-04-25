package com.rim.IceField;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlizzardTest {

    @Test
    void blow() {
        Eskimo e1 = new Eskimo();
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
        playersList.add(e1);
        Game game = new Game();
        game.newGame(playersList);
        game.getMap().Icebergs[0][0].Add_currentPlayers(e1);
        Blizzard.blow(playersList, game.getMap());
        assertEquals(4,e1.heatLevel);
        assertEquals(2,game.getMap().Icebergs[0][0].getAmountOfSnow());
        assertEquals(2,game.getMap().Icebergs[0][1].getAmountOfSnow());

        e1.useSkill(game.getMap(),"east");
        try {
            e1.move("east", game.getMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Blizzard.blow(playersList, game.getMap());
        assertEquals(4,e1.heatLevel);
        assertEquals(3,game.getMap().Icebergs[0][1].getAmountOfSnow());




    }
}