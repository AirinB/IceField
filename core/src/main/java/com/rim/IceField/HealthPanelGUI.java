package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;

public class HealthPanelGUI {
    SpriteBatch batch;
    ArrayList<HealthGUI> healthDescriptors;
   // ArrayList<PlayerBase> players;
    int posX;
    int posY;

    public HealthPanelGUI(ArrayList<PlayerBase> players, int X, int Y) {
        //add the health descriptors for all the players
        batch = new SpriteBatch();
        healthDescriptors = new ArrayList<HealthGUI>();

        for (PlayerBase p: players) {
            HealthGUI h = new HealthGUI(p);
            healthDescriptors.add(h);
        }

        posX = X;
        posY = Y;
    }

    public void render(){
        batch.begin();
       // int offsetX = 0;
        //is negative, go down on the y axis
        int offsetY = 0;

        for (HealthGUI h: healthDescriptors) {
            h.updatePlayerLife();
            h.render(batch, posX, posY - offsetY);
            offsetY += 25;
        }
        batch.end();
    }

    public void  dispose(){
        batch.dispose();

    }


    public static class HealthGUI {
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
}
