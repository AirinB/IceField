package com.rim.IceField;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Scanner;

//TODO move is not working
// Inventroy needs to be refactored

public class GameMain extends BasicGame implements InputProcessor{
    public int count = 0;
    public int round = 0;
    public static final String GAME_IDENTIFIER = "com.rim.IceField";

    public BlizzardGUI blizzardGUI;
    public Map map;
    public MapGUI mapgui;


    public ItemBaseGUI rope;
    public ItemBaseGUI food;
    public ItemBaseGUI charge;
    public ItemBaseGUI divingSuit;
    public ItemBaseGUI flare;
    public ItemBaseGUI gun;
    public ItemBaseGUI shovel;

    public HealthPanelGUI healthPanelGUI;


    public Game game;
    ArrayList<PlayerBaseGUI> playersList;
    ArrayList<PlayerBase> players;
    public PlayerBaseGUI playerBaseGUI1;
    public PlayerBaseGUI playerBaseGUI2;

    public PlayerBase  p1;
    public  PlayerBase p2;
    private PlayerBaseGUI currentPlayerGUI;


    @Override
    public void initialise() {
        p1 = new Eskimo();
        p2 = new PolarExplorer();
        playerBaseGUI1 = new PlayerBaseGUI(p1);
        playerBaseGUI2 = new PlayerBaseGUI(p2);

        playersList = new ArrayList<PlayerBaseGUI>();
        playersList.add(playerBaseGUI1);
        playersList.add(playerBaseGUI2);




        players = new ArrayList<PlayerBase>();
        players.add(playerBaseGUI1.player);
        players.add(playerBaseGUI2.player);

        healthPanelGUI = new HealthPanelGUI(players, 20, Gdx.graphics.getHeight() - 20);

        game = new Game(players);
        playerBaseGUI1.player.setGame(game);
        playerBaseGUI2.player.setGame(game);


        playerBaseGUI1.player.currentIceberg = game.getMap().Icebergs[0][0];
        game.getMap().Icebergs[0][0].Add_currentPlayers(playerBaseGUI1.player);
        playerBaseGUI1.player.isTurn = true;

        playerBaseGUI2.player.currentIceberg = game.getMap().Icebergs[0][0];
        game.getMap().Icebergs[0][0].Add_currentPlayers(playerBaseGUI2.player);
        playerBaseGUI1.player.isTurn = true;


        rope = new ItemBaseGUI(new Rope());
        food = new ItemBaseGUI(new Food());
        charge = new ItemBaseGUI(new Charge());
        flare = new ItemBaseGUI(new Flare());
        gun = new ItemBaseGUI(new Gun());
        shovel = new ItemBaseGUI(new Shovel());
        divingSuit = new ItemBaseGUI(new DivingSuit());

        //the blizzard
        blizzardGUI = new BlizzardGUI();

        map = new Map();
        mapgui = new MapGUI(map);
        mapgui.initialise();
    }

    private void nextPlayer() {
        if (count == playersList.size()) count = 0;

        if (currentPlayerGUI != null) {
            currentPlayerGUI.player.isTurn = false;
        }

        currentPlayerGUI = playersList.get(count);
        currentPlayerGUI.player.isTurn = true;
        round = 0;
        count++;
    }

