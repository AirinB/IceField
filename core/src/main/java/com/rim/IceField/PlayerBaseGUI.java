package com.rim.IceField;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

//TODO need to highlight the iceberg
// that is selected by the Polar Explorer

public class PlayerBaseGUI implements InputProcessor {
    PlayerBase player;
    Sprite sprite;
    Texture playerTexture;  //display the texture

    Texture iglooTexture; //display the igloo
    int iglooX;
    int iglooY;

    Texture lifeTexture; //dislay the life left
    BitmapFont font2; // text near the life left

    BitmapFont font; //display the polar explorer skill
    String printAmountOfPlayers;
    int showTextFlag = 0; // if the flag is 0 the text is diplayed otherwise its not

    //not used for now
    //Label label1;
    private SpriteBatch batch; // used for drawing
    private InventoryGUI inventoryGUI;


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
        lifeTexture = new Texture("assets/5fullbattery.png");
        sprite = new Sprite(playerTexture, 100, 100, 100, 100);
        sprite.setPosition(40, 40);
        batch = new SpriteBatch();
        inventoryGUI = new InventoryGUI(player);


        //font1
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

        //font2
        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("assets/8bitFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 12;
        parameter1.borderWidth = 1;
        parameter1.color = Color.WHITE;
        parameter1.shadowOffsetX = 1;
        parameter1.shadowOffsetY = 1;
        parameter1.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        font2 = generator1.generateFont(parameter1); // font size 12 pixels
        generator1.dispose(); // don't forget to dispose to avoid memory leaks!


        //label style
//        Label.LabelStyle label1Style = new Label.LabelStyle();
//        label1Style.font = font;
//
//        label1 = new Label("Title (BitmapFont)",label1Style);
//        label1.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth() / 12);
//        label1.setPosition(0,Gdx.graphics.getHeight()-(Gdx.graphics.getWidth() / 12)*2);
//        label1.setAlignment(Align.center);

    }

    public void updatePlayerLife(int lifeLeft) {
        switch (lifeLeft) {
            case 5:
                lifeTexture = new Texture("assets/5fullbattery.png");
                break;
            case 4:
                lifeTexture = new Texture("assets/4battery.png");
                break;
            case 3:
                lifeTexture = new Texture("assets/3battery.png");
                break;
            case 2:
                lifeTexture = new Texture("assets/2battery.png");
                break;
            case 1:
                lifeTexture = new Texture("assets/1battery.png");
                break;
            case 0:
                lifeTexture = new Texture("assets/0battery.png");
                break;

        }

    }

    public void updateMove(String dir) {


        if (dir.equals("east")) {
            if (player.posX >= Gdx.graphics.getWidth() - 30) return;
            player.posX += player.offX;
        } else if (dir.equals("west")) {
            if (player.posX <= 0) return;
            player.posX -= player.offX;
        } else if (dir.equals(("north"))) {
            if (player.posY >= Gdx.graphics.getHeight() - 30) return;
            player.posY += player.offY;
        } else if (dir.equals("south")) {
            if (player.posY <= 0) return;
            player.posY -= player.offY;
        }
    }

    public void updateIgloo(String dir) {
        //the offX and offY might need to be changed with the width of the tile(iceberg

        if (dir.equals("east")) {
            iglooX = player.posX + player.offX;
            iglooY = player.posY;
        } else if (dir.equals("west")) {
            iglooX = player.posX - player.offX;
            iglooY = player.posY;
        } else if (dir.equals(("north"))) {
            iglooY = player.posY + player.offY;
            iglooX = player.posX;
        } else if (dir.equals("south")) {
            iglooY = player.posY - player.offY;
            iglooX = player.posX;
        }
    }

    public void updateMaxlpayers(int amountOfPlayers) {
        printAmountOfPlayers = "The highlited iceberg\n can hold " + amountOfPlayers + " players.";
        showTextFlag = 1;
    }


    public void render(Graphics g) {
        batch.begin();
        // Drawing goes here!
        //player
        batch.draw(playerTexture, player.posX, player.posY, 50, 50);
        //life
        font2.draw(batch, "player1", 20, 458);
        batch.draw(lifeTexture, 120, 440, 30, 30);

        //inv
        inventoryGUI.render(batch);

        //igloo
        if (iglooY != 0 | iglooX != 0) batch.draw(iglooTexture, iglooX, iglooY, 65, 65);


        if (showTextFlag == 1) font.draw(batch, printAmountOfPlayers, Gdx.graphics.getWidth() / 2, 460);

        // g.drawTexture(texture, player.posX, player.posY);

        batch.end();

    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }


    //F1 - use DivingSuit
    //F2 - use Food
    //F3 - use Rope
    //F4 - use Shovel
    //P -  pick item
    //R - remove snow
    //S + up/down/left/right save player
    //A  + up/down/left/right apply skill
    //space - fire gun
    public void input(PlayerBase player) throws Exception {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (player.move("north", player.game.getMap())) updateMove("north");
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (player.move("south", player.game.getMap())) updateMove("south");
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (player.move("west", player.game.getMap())) updateMove("west");
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (player.move("east", player.game.getMap())) updateMove("east");
        } else if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            player.pickItem();
        } else if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            player.removeSnow();
        } else if (Gdx.input.isKeyPressed(Input.Keys.F1)) {
            player.useItem("diving suit");
        } else if (Gdx.input.isKeyPressed(Input.Keys.F2)) {
            player.useItem("food");
        } else if (Gdx.input.isKeyPressed(Input.Keys.F3)) {
            player.useItem("rope");
        } else if (Gdx.input.isKeyPressed(Input.Keys.F4)) {
            player.useItem("shovel");


        } else if ((Gdx.input.isKeyPressed(Input.Keys.S)) && Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.SavePlayer("north", player.game.getMap());
        } else if ((Gdx.input.isKeyPressed(Input.Keys.S)) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.SavePlayer("south", player.game.getMap());
        } else if ((Gdx.input.isKeyPressed(Input.Keys.S)) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.SavePlayer("west", player.game.getMap());
        } else if ((Gdx.input.isKeyPressed(Input.Keys.S)) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.SavePlayer("east", player.game.getMap());


        } else if ((Gdx.input.isKeyPressed(Input.Keys.A)) && Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if ((player.useSkill(player.game.getMap(), "north") && player.getTag().equals("eskimo"))){
                updateIgloo("north");
            }

        } else if ((Gdx.input.isKeyPressed(Input.Keys.A)) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
           if ((player.useSkill(player.game.getMap(), "south") && player.getTag().equals("eskimo"))){
               updateIgloo("south");
           }

        } else if ((Gdx.input.isKeyPressed(Input.Keys.A)) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if((player.useSkill(player.game.getMap(), "west") && player.getTag().equals("eskimo"))){
                updateIgloo("west");
            }

        } else if ((Gdx.input.isKeyPressed(Input.Keys.A)) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if((player.useSkill(player.game.getMap(), "east") && player.getTag().equals("eskimo"))){
                updateIgloo("east");
            }

        }else if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (player.game.isWin()) player.game.GameOver();

        }else if(Gdx.input.isKeyPressed(Input.Keys.C)) {
            player.getPosition();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                try {
                    if (player.move("north", player.game.getMap())) updateMove("north");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Input.Keys.S:
                try {
                    if (player.move("south", player.game.getMap())) updateMove("south");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Input.Keys.A:
                try {
                    if (player.move("west", player.game.getMap())) updateMove("west");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Input.Keys.D:
                try {
                    if (player.move("east", player.game.getMap())) updateMove("east");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Input.Keys.P:
                try {
                    player.pickItem();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }



}
