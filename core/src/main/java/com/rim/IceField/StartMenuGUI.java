package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rim.IceField.Textures.ClickableImage;
import java.util.HashMap;


public class StartMenuGUI {
    private Texture background;
    private ClickableImage eskimo;
    private ClickableImage explorer;
    private ClickableImage playButton;
    private SpriteBatch sBatch;
    private Music music;

    private HashMap<String, Integer> players = new HashMap<String, Integer>();

    public StartMenuGUI(final GameMain game) {
        background = new Texture("resources/assets/menuBackground.png");
        eskimo = new ClickableImage("resources/assets/eskimo.png", 100, 100);
        explorer = new ClickableImage("resources/assets/polarExp.png", 400, 100);
        playButton = new ClickableImage("resources/assets/PlayButton.png", 420, 10);
        music = Gdx.audio.newMusic(Gdx.files.internal("resources/assets/Background.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        sBatch = new SpriteBatch();
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

    BitmapFont font = new BitmapFont();

    public void render(org.mini2Dx.core.graphics.Graphics g) {
        sBatch.begin();
        sBatch.draw(background, 0, 0, 640, 480);
        font.draw(sBatch, "ICE FIELD MENU", 200, 450);
        font.setColor(1, 0, 0, 1);
        font.getData().setScale(2);

        font.draw(sBatch, "Number of players:", 50, 380);
        font.draw(sBatch, "Choose character", 50, 300);

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
