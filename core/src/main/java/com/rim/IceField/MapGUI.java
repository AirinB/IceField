package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.mini2Dx.core.graphics.Graphics;

public class MapGUI {
    private final int TILE_SIZE = 48;               //Defines the size of the tile in pixels
    private Map map;
    Texture iceberg;
    Texture water;
    Texture snow;

    private SpriteBatch sBatch;

    public MapGUI(Map map) {
        this.map = map;
        initialise();
    }


    public void initialise() {
        iceberg = new Texture("assets/iceberg.png");
        water = new Texture("assets/water.jpg");
        snow = new Texture("assets/snow.png");

        sBatch = new SpriteBatch();
    }

    public void update() {

    }

    public void render(Graphics g) {
        sBatch.begin();
        sBatch.draw(water, 0 ,0, 640, 480);
        for (int Y = 0; Y < map.getMAP_WIDTH(); Y++) {
            for (int X = 0; X < map.getMAP_HEIGHT(); X++) {
                if (map.Icebergs[Y][X].getIsStable()) {
                    sBatch.draw(iceberg,X * TILE_SIZE + 88, Y * TILE_SIZE, 48, 48);
                }
                if (map.Icebergs[Y][X].getAmountOfSnow() > 0) {
                    sBatch.draw(snow, X * TILE_SIZE + 88, Y * TILE_SIZE, 48, 48);
                }
            }
        }
        sBatch.end();
    }

    public void dispose() {
        sBatch.dispose();
    }
}
