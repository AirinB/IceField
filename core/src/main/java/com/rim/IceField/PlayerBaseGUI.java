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

//TODO need to highlight the iceberg
// that is selected by the Polar Explorer

public class PlayerBaseGUI implements InputProcessor {
    PlayerBase player;
    Texture playerTexture;  //display the texture
    int sizePlayerX;
    int sizePlayerY;

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
        if (player.getTag().equals("Eskimo")) {
            playerTexture = new Texture("assets/eskimo.png");
            sizePlayerX = 30;
            sizePlayerY = 40;
        }else if(player.getTag().equals("PolarExplorer")) {
            playerTexture = new Texture("assets/polarExp.png");
            sizePlayerX = 30;
            sizePlayerY = 40;
        }

        iglooTexture = new Texture("assets/igloo.png");



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
            if(player.posX >= Gdx.graphics.getWidth() - 30) return;
            player.posX += player.offX;
        }else if(dir.equals("west")){
            if(player.posX <= 0) return;
            player.posX -= player.offX;
        }else if(dir.equals(("north"))){
            if ( player.posY >= Gdx.graphics.getHeight() - 30) return;
            player.posY += player.offY;
        }else if(dir.equals("south")){
            if(player.posY <= 0) return;
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

    public  void updatedPlayerState(){
        if(player.isDrowning && player.getTag().equals("PolarExplorer")){
            playerTexture = new Texture("assets/polarExpInWater.png");
            sizePlayerY = 20;
        }else if (player.isDrowning && player.getTag().equals("Eskimo")){
            playerTexture = new Texture("assets/eskimoInWater.png");
            sizePlayerY = 30;
        }
    }



    public void render(Graphics g) {
        batch.begin();
        // Drawing goes here!
        //player
        updatedPlayerState();
        batch.draw(playerTexture, player.posX, player.posY, sizePlayerX, sizePlayerY);

        //inv
        inventoryGUI.render(batch);

        //igloo
        if(iglooY != 0 | iglooX != 0) batch.draw(iglooTexture, iglooX, iglooY, 20, 20);

        if(showTextFlag == 1) font.draw(batch, printAmountOfPlayers, Gdx.graphics.getWidth()/2 - 100 , 460);

        batch.end();

        Gdx.input.setInputProcessor(this);

    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }


    public boolean input(PlayerBase player) throws Exception {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (player.move("north", player.game.getMap())) {
                updateMove("north");
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (player.move("south", player.game.getMap())) {
                updateMove("south");
                return true;
            }


        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (player.move("west", player.game.getMap())) {
                updateMove("west");
                return true;
            }


        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (player.move("east", player.game.getMap())) {
                updateMove("east");
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if (player.pickItem()) {
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            if (player.removeSnow()) {
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            if (player.useItem("diving suit")) {
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            if (player.useItem("food")) {
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            if  (player.useItem("rope")) {
                return true;
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
            if (player.useItem("shovel")) {
                return true;
            }

        } else if ((Gdx.input.isKeyJustPressed(Input.Keys.L)) && Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (player.SavePlayer("north", player.game.getMap())) {
                return true;
            }

        } else if ((Gdx.input.isKeyJustPressed(Input.Keys.L)) && Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (player.SavePlayer("south", player.game.getMap())) {
                return true;
            }

        } else if ((Gdx.input.isKeyJustPressed(Input.Keys.L)) && Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (player.SavePlayer("west", player.game.getMap())) {
                return true;
            }

        } else if ((Gdx.input.isKeyJustPressed(Input.Keys.L)) && Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (player.SavePlayer("east", player.game.getMap())) {
                return true;
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((player.useSkill(player.game.getMap(), "south") && player.getTag().equals("Eskimo"))) {
                    this.updateIgloo("south");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((player.useSkill(player.game.getMap(), "north") && player.getTag().equals("Eskimo"))) {
                    this.updateIgloo("north");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((player.useSkill(player.game.getMap(), "west") && player.getTag().equals("Eskimo"))) {
                    this.updateIgloo("west");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((player.useSkill(player.game.getMap(), "east") && player.getTag().equals("Eskimo"))) {
                    this.updateIgloo("east");
                    return true;
                }
            }


        } else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((player.useSkill(player.game.getMap(), "south") && player.getTag().equals("PolarExplorer"))) {
                    System.out.println("polar skill");
                    return true;
                }
            }


        } else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((player.useSkill(player.game.getMap(), "north") && player.getTag().equals("PolarExplorer"))) {
                    System.out.println("polar skill");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((player.useSkill(player.game.getMap(), "west") && player.getTag().equals("PolarExplorer"))) {
                    System.out.println("polar skill");
                    return true;
                }
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                if ((player.useSkill(player.game.getMap(), "east") && player.getTag().equals("PolarExplorer"))) {
                    System.out.println("polar skill");
                    return true;
                }
            }

        }else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (player.game.isWin()) player.game.GameOver();
            return true;

        }else if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            player.getPosition();
            return true;
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        return false;
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