    @Override
    public void update(float delta) {
        if (round == 4) {
            nextPlayer();
        }
        if (currentPlayerGUI == null) nextPlayer();

        try {
            if (currentPlayerGUI.player.isDrowning) {
                nextPlayer();
                return;
            }

            if (readPlayerActions()) {
                round++;
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        food.update(140, 160);
//        rope.update(220, 190);
//        charge.update(300, 100);
//        flare.update(111, 275);
//        divingSuit.update(400, 120);
//        shovel.update(295, 200);
//        gun.update(289, 266);


//        blizzardGUI.update();
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
        divingSuit.render(g);


        flare.render(g);
        shovel.render(g);
        gun.render(g);


        playerBaseGUI1.render(g);
        playerBaseGUI2.render(g);


        healthPanelGUI.render();

        blizzardGUI.render(g);


    }

    public boolean readPlayerActions() throws Exception {
        boolean playerIsMoving = currentPlayerGUI.player.getMovingState();
        if (playerIsMoving) {
            return false;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (currentPlayerGUI.player.move("north")) {
                currentPlayerGUI.updateMove("north");
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (currentPlayerGUI.player.move("south")) {
                currentPlayerGUI.updateMove("south");
                return true;
            }


        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (currentPlayerGUI.player.move("west")) {
                currentPlayerGUI.updateMove("west");
                return true;
            }


        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (currentPlayerGUI.player.move("east")) {
                currentPlayerGUI.updateMove("east");
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if (currentPlayerGUI.player.pickItem()) {
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            if (currentPlayerGUI.player.removeSnow()) {
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            if (currentPlayerGUI.player.useItem("diving suit")) {
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            if (currentPlayerGUI.player.useItem("food")) {
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            if  (currentPlayerGUI.player.useItem("rope")) {
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
            if (currentPlayerGUI.player.useItem("shovel")) {
                return true;
            }

        } else if ((Gdx.input.isKeyJustPressed(Input.Keys.L)) && Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (currentPlayerGUI.player.SavePlayer("north")) {
                return true;
            }

        } else if ((Gdx.input.isKeyJustPressed(Input.Keys.L)) && Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (currentPlayerGUI.player.SavePlayer("south")) {
                return true;
            }

        } else if ((Gdx.input.isKeyJustPressed(Input.Keys.L)) && Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (currentPlayerGUI.player.SavePlayer("west")) {
                return true;
            }

        } else if ((Gdx.input.isKeyJustPressed(Input.Keys.L)) && Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (currentPlayerGUI.player.SavePlayer("east")) {
                return true;
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((currentPlayerGUI.player.useSkill("south") && currentPlayerGUI.player.getTag().equals("Eskimo"))) {
                    currentPlayerGUI.updateIgloo("south");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((currentPlayerGUI.player.useSkill("north") && currentPlayerGUI.player.getTag().equals("Eskimo"))) {
                    currentPlayerGUI.updateIgloo("north");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((currentPlayerGUI.player.useSkill("west") && currentPlayerGUI.player.getTag().equals("Eskimo"))) {
                    currentPlayerGUI.updateIgloo("west");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((currentPlayerGUI.player.useSkill("east") && currentPlayerGUI.player.getTag().equals("Eskimo"))) {
                    currentPlayerGUI.updateIgloo("east");
                    return true;
                }
            }


        } else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((currentPlayerGUI.player.useSkill("south") && currentPlayerGUI.player.getTag().equals("PolarExplorer"))) {
                    System.out.println("polar skill");
                    return true;
                }
            }


        } else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((currentPlayerGUI.player.useSkill("north") && currentPlayerGUI.player.getTag().equals("PolarExplorer"))) {
                    System.out.println("polar skill");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((currentPlayerGUI.player.useSkill("west") && currentPlayerGUI.player.getTag().equals("PolarExplorer"))) {
                    System.out.println("polar skill");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((currentPlayerGUI.player.useSkill("east") && currentPlayerGUI.player.getTag().equals("PolarExplorer"))) {
                    System.out.println("polar skill");
                    return true;
                }
            }

        }else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (currentPlayerGUI.player.game.isWin()) currentPlayerGUI.player.game.GameOver();
            return true;

        }else if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            currentPlayerGUI.player.getPosition();
            return true;
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
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





        try {
            game.newGame();

        }
        catch (Exception e)
        {
            if (e.getMessage().equals("End of Game")) System.out.println("Game is over");
            else if (e.getMessage().equals("End of turn and end of Game"))  System.out.println("Game is over");
        }


    }
}