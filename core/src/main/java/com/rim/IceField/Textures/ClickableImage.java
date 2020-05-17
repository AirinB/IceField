package com.rim.IceField.Textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rim.IceField.GameStage;

public class ClickableImage {
    private Texture texture;
    private ImageButton button;
    private InputListener inputListener;

    public ClickableImage(String texturePath, int posX, int posY) {
        texture = new Texture(texturePath);
        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(textureRegion);
        button = new ImageButton(myTexRegionDrawable); //Set the button up
        button.setPosition(posX, posY);

        GameStage.stage.addActor(button); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(GameStage.stage); //Start taking input from the ui
    }

    public void addListener(final Runnable callback) {
        inputListener = new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                callback.run();
            }
        };
        button.addListener(inputListener);
    }

    public void setPosition(int x, int y) {
        button.setPosition(x, y);
    }

    public void dispose() {
        button.remove();
        button.removeListener(inputListener);
        texture.dispose();
    }
}
