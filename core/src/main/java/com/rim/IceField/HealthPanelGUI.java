package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.rim.IceField.Textures.LifeBarTexture;

import java.util.ArrayList;

public class HealthPanelGUI {
    SpriteBatch batch;
    ArrayList<PlayerHealthPanel> healthPanels;

    public HealthPanelGUI(ArrayList<PlayerBase> players, int X, int Y) {
        batch = new SpriteBatch();
        healthPanels = new ArrayList<PlayerHealthPanel>();

        int offsetY = 0;
        for (PlayerBase p: players) {
            PlayerHealthPanel h = new PlayerHealthPanel(p, X, Y - offsetY);
            healthPanels.add(h);
            offsetY += 25;
        }
    }

    public void render(){
        batch.begin();
        for (PlayerHealthPanel h: healthPanels) {
            h.render(batch);
        }
        batch.end();
    }

    public void  dispose(){
        batch.dispose();

    }

    public static class PlayerHealthPanel {
        PlayerBase player;
        private int fontSize = 12;
        private int posX = 0;
        private int posY = 0;

        LifeBarTexture lifeBarTexture; //dislay the life left
        BitmapFont text; // text near the life left






        public PlayerHealthPanel(PlayerBase p, int posX, int posY) {
            player = p;
            this.posX = posX;
            this.posY = posY;

            initialize();
        }

        public void initialize() {
            lifeBarTexture = new LifeBarTexture(Math.max(4, player.heatLevel), this.posX + 110, this.posY);
            FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("assets/8bitFont.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter options = new FreeTypeFontGenerator.FreeTypeFontParameter();
            options.size = fontSize;
            options.borderWidth = 1;
            options.color = Color.WHITE;
            options.shadowOffsetX = 1;
            options.shadowOffsetY = 1;
            options.shadowColor = new Color(0, 0.5f, 0, 0.75f);
            text = fontGenerator.generateFont(options); // font size 12 pixels
            fontGenerator.dispose(); // don't forget to dispose to avoid memory leaks!
        }


        public void render(SpriteBatch batch){
            // We need to render on Y with posY + fontSize because Render originY for text is top.
            text.draw(batch, "Player " + player.getID(), this.posX, posY + fontSize);
            lifeBarTexture.render(batch, player.heatLevel);

        }

        public void dispose() {
            text.dispose();
        }

    }
}
