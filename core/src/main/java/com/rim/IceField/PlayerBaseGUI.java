package com.rim.IceField;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.rim.IceField.Animations.PlayerAnimation;
import org.mini2Dx.core.graphics.Graphics;


public class PlayerBaseGUI {
    private GameConfig gameConfig = new GameConfig();
    PlayerBase player;
    Texture playerTexture;  //display the texture
    Texture pDSuitInWater;
    int sizePlayerX;
    int sizePlayerY;

    Texture iglooTexture; //display the igloo
    int iglooX;
    int iglooY;
    private int moveSpeed = gameConfig.playerMoveSpeed;
    private int tileSize = gameConfig.mapMoveDistance;
    private int moveTarget = 0;


    BitmapFont font; //display the polar explorer skill
    String printAmountOfPlayers;
    int showTextFlag = 0; // if the flag is 0 the text is diplayed otherwise its not

    //not used for now
    //Label label1;
    private SpriteBatch batch; // used for drawing
    private InventoryGUI inventoryGUI;
    private PlayerAnimation playerAnimation;


    // A variable for tracking elapsed time for the animation
    float stateTime;


    public PlayerBaseGUI(PlayerBase p) {
        player = p;
        initialize();
    }


    public void initialize() {
        pDSuitInWater = new Texture("resources/assets/dsuit_in_Water.png");
        playerAnimation = new PlayerAnimation(moveSpeed, player);
        if (player.getTag().equals("Eskimo")) {
            playerTexture = new Texture("resources/assets/eskimo_in_water.png");
        }else if(player.getTag().equals("PolarExplorer")) {
            playerTexture = new Texture("resources/assets/explorer_in_water.png");
        }

        iglooTexture = new Texture("resources/assets/igloo.png");
        batch = new SpriteBatch();
        inventoryGUI = new InventoryGUI(player);

        //font1
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("resources/fonts/8bitFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        parameter.borderWidth = 1;
        parameter.color = Color.SKY;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

    }

    public void updateMove(String dir) {
        player.setMoving(true);
        player.direction = dir;
        moveTarget = tileSize;
    }

    private void updatePlayerPositionIfNeeded() {
        boolean isMoving = player.getMovingState();
        if (moveTarget <= 0 || !isMoving) {
            moveTarget = 0;
            player.setMoving(false);
            return;
        }

        String dir = player.getDirection();
        int currentSpeed = Math.min(moveTarget, moveSpeed);

        if(dir.equals("east")){
            if(player.posX >= Gdx.graphics.getWidth() - 30) return;
            player.posX += currentSpeed;
        }else if(dir.equals("west")){
            if(player.posX <= 0) return;
            player.posX -= currentSpeed;
        }else if(dir.equals(("north"))){
            if ( player.posY >= Gdx.graphics.getHeight() - 30) return;
            player.posY += currentSpeed;
        }else if(dir.equals("south")){
            if(player.posY <= 0) return;
            player.posY -= currentSpeed;
        }
        moveTarget -= currentSpeed;
    }

    public void updateIgloo(String dir){
        //the offX and offY might need to be changed with the width of the tile(iceberg

        if(dir.equals("east")){
            iglooX = player.posX + tileSize;
            iglooY = player.posY;
            System.out.println("Igloo y:" + iglooY  + "x:" + iglooY);
        }else if(dir.equals("west")){
            iglooX = player.posX -  tileSize;
            iglooY = player.posY;
            System.out.println("Igloo y:" + iglooY  + "x:" + iglooY);
        }else if(dir.equals(("north"))){
            iglooY = player.posY + tileSize;
            iglooX = player.posX;
            System.out.println("Igloo y:" + iglooY  + "x:" + iglooY);
        }else if(dir.equals("south")){
            iglooY = player.posY - tileSize;
            iglooX = player.posX;
            System.out.println("Igloo y:" + iglooY  + "x:" + iglooY);
        }
    }

    public void updateMaxlpayers(int amountOfPlayers){
        printAmountOfPlayers = "The highlited iceberg\n can hold " + amountOfPlayers + " players.";
        showTextFlag = 1;
    }


    public void render(Graphics g) {
        updatePlayerPositionIfNeeded();
        //inv
        inventoryGUI.render();

        batch.begin();
        // Drawing goes here!


        //igloo
        if(iglooY != 0 | iglooX != 0) batch.draw(iglooTexture, iglooX, iglooY, 30, 30);

        if(showTextFlag == 1) font.draw(batch, printAmountOfPlayers, Gdx.graphics.getWidth()/2 - 100 , 460);
        if(player.isDrowning && !player.getMovingState()) {
            if(player.isWearingDSuit){
                batch.draw(pDSuitInWater, player.posX, player.posY);
            }else{
                batch.draw(playerTexture, player.posX, player.posY);
            }

            batch.end();
        } else {
            batch.end();
            playerAnimation.render();
        }
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
