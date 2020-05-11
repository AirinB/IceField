package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Scanner;



public class GameMain extends BasicGame {
    public static final String GAME_IDENTIFIER = "com.rim.IceField";
    public ArrayList<PlayerBase> players;

    public PlayerBaseGUI playerBaseGUI;
    public BlizzardGUI blizzardGUI;
    public ItemBaseGUI rope;
    public ItemBaseGUI food;
    public ItemBaseGUI charge;
    public ItemBaseGUI divingSuit;
    public ItemBaseGUI flare;
    public ItemBaseGUI gun;
    public ItemBaseGUI shovel;
    public Map map;
    public MapGUI mapgui;

    public HealthPanelGUI healthPanelGUI;


    @Override
    public void initialise() {
        //initialize all var
        PlayerBase p = new Eskimo();
        PlayerBase p1 = new PolarExplorer();
        p1.heatLevel = 2;

        players = new ArrayList<PlayerBase>();
        players.add(p);
        players.add(p1);

        p.isTurn = true;
        playerBaseGUI = new PlayerBaseGUI(p);
        rope = new ItemBaseGUI(new Rope());
        food = new ItemBaseGUI(new Food());
        charge = new ItemBaseGUI(new Charge());
        flare = new ItemBaseGUI(new Flare());
        gun = new ItemBaseGUI(new Gun());
        shovel = new ItemBaseGUI(new Shovel());
        divingSuit =  new ItemBaseGUI(new DivingSuit());
        blizzardGUI = new BlizzardGUI();
        healthPanelGUI = new HealthPanelGUI(players, 20, 450);

        p.getInventory().addItem(new Flare());
        map = new Map();
        mapgui = new MapGUI(map);
        mapgui.initialise();

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

        // draw the igloo
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            playerBaseGUI.updateIgloo("south");
        }

        if(Gdx.input.isKeyPressed(Input.Keys.I)){
            playerBaseGUI.updateMaxlpayers(5);
        }


        food.update(140, 160);
        rope.update(220, 190);
        charge.update(300, 100);
        flare.update(111, 275);
        divingSuit.update(400, 120);
        shovel.update(295, 200);
        gun.update(289, 266);
        // playerBaseGUI.updatePlayerLife(0);
        blizzardGUI.update();

        healthPanelGUI.render();




    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        mapgui.render(g);
        food.render(g);
        rope.render(g);
        charge.render(g);
        flare.render(g);
        shovel.render(g);
        gun.render(g);
        divingSuit.render(g);
        playerBaseGUI.render(g);
        blizzardGUI.render(g);
        healthPanelGUI.render();

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
            player.currentIceberg = game.getMap().Icebergs[0][0];
            game.getMap().Icebergs[0][0].Add_currentPlayers(player);
        }
        //TEST




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
        try {
        game.newGame();

        }
        catch (Exception e)
        {
            if (e.getMessage().equals("End of Game")) System.out.println("Game is over");
            else if (e.getMessage().equals("End of turn and end of Game"))  System.out.println("Game is over");
            return;
        }


    }
}