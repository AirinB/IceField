package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class GameMain extends BasicGame implements InputProcessor {
    public static final String GAME_IDENTIFIER = "com.rim.IceField";

    public int count = 0;
    public int round = 0;
    public Map map;

    public MapGUI mapgui;
    public HealthPanelGUI healthPanelGUI;
    public BlizzardGUI blizzardGUI;

    public StartMenuGUI startMenuGUI;


    public Game game;
    public PlayerBaseGUI playerBaseGUI1;
    public PlayerBaseGUI playerBaseGUI2;
    public PlayerBase p1;
    public PlayerBase p2;
    ArrayList<PlayerBaseGUI> playersList;
    ArrayList<PlayerBase> players;

    int currentRound = 0;

    private PlayerBaseGUI currentPlayerGUI;

    private EndGame endGameMessage;

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
        for (PlayerBase player : playersList) {
            player.setGame(game);
            player.currentIceberg = game.getMap().Icebergs[0][0];
            game.getMap().Icebergs[0][0].Add_currentPlayers(player);
        }
        //TEST

        try {
            game.newGame();

        } catch (Exception e) {
            if (e.getMessage().equals("End of Game")) System.out.println("Game is over");
            else if (e.getMessage().equals("End of turn and end of Game")) System.out.println("Game is over");
        }


    }

    @Override
    public void initialise() {

        startMenuGUI = new StartMenuGUI(this);
    }

    public void initialiseGame(HashMap<String, Integer> playersCount) {
        int eskimoCount = playersCount.get("eskimo");
        int explorerCount = playersCount.get("explorer");
        System.out.println("eskimoCount: " + eskimoCount);
        System.out.println("explorerCount: " + explorerCount);
        p1 = new Eskimo();
        p2 = new PolarExplorer();
       // p1.isWearingDSuit = true;
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


        //the blizzard
        blizzardGUI = new BlizzardGUI();

        mapgui = new MapGUI(game.getMap());
        mapgui.initialise();

        endGameMessage = new EndGame();
    }

    private void nextPlayer() {
        if (count == playersList.size()) {
            count = 0;

            if (game.randomBlow[currentRound]) {
                try {
                    Blizzard.blow(players, game.getMap().getIcebergs());
                    System.out.println("heat level" + p1.heatLevel + " " + p2.heatLevel);
                    System.out.println("the blizzard is blowing");
                    blizzardGUI.blow = true;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            currentRound++;
        }

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
        if (!GameState.getGameState()) {
            return;
        }

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

        blizzardGUI.update();

    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        if (!GameState.getGameState()) {
            startMenuGUI.render(g);
        } else {
            mapgui.render(g);


            playerBaseGUI1.render(g);
            playerBaseGUI2.render(g);


            healthPanelGUI.render();

            blizzardGUI.render(g);

            endGameMessage.render(false);
        }
        GameStage.stage.draw();
        GameStage.stage.act();

    }

    public boolean readPlayerActions() throws Exception {
        boolean playerIsMoving = currentPlayerGUI.player.getMovingState();
        if (playerIsMoving) {
            return false;
        }

        //--------------------------------------------------------------Move player------------------------------------------
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

            return currentPlayerGUI.player.pickItem();

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            return currentPlayerGUI.player.removeSnow();

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            return currentPlayerGUI.player.useItem("diving suit");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            return currentPlayerGUI.player.useItem("food");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            return currentPlayerGUI.player.useItem("rope");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
            return currentPlayerGUI.player.useItem("shovel");

            //-----------------------------------------------------Save player----------------------------------------------------------------

        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                if (currentPlayerGUI.player.SavePlayer("north")) {
                    currentPlayerGUI.player.updateSave(currentPlayerGUI.player.game.getMap().Icebergs[currentPlayerGUI.player.currentIceberg.getY() - 1][currentPlayerGUI.player.currentIceberg.getX()].getCurrentPlayers());

                    return true;
                }
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                if (currentPlayerGUI.player.SavePlayer("south")) {
                    currentPlayerGUI.player.updateSave(currentPlayerGUI.player.game.getMap().Icebergs[currentPlayerGUI.player.currentIceberg.getY() + 1][currentPlayerGUI.player.currentIceberg.getX()].getCurrentPlayers());
                    return true;
                }

            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                if (currentPlayerGUI.player.SavePlayer("west")) {
                    currentPlayerGUI.player.updateSave(currentPlayerGUI.player.game.getMap().Icebergs[currentPlayerGUI.player.currentIceberg.getY()][currentPlayerGUI.player.currentIceberg.getX() - 1].getCurrentPlayers());
                    return true;
                }
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                if (currentPlayerGUI.player.SavePlayer("east")) {
                    currentPlayerGUI.player.updateSave(currentPlayerGUI.player.game.getMap().Icebergs[currentPlayerGUI.player.currentIceberg.getY()][currentPlayerGUI.player.currentIceberg.getX() + 1].getCurrentPlayers());
                    return true;
                }
            }


            // --------------------------------------------------------Skills-------------------------------------------------------------
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if (currentPlayerGUI.player.getTag().equals("PolarExplorer") && (currentPlayerGUI.player.useSkill("north"))) {
                    currentPlayerGUI.updateMaxlpayers(currentPlayerGUI.player.game.getMap().Icebergs[currentPlayerGUI.player.currentIceberg.getY() + 1][currentPlayerGUI.player.currentIceberg.getX()].getMaxNumOfPlayers());
                    return true;
                } else if (currentPlayerGUI.player.getTag().equals("Eskimo") && (currentPlayerGUI.player.useSkill("north"))) {
                    currentPlayerGUI.updateIgloo("north");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if (currentPlayerGUI.player.getTag().equals("PolarExplorer") && (currentPlayerGUI.player.useSkill("south"))) {
                    currentPlayerGUI.updateMaxlpayers(currentPlayerGUI.player.game.getMap().Icebergs[currentPlayerGUI.player.currentIceberg.getY() - 1][currentPlayerGUI.player.currentIceberg.getX()].getMaxNumOfPlayers());
                    return true;
                } else if (currentPlayerGUI.player.getTag().equals("Eskimo") && (currentPlayerGUI.player.useSkill("south"))) {
                    currentPlayerGUI.updateIgloo("south");
                    return true;
                }
            }


        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if (currentPlayerGUI.player.getTag().equals("PolarExplorer") && (currentPlayerGUI.player.useSkill("west"))) {
                    currentPlayerGUI.updateMaxlpayers(currentPlayerGUI.player.game.getMap().Icebergs[currentPlayerGUI.player.currentIceberg.getY()][currentPlayerGUI.player.currentIceberg.getX() - 1].getMaxNumOfPlayers());
                    return true;
                } else if (currentPlayerGUI.player.getTag().equals("Eskimo") && (currentPlayerGUI.player.useSkill("west"))) {
                    currentPlayerGUI.updateIgloo("west");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if (currentPlayerGUI.player.getTag().equals("PolarExplorer") && (currentPlayerGUI.player.useSkill("east"))) {
                    currentPlayerGUI.updateMaxlpayers(currentPlayerGUI.player.game.getMap().Icebergs[currentPlayerGUI.player.currentIceberg.getY()][currentPlayerGUI.player.currentIceberg.getX() + 1].getMaxNumOfPlayers());
                    return true;
                } else if (currentPlayerGUI.player.getTag().equals("Eskimo") && (currentPlayerGUI.player.useSkill("east"))) {
                    currentPlayerGUI.updateIgloo("east");
                    return true;
                }
            }

            //------------------------------------------------------------------------------------------------------------

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (currentPlayerGUI.player.game.isWin()) {
                currentPlayerGUI.player.game.GameOver();
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
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


}