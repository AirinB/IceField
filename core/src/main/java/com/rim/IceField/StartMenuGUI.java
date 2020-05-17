package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.rim.IceField.Textures.ClickableImage;

import java.util.HashMap;


public class StartMenuGUI {
    private Texture background;
    private ClickableImage eskimo;
    private ClickableImage explorer;
    private ClickableImage playButton;
    private SpriteBatch sBatch;
    BitmapFont font;

    private HashMap<String, Integer> players = new HashMap<String, Integer>();

    public StartMenuGUI(final GameMain game) {
        background = new Texture("resources/assets/menu_back.png");
        eskimo = new ClickableImage("resources/assets/eskimo.png", 100, 100);
        explorer = new ClickableImage("resources/assets/polarExp.png", 400, 100);
        playButton = new ClickableImage("resources/assets/PlayButton.png", 420, 10);
        sBatch = new SpriteBatch();

        //font2
        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("resources/fonts/8bitFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 20;
        parameter1.borderWidth = 1;
        parameter1.color = new Color(	0x7bdff2);
        parameter1.shadowOffsetX = 1;
        parameter1.shadowOffsetY = 1;
        parameter1.shadowColor = new Color(0xb2f7ef);
        font = generator1.generateFont(parameter1); // font size 12 pixels
        generator1.dispose(); // don't forget to dispose to avoid memory leaks!

        players.put("eskimo", 0);
        players.put("explorer", 0);

        eskimo.addListener(new Runnable() {
            @Override
            public void run() {
                AddEskimo();
            }
        });

        explorer.addListener(new Runnable() {
            @Override
            public void run() {
                AddExplorer();
            }
        });

        playButton.addListener(new Runnable() {
            @Override
            public void run() {
                GameState.setGameState(true);
                game.initialiseGame(players);
                eskimo.dispose();
                explorer.dispose();
                playButton.dispose();
            }
        });
    }



    public void render(org.mini2Dx.core.graphics.Graphics g) {
        sBatch.begin();
        sBatch.draw(background, 0, 0, 640, 480);
        font.draw(sBatch, "ICE FIELD", Gdx.graphics.getWidth()/2 - 50, 450);



       // font.draw(sBatch, "Number of players:", 50, 380);
        font.draw(sBatch, "Choose characters", 50, 390);

        sBatch.end();
    }

    public void AddEskimo() {
        int tmpValue = players.get("eskimo");
        players.put("eskimo", tmpValue + 1);
    }

    public void AddExplorer() {
        int tmpValue = players.get("explorer");
        players.put("explorer", tmpValue + 1);
    }
}
