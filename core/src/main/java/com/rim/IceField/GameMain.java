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
    public BlizzardGUI blizzardGUI;
    @Override

    public void initialise() {
        //initialize all var

        playerBaseGUI = new PlayerBaseGUI(new Eskimo());
        blizzardGUI = new BlizzardGUI();

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

        blizzardGUI.update();


    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        playerBaseGUI.render(g);
        blizzardGUI.render(g);


    }

    public static void main(String[] args) throws Exception {



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
                    playersList.add(e);
                    System.out.println("New Eskimo added.");
                    break;
                case 2:
                    PolarExplorer pe = new PolarExplorer();
                    playersList.add(pe);
                    System.out.println("New Polar Explorer added.");

                    break;
            }
        }

        Game game = new Game(playersList);
        for (PlayerBase player:playersList) {
            player.setGame(game);
        }
        //TEST
        Rope rp = new Rope();
        Food fd1 = new Food();
        Food fd2 = new Food();
        Food fd3 = new Food();
        Food fd4 = new Food();
        Food fd5 = new Food();

     playersList.get(1).inventory.addItem(rp);
   /*  playersList.get(0).inventory.addItem(fd1);
        playersList.get(0).inventory.addItem(fd2);
        playersList.get(0).inventory.addItem(fd3);
        playersList.get(0).inventory.addItem(fd4);
        playersList.get(0).inventory.addItem(fd5);*/
        game.getMap().Icebergs[0][0].setItem(fd1);
//TEST
        //System.out.println("If you want to load the inputs from file, enter 1");

       // int a = input.nextInt();
    /*    if( a == 1){
            //newGame from file
            Scanner scannerChoice = new Scanner(System.in);
            System.out.println("Enter the path\n");
            String path = scannerChoice.nextLine();
            scannerChoice.close();
        }*/
            game.newGame();




    }
}






