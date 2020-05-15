package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.mini2Dx.core.graphics.Graphics;

import java.awt.*;

import java.awt.*;

public class MapGUI {
    private GameConfig gameConfig = new GameConfig();
    private final int TILE_SIZE = gameConfig.mapTileSize;

    private Point coordinate;
    private Map map;
    private int padding = gameConfig.mapTilePadding;
    private Texture waterTexture;
    Texture tile1;
    Texture tile2;
    Texture tile3;
    private SpriteBatch sBatch;
    private TiledMap tMap;
    private OrthogonalTiledMapRenderer renderer;

    public MapGUI(Map map) {
        this.map = map;
        initialise();
    }


    public void initialise() {
        //tMap = new TmxMapLoader().load("resources/map.tmx");
        //tiles = new Texture("resources/assets/iceberg32.png","resources/assets/water.jpg","resources/assets/snow.png")[3];
        waterTexture = new Texture("resources/assets/water_big.jpeg");
        tile1 = new Texture("resources/assets/iceberg_new.png");
        tile2 = new Texture("resources/assets/water.jpg");
        tile3 = new Texture("resources/assets/snow.png");

        sBatch = new SpriteBatch();
    }

    public void update() {

    }

    private Point getCoordinate(int row, int col) {
        int x = col * TILE_SIZE + col * padding; //+100
        int y = row * TILE_SIZE + row * padding; // + 30
        return new Point(x, y);
    }

    public void render(Graphics g) {

        Point coordinate;
        sBatch.begin();
        sBatch.draw(waterTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for (int row = 0; row < map.getMAP_HEIGHT(); row++) {
            for (int col = 0; col < map.getMAP_WIDTH(); col++) {
                int invertedY = map.getMAP_HEIGHT() - row - 1;
                if (map.Icebergs[invertedY][col].getType().equals("hole")) {
                    continue;
                } else {
                    coordinate = getCoordinate(row, col);
                    if (map.Icebergs[row][col].getIsStable()) {
                        sBatch.draw(tile1, coordinate.x, coordinate.y, TILE_SIZE, TILE_SIZE);
                    } else if (map.Icebergs[row][col].getAmountOfSnow() > 0) {
                        sBatch.draw(tile3, coordinate.x, coordinate.y, TILE_SIZE, TILE_SIZE);
                    }
                }
            }
        }
        sBatch.end();
    }

    public void dispose() {
        sBatch.dispose();
    }
}
