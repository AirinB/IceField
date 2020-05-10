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

public class MapGUI {
    private final int TILE_SIZE = 32;               //Defines the size of the tile in pixels
    private Map map;
    Texture tile1;
    Texture tile2;
    Texture tile3;
    Sprite sprite;
    private SpriteBatch sBatch;
    private TiledMap tMap;
    private OrthogonalTiledMapRenderer renderer;

    public MapGUI(Map map) {
        this.map = map;
        initialise();
    }


    public void initialise() {
        //tMap = new TmxMapLoader().load("assets/map.tmx");
        //tiles = new Texture("assets/iceberg32.png","assets/water.jpg","assets/snow.png")[3];
        tile1 = new Texture("assets/iceberg32.png");
        tile2 = new Texture("assets/water.jpg");
        tile3 = new Texture("assets/snow.png");

        sprite = new Sprite(tile1, 100, 100, 100, 100);
        sprite.setPosition(40, 40);
        sBatch = new SpriteBatch();
    }

    public void update() {

    }

    public void render(Graphics g) {
        //Gdx.gl.glClearColor(0,0,0,1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //renderer.render();
        sBatch.begin();

        for (int Y = 0; Y < map.getMAP_WIDTH(); Y++) {
            for (int X = 0; X < map.getMAP_HEIGHT(); X++) {
                if (map.Icebergs[Y][X].getIsStable()) {
                    //g.drawTexture(tile1, X * TILE_SIZE, Y * TILE_SIZE);
                    sBatch.draw(tile1,X * TILE_SIZE, Y * TILE_SIZE, 32, 32);
                }
                else if (map.Icebergs[Y][X].getAmountOfSnow() > 0) {
                    //g.drawTexture(tile3, X * TILE_SIZE, Y * TILE_SIZE);
                    sBatch.draw(tile3, X * TILE_SIZE, Y * TILE_SIZE, 32, 32);
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
