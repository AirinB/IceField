package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Scanner;


public class GameMain extends BasicGame {
    public static final String GAME_IDENTIFIER = "com.rim.IceField";

    @Override
    public void initialise() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {

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


        System.out.println("If you want to load the inputs from file, enter 1");

        int a = input.nextInt();
        if( a == 1){
            //newGame from file
            Scanner scannerChoice = new Scanner(System.in);
            System.out.println("Enter the path\n");
            String path = scannerChoice.nextLine();
            scannerChoice.close();
        }else{
            game.newGame();
        }



    }
}






