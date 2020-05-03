package com.rim.IceField;


import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;

public class PlayerBaseGUI {
    PlayerBase player;
    Texture sprite;

    public PlayerBaseGUI(PlayerBase p) {
        player = p;
    }

    public void initialize() {
        if (player.getTag().equals("Eskimo"))
            sprite = new Texture("assets/eskimo.png");
        if (player.getTag().equals("Eskimo"))
            sprite = new Texture("assets/polarExp.png");
    }

    public void update() {
        //Where keyboard input is
    }

    public void render(Graphics g) {
        g.drawTexture(sprite, 0f, 0f);
    }
}
