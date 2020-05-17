package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Scanner;

// TODO: move is not working
// Inventroy needs to be refactored

public class NewGameMain extends com.badlogic.gdx.Game {
    public int count = 0;
    public int round = 0;
    public static final String GAME_IDENTIFIER = "com.rim.IceField";


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
    public BlizzardGUI blizzardGUI;

    public StartMenuGUI startMenuGUI;


    public Game game;
    ArrayList<PlayerBaseGUI> playersList;
    ArrayList<PlayerBase> players;
    public PlayerBaseGUI playerBaseGUI1;
    public PlayerBaseGUI playerBaseGUI2;

    public PlayerBase  p1;
    public  PlayerBase p2;
    private PlayerBaseGUI currentPlayerGUI;


    @Override
    public void create() {
        setScreen(new TestScreen());
    }

}