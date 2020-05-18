package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class EndGame {
    BitmapFont font;
    SpriteBatch batch;


    public EndGame( ) {
        batch = new SpriteBatch();
        initialize();

    }

    public void initialize(){
        //font2
        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("resources/fonts/8bitFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 72;
        parameter1.borderWidth = 1;
        parameter1.color = new Color(	0x7bdff2);
        parameter1.shadowOffsetX = 1;
        parameter1.shadowOffsetY = 1;
        parameter1.shadowColor = new Color(0xb2f7ef);
        font = generator1.generateFont(parameter1); // font size 12 pixels
        generator1.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    public void render(boolean outcome){
        batch.begin();
        if(outcome) {
            font.draw(batch, "WIN!", Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 + font.getLineHeight());
        }else {
            font.draw(batch, "You Lost!", Gdx.graphics.getWidth() / - 300 , Gdx.graphics.getHeight() / 2 + font.getLineHeight());
        }
        batch.end();
    }

}
