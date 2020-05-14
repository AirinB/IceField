package com.rim.IceField.Textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ClickableImage {
    private Texture texture;
    private TextureRegion textureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;
    private Stage stage;
    private InputListener inputListener;

    public ClickableImage(String texturePath, int posX, int posY, Stage stage) {
        this.stage = stage;
        texture = new Texture(texturePath);
        textureRegion = new TextureRegion(texture);
        myTexRegionDrawable = new TextureRegionDrawable(textureRegion);
        button = new ImageButton(myTexRegionDrawable); //Set the button up
        button.setPosition(posX, posY);

        stage.addActor(button); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui
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
        button.removeListener(inputListener);
        texture.dispose();
    }
}
