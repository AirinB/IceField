package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

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
        int x = col * TILE_SIZE + col * padding;
        int y = row * TILE_SIZE + row * padding;
        return new Point(x ,y);
    }

    public void render(Graphics g) {

        //Gdx.gl.glClearColor(0,0,0,1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //renderer.render();
        Point coordinate;
        sBatch.begin();
        sBatch.draw(waterTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        for(int waterRow = 0; waterRow < waterRows; waterRow++) {
//            for(int waterCol = 0; waterCol < waterCols; waterCol++) {
//
//            }
//        }

        for (int row = 0; row < map.getMAP_WIDTH(); row++) {
            for (int col = 0; col < map.getMAP_HEIGHT(); col++) {
                coordinate = getCoordinate(row, col);
                if (map.Icebergs[row][col].getIsStable()) {
                    sBatch.draw(tile1, coordinate.x, coordinate.y, TILE_SIZE, TILE_SIZE);
                }
                else if (map.Icebergs[row][col].getAmountOfSnow() > 0) {
                    sBatch.draw(tile3, coordinate.x, coordinate.y, TILE_SIZE, TILE_SIZE);
                }
                /*else if (map.Icebergs[Y][X].)
                    g.drawTexture(tiles[1], X * TILE_SIZE, Y * TILE_SIZE);*/
            }
        }
        sBatch.end();
    }

    public void dispose() {
        sBatch.dispose();
    }
}
