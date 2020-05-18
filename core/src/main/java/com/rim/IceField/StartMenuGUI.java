package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.rim.IceField.Textures.ClickableButton;
import java.util.HashMap;


public class StartMenuGUI {
    private Texture background;
    private ClickableButton eskimo;
    private ClickableButton explorer;
    private ClickableButton playButton;
    private SpriteBatch sBatch;
    private Music music;
    BitmapFont font;

    private HashMap<String, Integer> players = new HashMap<String, Integer>();
    private Skin skin;

    private static final String reallyLongString = "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n"
            + "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n"
            + "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n";



    public StartMenuGUI(final GameMain game) {
        background = new Texture("resources/assets/menu_back.png");
        eskimo = new ClickableButton("resources/assets/eskimo.png", 100, 100);
        explorer = new ClickableButton("resources/assets/polarExp.png", 400, 100);

        background = new Texture("resources/assets/menuBackground.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("resources/assets/Background.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
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

        skin = new Skin(Gdx.files.internal("resources/skins/ice/freezing-ui.json"));
        final TextButton button = new TextButton("Start", skin, "default");
        button.setWidth(200f);
        button.setHeight(40f);
        playButton = new ClickableButton(button, 420, 20);

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
