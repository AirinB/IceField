package com.rim.IceField;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class GameConfig extends Properties {
    InputStream inputStream;
    String propFileName = "resources/configs/game.properties";

    public int mapTileSize;
    public int mapTilePadding;
    public int mapMoveDistance;
    public int playerMoveSpeed;
    public Point playerInitialCoordinates;

    public GameConfig() {
        super();
        try {
            inputStream = new FileInputStream("resources/configs/game.properties");
            this.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
            System.out.println("property file '" + propFileName + "' not found in the classpath");
            e.printStackTrace();
        }
        setProperties();
    }

    private void setProperties() {
        mapTileSize = Integer.parseInt(this.getProperty("mapTileSize"));
        mapTilePadding = Integer.parseInt(this.getProperty("mapTilePadding"));
        mapMoveDistance = mapTileSize + mapTilePadding;
        playerMoveSpeed = Integer.parseInt(this.getProperty("playerMoveSpeed"));
        playerInitialCoordinates = new Point(0, mapMoveDistance * 9 + 15);
    }

}
