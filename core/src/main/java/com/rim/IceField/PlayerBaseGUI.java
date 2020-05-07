package com.rim.IceField;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

//TODO need to highlight the iceberg
// that is selected by the Polar Explorer

public class PlayerBaseGUI {
    PlayerBase player;
    Sprite sprite;
    Texture playerTexture;
    Texture iglooTexture;
    BitmapFont font;
    //not used for now
    //Label label1;
    private SpriteBatch batch;
    int iglooX;
    int iglooY;
    String printAmountOfPlayers;
    int showTextFlag = 0;


    public PlayerBaseGUI(PlayerBase p) {
        player = p;
        initialize();
    }

    public void initialize() {
        if (player.getTag().equals("Eskimo"))
            playerTexture = new Texture("assets/eskimo.png");
        if (player.getTag().equals("PolarExplorer"))
            playerTexture = new Texture("assets/polarExp.png");

        iglooTexture = new Texture("assets/igloo.png");
        sprite = new Sprite(playerTexture, 100, 100, 100, 100);
        sprite.setPosition(40, 40);
        batch = new SpriteBatch();


        //font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/8bitFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        parameter.borderWidth = 1;
        parameter.color = Color.SKY;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        //label style
//        Label.LabelStyle label1Style = new Label.LabelStyle();
//        label1Style.font = font;
//
//        label1 = new Label("Title (BitmapFont)",label1Style);
//        label1.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth() / 12);
//        label1.setPosition(0,Gdx.graphics.getHeight()-(Gdx.graphics.getWidth() / 12)*2);
//        label1.setAlignment(Align.center);




    }

    public void updateMove(String dir) {
        if(dir.equals("east")){
            player.posX += player.offX;
        }else if(dir.equals("west")){
            player.posX -= player.offX;
        }else if(dir.equals(("north"))){
            player.posY += player.offY;
        }else if(dir.equals("south")){
            player.posY -= player.offY;
        }
    }

    public void updateIgloo(String dir){
        //the offX and offY might need to be changed with the width of the tile(iceberg

        if(dir.equals("east")){
            iglooX = player.posX+ player.offX;
            iglooY = player.posY;
        }else if(dir.equals("west")){
            iglooX = player.posX -  player.offX;
            iglooY = player.posY;
        }else if(dir.equals(("north"))){
            iglooY = player.posY + player.offY;
            iglooX = player.posX;
        }else if(dir.equals("south")){
            iglooY = player.posY - player.offY;
            iglooX = player.posX;
        }
    }

    public void updateMaxlpayers(int amountOfPlayers){
        printAmountOfPlayers = "The highlited iceberg\n can hold " + amountOfPlayers + " players.";
        showTextFlag = 1;
    }

    public void render(Graphics g) {
        batch.begin();
        // Drawing goes here!
        batch.draw(playerTexture, player.posX, player.posY, 40, 40);
        if(iglooY != 0 | iglooX != 0) batch.draw(iglooTexture, iglooX, iglooY, 65, 65);


        if(showTextFlag == 1) font.draw(batch, printAmountOfPlayers, Gdx.graphics.getWidth()/2 - 70, 460);

       // g.drawTexture(texture, player.posX, player.posY);

        batch.end();

    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
