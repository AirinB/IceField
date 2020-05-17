package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.mini2Dx.core.graphics.Graphics;

import java.awt.*;


public class MapGUI {
    private GameConfig gameConfig = new GameConfig();
    private final int TILE_SIZE = gameConfig.mapTileSize;

    private ItemBaseGUI[][] itemsOnMap = new ItemBaseGUI[10][10];
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

        for (int row = 0; row < map.getMAP_HEIGHT(); row++) {
            for (int col = 0; col < map.getMAP_WIDTH(); col++) {
                int invertedY = map.getMAP_HEIGHT() - row - 1;
                Iceberg tmpIceberg = map.Icebergs[invertedY][col];
                ItemBase tmpItem = tmpIceberg.getItem();
                if (tmpItem.getTag() != null) {
                    Point tmpPosition = getCoordinate(row, col);
                    tmpPosition.y += 5;
                    tmpPosition.x += 5;
                    this.itemsOnMap[row][col] = new ItemBaseGUI(tmpItem, tmpPosition);
                }
            }
        }
    }


    public void initialise() {
        //tMap = new TmxMapLoader().load("resources/map.tmx");
        //tiles = new Texture("resources/assets/iceberg32.png","resources/assets/water.jpg","resources/assets/snow.png")[3];
        waterTexture = new Texture("resources/assets/water_big.jpeg");
        tile1 = new Texture("resources/assets/iceberg_new.png");
        tile2 = new Texture("resources/assets/water.jpg");
        tile3 = new Texture("resources/assets/snow_mask_1x.png");

        sBatch = new SpriteBatch();
    }

    public void update() {

    }

    private Point getCoordinate(int row, int col) {
        int x = col * TILE_SIZE + col * padding; //+100
        int y = row * TILE_SIZE + row * padding; // + 30
        return new Point(x, y);
    }

    private void renderItem(int row, int col) {
        if (this.itemsOnMap[row][col] != null) {
            if (!(this.itemsOnMap[row][col].item.isObtained())) this.itemsOnMap[row][col].render();

        }
    }

    public void render(Graphics g) {

        Point coordinate;
        sBatch.begin();
        sBatch.draw(waterTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        sBatch.end();

        for (int row = 0; row < map.getMAP_HEIGHT(); row++) {
            for (int col = 0; col < map.getMAP_WIDTH(); col++) {
                int invertedY = map.getMAP_HEIGHT() - row - 1;
                Iceberg tmpIceberg = map.Icebergs[invertedY][col];
                    coordinate = getCoordinate(row, col);
                    sBatch.begin();
                    if (tmpIceberg.getMaxNumOfPlayers()>=1 && tmpIceberg.getAmountOfSnow() == 0) {
                        sBatch.draw(tile1, coordinate.x, coordinate.y, TILE_SIZE, TILE_SIZE);
                    } if (tmpIceberg.getAmountOfSnow() > 0) {
                        sBatch.draw(tile3, coordinate.x, coordinate.y, TILE_SIZE, TILE_SIZE);
                    }
                    sBatch.end();
                    if (tmpIceberg.getAmountOfSnow() == 0) {
                        renderItem(row, col);
                    }

            }
        }
    }

    public void dispose() {
        sBatch.dispose();
    }



}
