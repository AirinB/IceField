package com.rim.IceField;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;


public class PlayerBaseGUI {
    PlayerBase player;
    Sprite sprite;
    Texture texture;
    private SpriteBatch batch;
    int positionX;
    int positionY;

    public PlayerBaseGUI(PlayerBase p) {
        player = p;
        initialize();
    }

    public void initialize() {
        if (player.getTag().equals("Eskimo"))
            texture = new Texture("assets/eskimo.png");
        if (player.getTag().equals("PolarExplorer"))
            texture = new Texture("assets/polarExp.png");
        sprite = new Sprite(texture, 100, 100, 100, 100);
        sprite.setPosition(40, 40);
        batch = new SpriteBatch();




    }

    public void updateMove(String dir) {
        if(dir.equals("east")){
            player.posX += player.offX;
        }else if(dir.equals("west")){
            player.posX -= player.offX;
        }else if(dir.equals(("north"))){
            player.posY += player.offY;
        }else if(dir.equals("south")){
            player.posY -= player.offY;
        }
    }

    public void render(Graphics g) {
        batch.begin();
        // Drawing goes here!
        batch.draw(texture, player.posX, player.posY, 40, 40);
       // g.drawTexture(texture, player.posX, player.posY);

        batch.end();

    }

}
