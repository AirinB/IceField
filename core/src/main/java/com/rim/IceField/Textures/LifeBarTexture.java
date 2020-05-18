package com.rim.IceField.Textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.rim.IceField.PlayerBase;

import java.util.HashMap;

public class LifeBarTexture extends Stack {
    private int frameWidth = 20;
    private int frameHeight = 20;
    private int maxLives = 5;
    private Image currentLifeImage;
    private TextureRegion allLivesTextureRegion;
    private TextureRegion currentLivesTextureRegion;
    private TextureRegionDrawable currentLivesTextureRegionDrawable;
    private PlayerBase player;

    HashMap<String, TextureRegion> lifeBarStatus = new HashMap<String, TextureRegion>();
    Texture lifeBarSheet;
    SpriteBatch spriteBatch;

    public LifeBarTexture(int maxLives, PlayerBase p) {
        super();
        player = p;
        this.maxLives = maxLives <= 5 && maxLives > 0  ? maxLives : 5;
        lifeBarSheet = new Texture(Gdx.files.internal("resources/assets/lifeBar.png"));

        int maxWidth = this.maxLives * frameWidth;
        allLivesTextureRegion = new TextureRegion(lifeBarSheet, 0, frameHeight, maxWidth, frameHeight);
        currentLivesTextureRegion = new TextureRegion(lifeBarSheet, 0, 0, maxWidth, frameHeight);

        Image fullLifeImage = new Image(allLivesTextureRegion);
        this.addActor(fullLifeImage);

        currentLivesTextureRegionDrawable = new TextureRegionDrawable(currentLivesTextureRegion);
        currentLifeImage = new Image(currentLivesTextureRegionDrawable);
        currentLifeImage.setScaling(Scaling.stretchY);
        currentLifeImage.setSize(maxWidth, frameHeight);
        currentLifeImage.setAlign(Align.left);
        this.addActor(currentLifeImage);
    }


    public void update() {
        int lives = player.getHeatLevel();
        int currentLives = Math.min(lives, this.maxLives);
        currentLivesTextureRegion.setRegionWidth(currentLives * frameWidth);
        currentLivesTextureRegionDrawable.setRegion(currentLivesTextureRegion);
        currentLifeImage.setDrawable(currentLivesTextureRegionDrawable);
        currentLifeImage.setWidth(currentLives * frameWidth);
    }
}
