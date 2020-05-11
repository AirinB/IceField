package com.rim.IceField;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.mini2Dx.core.graphics.Graphics;

public class ItemBaseGUI {
    ItemBase item;
    Texture itemTexture;
    private SpriteBatch batch;
    int positionX;
    int positionY;
    int width;
    int height;

    public ItemBaseGUI(ItemBase Item) {
        this.item = Item;
        initialize();
    }

    public void initialize() {
        batch = new SpriteBatch();
        positionX = 0;
        positionY = 0;
        if (item.getTag().equals("charge")) {
            itemTexture = new Texture("assets/security.png");
            width = 20;
            height = 10;
        }else if (item.getTag().equals("flare")) {
            itemTexture = new Texture("assets/flare.png");
            width = 25;
            height = 25;
        }else if (item.getTag().equals("food")) {
            itemTexture = new Texture("assets/food.png");
            width = 25;
            height = 25;
        }else if (item.getTag().equals("diving suit")) {
            itemTexture = new Texture("assets/diving-suit.png");
            width =20;
            height = 30;
        }else if (item.getTag().equals("gun")) {
            itemTexture = new Texture("assets/gun.png");
            width = 30;
            height = 25;
        }else if (item.getTag().equals("rope")) {
            itemTexture = new Texture("assets/rope.png");
            width = 25;
            height = 25;
        }else if (item.getTag().equals("shovel")) {
            itemTexture = new Texture("assets/shovel.png");
            width = 30;
            height = 30;
        }
    }

    public void update(int x, int y) {
        //Where updates happen related to taking the item
        positionX = x;
        positionY = y;
    }

    public void render(Graphics g) {
        batch.begin();
        //if the position is 0, 0 then don't render
        if(positionY != 0 | positionX != 0) batch.draw(itemTexture, positionX, positionY, width, height);

        batch.end();
    }
}
