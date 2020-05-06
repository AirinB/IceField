package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Scanner;


public class GameMain extends BasicGame {
    public static final String GAME_IDENTIFIER = "com.rim.IceField";
    public PlayerBaseGUI playerBaseGUI;
    @Override

    public void initialise() {
        //initialize all var

        playerBaseGUI = new PlayerBaseGUI(new Eskimo());
    }

    @Override
    public void update(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerBaseGUI.updateMove("south");
        }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            playerBaseGUI.updateMove("west");
        }else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            playerBaseGUI.updateMove("north");
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            playerBaseGUI.updateMove("east");
        }


    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        playerBaseGUI.render(g);


    }

    public static void main(String[] args) throws Exception {
        ItemBase shovel = new Shovel();
        ItemBase charge = new Charge();
        ItemBase rope = new Rope();

        ItemBase food = new Food();

        ItemBase flare = new Food();

        ItemBase divingSuit = new DivingSuit();

        ItemBase gun = new Food();


        System.out.println("Introduce the number of players in the game: ");
        Scanner input = new Scanner(System.in);
        int numberOfPlayers = input.nextInt();
        //List of players.
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
        // Filling the list of players with a number of players specified by the user
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Press 1 to create a new Eskimo, 2 for a Polar Explorer");
            int m = input.nextInt();
            switch (m) {
                case 1:
                    Eskimo e = new Eskimo();
                    e.inventory.addItem(food);
                    e.inventory.addItem(flare);
                    e.inventory.addItem(rope);
                    e.inventory.addItem(charge);
                    playersList.add(e);
                    System.out.println("New Eskimo added.");
                    break;
                case 2:
                    PolarExplorer pe = new PolarExplorer();
                    pe.inventory.addItem(shovel);
                    pe.inventory.addItem(divingSuit);
                    pe.inventory.addItem(gun);

                    playersList.add(pe);
                    System.out.println("New Polar Explorer added.");

                    break;
            }
        }

        Map map = new Map();
        //map.generateStaticMap(playersList);
        map.loadMap();
        map.showMap();
        Game game = new Game(playersList, map);

        game.newGame();




    }
}






