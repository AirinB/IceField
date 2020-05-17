package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Sprite;
//import java.awt.TextField;
import java.util.ArrayList;
import com.badlogic.gdx.Screen;

import  com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;



public class StartMenuGUI extends BasicGame {
    public static final String GAME_IDENTIFIER = "com.rim.IceField";
    GameMain gmain = new GameMain();

    // IcefieldGame game;

    private Texture background;
    private Texture eskimo;
    private Texture explorer;
    private Texture playButton;
    private SpriteBatch sBatch;

    private static final int ESKIMO_WIDTH = 150;
    private static final int ESKIMO_HEIGHT = 150;
    private static final int EXPLORER_WIDTH = 150;
    private static final int EXPLORER_HEIGHT = 150;
    private static final int EXPLORER_Y = 100;
    private static final int EXPLORER_X = 100;
    private static final int ESKIMO_Y = 100;
    private static final int ESKIMO_X = 100;

    private int eskimos = 2;
    private int explorers = 2;

    //Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
//    TextField text = new TextField("", skin);
//   VisualSettingManager visualSettingManager = new
//   final SelectBox<String> selectBox = new SelectBox<String>(visualSettingManager.getSkin());

//
    public StartMenuGUI() {
        // this.game = game;

        background = new Texture("resources/assets/menuBackground.png");
        eskimo = new Texture("resources/assets/eskimo.png");
        explorer = new Texture("resources/assets/polarExp.png");
        playButton = new Texture("resources/assets/PlayButton.png");
        sBatch = new SpriteBatch();
    }

    @Override
    public void initialise() {
//        background = new Texture("resources/assets/menuBackground.png");
//        eskimo = new Texture("resources/assets/eskimo.png");
//        explorer = new Texture("resources/assets/polarExp.png");
//        playButton = new Texture("resources/assets/PlayButton.png");
//        sBatch = new SpriteBatch();

    }

    @Override
    public void update(float v) {

    }

    @Override
    public void interpolate(float v) {

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

        sBatch.draw(playButton, 420, 10, 120, 80);
        sBatch.draw(eskimo, 100, 100, 150, 150);
        sBatch.draw(explorer, 400, 100, 150, 150);

        //Eskimo clicked
        if (Gdx.input.getX() < ESKIMO_X + ESKIMO_WIDTH && Gdx.input.getX() > ESKIMO_X && 480 - Gdx.input.getY() < ESKIMO_Y + ESKIMO_HEIGHT && 480 - Gdx.input.getY() > ESKIMO_Y) {
            if (Gdx.input.isTouched()) {
                AddEskimo();
            }
        }
        //Explorer clicked
        if (Gdx.input.getX() < EXPLORER_X + EXPLORER_WIDTH && Gdx.input.getX() > EXPLORER_X && 480 - Gdx.input.getY() < EXPLORER_Y + EXPLORER_HEIGHT && 480 - Gdx.input.getY() > EXPLORER_Y) {
            if (Gdx.input.isTouched()) {
                AddExplorer();
            }
        }
        //PlayButton clicked
        else if (Gdx.input.getX() < 420 + 640 && Gdx.input.getX() > 420 && 480 - Gdx.input.getY() < 10 + 80 && 480 - Gdx.input.getY() > 10) {
            if (Gdx.input.isTouched()) {

                GameState.setGameState(true);
              //  gmain.initialise();
            }
        }

//        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
//        final SelectBox<String> selectBox = new SelectBox<String>(skin);
//        selectBox.setItems("XYZ", "ABC", "PQR", "LMN");
//
//
//        text.setX(10);
//        text.setY(220);
//        text.setWidth(200);
//        text.setHeight(30);


        sBatch.end();
    }

    public void AddEskimo() {
        eskimos += 1;
    }

    public void AddExplorer() {
        explorers += 1;
    }

    public int getEskimos() {
        return eskimos;
    }

    public int getExplorers() {
        return explorers;
    }
}


//    @Override
//    public void show() {
//
//    }
//
//    @Override
//    public void render(float v) {
//        Gdx.gl.glClearColor(1,0,0,1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        game.batch.begin();
//
//        sBatch.begin();
//        sBatch.draw(background,0,0,640,480);
//        font.draw(sBatch, "ICE FIELD MENU", 200, 450);
//        font.setColor(1,0,0,1);
//        font.getData().setScale(2);
//
//        font.draw(sBatch, "Number of players:",50, 380);
//        font.draw(sBatch, "Choose character", 50, 300);
//
//        sBatch.draw(playButton, 420, 10 , 120,80);
//        sBatch.draw(eskimo, 100,100,150,150 );
//        sBatch.draw(explorer,400,100,150,150);
//
//        //Eskimo clicked
//        if(Gdx.input.getX() < ESKIMO_X + ESKIMO_WIDTH && Gdx.input.getX() > ESKIMO_X && 480 - Gdx.input.getY() < ESKIMO_Y + ESKIMO_HEIGHT && 480 - Gdx.input.getY() > ESKIMO_Y)
//        {
//            if(Gdx.input.isTouched())
//            {
//                AddEskimo();
//            }
//        }
//        //Explorer clicked
//        if(Gdx.input.getX() < EXPLORER_X + EXPLORER_WIDTH && Gdx.input.getX() > EXPLORER_X && 480 - Gdx.input.getY() < EXPLORER_Y + EXPLORER_HEIGHT && 480 - Gdx.input.getY() > EXPLORER_Y)
//        {
//            if(Gdx.input.isTouched())
//            {
//                AddExplorer();
//            }
//        }
//        //PlayButton clicked
//        else if(Gdx.input.getX() < 420 + 640 && Gdx.input.getX() > 420 && 480 - Gdx.input.getY() < 10 + 80 && 480 - Gdx.input.getY() > 10)
//        {
//            if(Gdx.input.isTouched())
//            {
//
//                GameState.setGameState(true);
//              //  game.initialise();
//            }
//        }
//        game.batch.end();
//    }
//
//    @Override
//    public void resize(int i, int i1) {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    public void dispose() {
//        sBatch.dispose();
//    }
//}
