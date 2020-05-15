package com.rim.IceField.Textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class LifeBarTexture {
    private int frameWidth = 20;
    private int frameHeight = 20;
    private int maxLives = 5;
    private int posX = 50;
    private int posY = 50;

    HashMap<String, TextureRegion> lifeBarStatus = new HashMap<String, TextureRegion>();
    Texture lifeBarSheet;
    SpriteBatch spriteBatch;

    public LifeBarTexture(int maxLives) {
        this.maxLives = maxLives <= 5 && maxLives > 0  ? maxLives : 5;
        this.initializeAnimation();
    }

    public LifeBarTexture(int maxLives, int posX, int posY) {
        this.maxLives = maxLives <= 5 && maxLives > 0  ? maxLives : 5;
        this.posX = posX;
        this.posY = posY;
        this.initializeAnimation();
    }

    private void initializeAnimation() {
        // Load the sprite sheet as a Texture
        lifeBarSheet = new Texture(Gdx.files.internal("resources/assets/lifeBar.png"));

        int maxWidth = this.maxLives * frameWidth;

        lifeBarStatus.put("gray", new TextureRegion(lifeBarSheet, 0, frameHeight, maxWidth, frameHeight));
        lifeBarStatus.put("red", new TextureRegion(lifeBarSheet, 0, 0, frameWidth, frameHeight));

        spriteBatch = new SpriteBatch();
    };

    public void render(SpriteBatch batch, int lives) {
        int currentLives = Math.min(lives, this.maxLives);
        lifeBarStatus.get("red").setRegionWidth(currentLives * frameWidth);
        batch.draw(lifeBarStatus.get("gray"), posX, posY);
        batch.draw(lifeBarStatus.get("red"), posX, posY);
    }
}
