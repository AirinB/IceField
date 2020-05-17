//package com.rim.IceField;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//
//import java.awt.*;
//import java.util.ArrayList;
//
//public class GameScreenGUI implements Screen {
//    final Game game;
//    private SpriteBatch sBatch;
//
//    public HealthPanelGUI healthPanelGUI;
//    public BlizzardGUI blizzardGUI;
//
//    public Map map;
//    public MapGUI mapgui;
//
//
//    public ItemBaseGUI rope;
//    public ItemBaseGUI food;
//    public ItemBaseGUI charge;
//    public ItemBaseGUI divingSuit;
//    public ItemBaseGUI flare;
//    public ItemBaseGUI gun;
//    public ItemBaseGUI shovel;
//
//    public int count = 0;
//    public int round = 0;
//
//    //public Game game;
//
//    ArrayList<PlayerBaseGUI> playersList;
//    ArrayList<PlayerBase> players;
//    public PlayerBaseGUI playerBaseGUI1;
//    public PlayerBaseGUI playerBaseGUI2;
//
//
//
//
//    public PlayerBase  p1;
//    public  PlayerBase p2;
//
//
//    private PlayerBaseGUI currentPlayerGUI;
//
//
//
//
//    public GameScreenGUI()
//    {
//      // this.game = game;
//
//      //  healthPanelGUI = new HealthPanelGUI(game.players, 20, Gdx.graphics.getHeight() - 20);
//
//        rope = new ItemBaseGUI(new Rope());
//        food = new ItemBaseGUI(new Food());
//        charge = new ItemBaseGUI(new Charge());
//        flare = new ItemBaseGUI(new Flare());
//        gun = new ItemBaseGUI(new Gun());
//        shovel = new ItemBaseGUI(new Shovel());
//        divingSuit = new ItemBaseGUI(new DivingSuit());
//        blizzardGUI = new BlizzardGUI();
//        IcefieldGame g = new IcefieldGame();
//
//        map = new Map();
//        mapgui = new MapGUI(map);
//        mapgui.initialise();
//
//        p1 = new Eskimo();
//        p2 = new PolarExplorer();
//
//
//        playerBaseGUI1 = new PlayerBaseGUI(p1);
//        playerBaseGUI2 = new PlayerBaseGUI(p2);
//
//        playersList = new ArrayList<PlayerBaseGUI>();
//
//        playersList.add(playerBaseGUI1);
//        playersList.add(playerBaseGUI2);
//
//
//
//
//        players = new ArrayList<PlayerBase>();
//
//        players.add(playerBaseGUI1.player);
//        players.add(playerBaseGUI2.player);
//
//        game = new Game(players);
//
//        playerBaseGUI1.player.setGame(game);
//        playerBaseGUI2.player.setGame(game);
//       // playerBaseGUI1.player.
//
//        playerBaseGUI1.player.currentIceberg = game.getMap().Icebergs[0][0];
//        game.getMap().Icebergs[0][0].Add_currentPlayers(playerBaseGUI1.player);
//        playerBaseGUI1.player.isTurn = true;
//
//        playerBaseGUI2.player.currentIceberg = game.getMap().Icebergs[0][0];
//        game.getMap().Icebergs[0][0].Add_currentPlayers(playerBaseGUI2.player);
//        playerBaseGUI1.player.isTurn = true;
//
//        sBatch = new SpriteBatch();
//
//
//
//    }
//
//
//    @Override
//    public void show() {
//
//    }
//
//    @Override
//    public void render(Graphics g) {
//        Gdx.gl.glClearColor(1,0,0,1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        sBatch.begin();
//
////        mapgui.render(g);
////        food.render(g);
////        rope.render(g);
////        charge.render(g);
////        divingSuit.render(g);
////        flare.render(g);
////        shovel.render(g);
////        gun.render(g);
//
//
//
//
//        sBatch.end();
//
//
//    }
//
//    @Override
//    public void resize(int i, int i1) {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//
//    }
//}
