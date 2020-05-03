package com.rim.IceField;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;

public class ItemBaseGUI {
    ItemBase item;
    Texture texture;

    public ItemBaseGUI(ItemBase Item) {
        this.item = Item;
    }

    public void initialize() {
        if (item.getTag().equals("Charge"))
            texture = new Texture("assets/security.png");
        if (item.getTag().equals("Flare"))
            texture = new Texture("assets/flare.png");
        if (item.getTag().equals("Food"))
            texture = new Texture("assets/food.png");
        if (item.getTag().equals("DivingSuit"))
            texture = new Texture("assets/diving-suit.png");
        if (item.getTag().equals("Gun"))
            texture = new Texture("assets/gun.png");
        if (item.getTag().equals("Rope"))
            texture = new Texture("assets/rope.png");
        if (item.getTag().equals("Shovel"))
            texture = new Texture("assets/shovel.png");
    }

    public void update() {
        //Where updates happen related to taking the item
    }

    public void render(Graphics g) {
        g.drawTexture(texture, 0f, 0f);
    }
}
