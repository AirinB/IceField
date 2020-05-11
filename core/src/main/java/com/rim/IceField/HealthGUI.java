package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class HealthGUI {
    PlayerBase player;

    Texture lifeTexture; //dislay the life left
    BitmapFont font2; // text near the life left

    public HealthGUI(PlayerBase p) {
        player = p;
        initialize();
    }

    public void initialize() {
        lifeTexture = new Texture("assets/5fullbattery.png");
        //font2
        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("assets/8bitFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 12;
        parameter1.borderWidth = 1;
        parameter1.color = Color.WHITE;
        parameter1.shadowOffsetX = 1;
        parameter1.shadowOffsetY = 1;
        parameter1.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        font2 = generator1.generateFont(parameter1); // font size 12 pixels
        generator1.dispose(); // don't forget to dispose to avoid memory leaks!


    }

    public void updatePlayerLife(){
        if(player.getTag().equals("PolarExplorer") && player.heatLevel == 4){
            lifeTexture = new Texture("assets/5fullbattery.png");
            return;
        }

        switch (player.heatLevel) {
            case 5:
                lifeTexture = new Texture("assets/5fullbattery.png");
                break;
            case 4:
                lifeTexture = new Texture("assets/4battery.png");
                break;
            case 3:
                lifeTexture = new Texture("assets/3battery.png");
                break;
            case 2:
                lifeTexture = new Texture("assets/2battery.png");
                break;
            case 1:
                lifeTexture = new Texture("assets/1battery.png");
                break;
            case 0:
                lifeTexture = new Texture("assets/0battery.png");
                break;

        }
    }

    public void render(SpriteBatch batch, int posX, int posY){
        updatePlayerLife();
        //life
        //font x  , y + 13
        //lifetexture 100 + x, y
        font2.draw(batch, "player " + player.getID(), posX, posY + 13);
        batch.draw(lifeTexture, posX + 110, posY,25, 15 );

    }

    public void dispose() {
        font2.dispose();
        lifeTexture.dispose();
    }

}





