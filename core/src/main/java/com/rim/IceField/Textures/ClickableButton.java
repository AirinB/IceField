package com.rim.IceField.Textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rim.IceField.GameStage;

public class ClickableButton {
    private Texture texture;
    private ImageButton button;
    private TextButton textButton;
    private InputListener inputListener;

    public ClickableButton(String texturePath, float posX, float posY) {
        texture = new Texture(texturePath);
        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(textureRegion);
        button = new ImageButton(myTexRegionDrawable); //Set the button up
        button.setPosition(posX, posY);

        GameStage.stage.addActor(button); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(GameStage.stage); //Start taking input from the ui
    }

    public ClickableButton(TextButton button, float posX, float posY) {
        textButton = button;
        button.setPosition(posX, posY);

        GameStage.stage.addActor(textButton); //Add the button to the stage to perform rendering and take input.
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
        if (button != null) {
            button.addListener(inputListener);
        }
        if (textButton != null) {
            textButton.addListener(inputListener);
        }
    }

    public void setPosition(int x, int y) {
        button.setPosition(x, y);
    }

    public void dispose() {
        if (button != null) {
            button.remove();
            button.removeListener(inputListener);
            texture.dispose();
        }

        if (textButton != null) {
            textButton.remove();
            textButton.removeListener(inputListener);
        }
    }
}
