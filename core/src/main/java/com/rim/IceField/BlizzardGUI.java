package com.rim.IceField;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.Random;

public class BlizzardGUI {
    private SpriteBatch batch;
    private Sprite sprite;
    Texture texture1;
    Texture texture2;
    Texture texture3;
    Texture texture4;

    Texture windRight1;
    Texture windRight2;
    Texture windRight3;
    Texture windRight4;

    int positionX1;
    int positionX2;
    int positionX3;
    int positionX4;

    int posX1;
    int posX2;
    int posX3;
    int posX4;
    int positionY;

    int screenWidth;

    public BlizzardGUI() {
        screenWidth = 640;
        positionX1 = -100;
        positionX2 = -100;
        positionX3 = -100;
        positionX4 = -100;

        posX1 = screenWidth;
        posX2 = screenWidth;
        posX3 = screenWidth;
        posX4 = screenWidth;

        positionY = 200;
        initialize();
    }

    public void initialize() {
        texture1 = new Texture("resources/assets/wind.png");

        windRight1 = new Texture("resources/assets/windRight.png");

        sprite = new Sprite(texture1, 100, 100, 100, 100);
        sprite.setPosition(40, 40);
        batch = new SpriteBatch();
    }


    public void update() {
        if(positionX1 <= screenWidth) positionX1 += 1;
        if(positionX2 <= screenWidth) positionX2 += 2;
        if(positionX3 <= screenWidth) positionX3 += 3.5;
        if(positionX4 <= screenWidth) positionX4 += 2.5;

        if(posX1 >= -110) posX1 -= 1;
        if(posX2 >= -110) posX2 -= 1.4;
        if(posX3 >= -110) posX3 -= 2.333;
        if(posX4 >= -110) posX4 -= 3.2345;

        if( positionX1 >= screenWidth && positionX2 >= screenWidth
                &&positionX3 >= screenWidth &&positionX4 >= screenWidth
                && posX1 <= 110 && posX2 <= 110 && posX3 <= 110 && posX4 <= 110){
            resetBlow();
        }
    }

    //reset the coordinates to the initial ones
    public void resetBlow(){
        positionX1 = -100;
        positionX2 = -100;
        positionX3 = -100;
        positionX4 = -100;

        posX1 = screenWidth;
        posX2 = screenWidth;
        posX3 = screenWidth;
        posX4 = screenWidth;

    }

    public int getRandInt(int min, int max) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt((max - min) + 1) + min;
    }

    public void render(Graphics g) {
        int positionY;
        int size;

        batch.begin();
        // Drawing goes here!

        batch.draw(texture1, positionX1, 200, 80, 60);
        batch.draw(texture1, positionX2, 240, 71, 71);
        batch.draw(texture1, positionX3, 50, 90, 90);
        batch.draw(texture1, positionX4, 110, 90, 70);

        batch.draw(windRight1, posX1, 110, 90, 60);
        batch.draw(windRight1, posX2, 50, 90, 70);
        batch.draw(windRight1, posX3, 222, 78, 78);
        batch.draw(windRight1, posX4, 350, 67, 67);
        // g.drawTexture(texture, player.posX, player.posY);


        batch.end();
    }

}
