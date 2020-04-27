package com.rim.IceField;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Scanner;


public class GameMain extends BasicGame {
    public static final String GAME_IDENTIFIER = "com.rim.IceField";


    //private Texture texture;

    @Override
    public void initialise() {
        //texture = new Texture("mini2Dx.png");
    }

    @Override
    public void update(float delta) {
        //Test
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        //g.drawTexture(texture, 0f, 0f);
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

        System.out.println("If you want to load the inputs from file, enter 1");

        if( input.nextInt() == 1){
            //newGame from file
            Scanner scannerChoice = new Scanner(System.in);
            System.out.println("Enter the path\n");
            String path = scannerChoice.nextLine();
            scannerChoice.close();
            ArrayList<ArrayList<String>> inputsFromFile = game.loadInputs(path);
            game.newGameFromFile(inputsFromFile );
        }else{
            game.newGame();
        }



    }
}






