package com.rim.IceField.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rim.IceField.InventoryGUI;
import com.rim.IceField.PlayerBase;

import java.util.HashMap;

public class PlayerAnimation {
    private static final int FRAME_COLS = 4, FRAME_ROWS = 4;

    Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    HashMap<String, Animation<TextureRegion>> walkAnimations = new HashMap<String, Animation<TextureRegion>>();
    Texture walkSheet;
    SpriteBatch spriteBatch;
    int moveSpeed;
    float stateTime;
    PlayerBase player;

    public PlayerAnimation(int moveSpeed, PlayerBase player) {
        this.moveSpeed = moveSpeed;
        this.player = player;
        this.initializeAnimation();
    }

    private void initializeAnimation() {
        if (player.getTag().equals("Eskimo")) {
            // Load the sprite sheet as a Texture
            walkSheet = new Texture(Gdx.files.internal("resources/assets/explorer_1x.png"));

        }else if(player.getTag().equals("PolarExplorer")) {
            // Load the sprite sheet as a Texture

            walkSheet = new Texture(Gdx.files.internal("resources/assets/eskimo_1xd.png"));

        }


        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        String[] directions = { "south", "west", "east", "north" };
        for (int i = 0; i < FRAME_ROWS; i++) {
            walkAnimations.put(directions[i], new Animation<TextureRegion>(0.2f/moveSpeed, tmp[i]));
        }

        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    };

    public void render() {
        Boolean isMoving = player.getMovingState();
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame;
        if (isMoving) {
            currentFrame = walkAnimations.get(player.getDirection()).getKeyFrame(stateTime, true);
        } else {
            int standingStateFrame = 2;
            currentFrame = walkAnimations.get(player.getDirection()).getKeyFrame(standingStateFrame, true);
        }

        spriteBatch.begin();
        spriteBatch.draw(currentFrame, player.getPosX(), player.getPosY()); // Draw current frame at (50, 50)
        spriteBatch.end();
    }
}
