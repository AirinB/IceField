package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rim.IceField.Textures.ClickableImage;

public class InventoryGUI {
    Stage stage;
    PlayerBase player;
    Inventory inventory;

    //The inventory
    Texture rope;
    Texture shovel;
    Texture divingSuit;
    Texture charge;
    Texture flare;
    Texture gun;
    SpriteBatch batch;
    ClickableImage foodButton;
    ClickableImage ropeButton;

    BitmapFont font; // text near the life left

    public InventoryGUI(PlayerBase p) {
        player = p;
        inventory = p.inventory;
        initialize();

    }

    public void  initialize(){
        stage = new Stage(new ScreenViewport());
        //the inventory
        charge = new Texture("resources/assets/securityNotCollected.png");
        flare =  new Texture("resources/assets/flareNotCollected.png");
        divingSuit =  new Texture("resources/assets/diving-suit.png");
        gun = new Texture("resources/assets/gunNotCollected.png");
        rope =  new Texture("resources/assets/rope.png");
        shovel =  new Texture("resources/assets/shovel.png");

        foodButton = new ClickableImage("resources/assets/food_1x.png", 570, 450, stage);
        foodButton.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hope it works!");
            }
        });

        ropeButton =  new ClickableImage("resources/assets/rope_1x.png", 570, 430, stage);
        ropeButton.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("Rope clicked!");
            }
        });

        //font2
        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("resources/fonts/8bitFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 12;
        parameter1.borderWidth = 1;
        parameter1.color = Color.WHITE;
        parameter1.shadowOffsetX = 1;
        parameter1.shadowOffsetY = 1;
        parameter1.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        font = generator1.generateFont(parameter1); // font size 12 pixels
        generator1.dispose(); // don't forget to dispose to avoid memory leaks!

        batch = new SpriteBatch();
    }

    public void updateGoal(){
        if(Inventory.isGunCollected) gun = new Texture("resources/assets/gun.png");

        if(Inventory.isChargeCollected) charge = new Texture("resources/assets/security.png");
        if(Inventory.isFlareCollected) flare = new Texture("resources/assets/flare.png");
    }

    public void render(){
        if(!player.isTurn) return;
        //render the useful items
        // 20  px difference on y
//        batch.draw(food,570, 450, 15, 15 );
//        foodButton.render();
//        ropeButton.render();
        stage.draw();
        batch.begin();
        font.draw(batch, " x" + inventory.countItem("food"), 580, 460);

//        batch.draw(rope,570, 430, 15, 15 );
        font.draw(batch, " x" + inventory.countItem("rope"), 580, 440);

        batch.draw(shovel,570, 410, 15, 15 );
        font.draw(batch, " x" + inventory.countItem("shovel"), 580, 420);

        batch.draw(divingSuit,570, 390, 15, 20);
        font.draw(batch, " x" + inventory.countItem("diving suit"), 580, 400);

      //  batch.draw(charge,570, 410, 15, 15 );
      //  font.draw(batch, " x0", 580, 420);


        //this part should be taken from here to the map
        //or to the player, can be left here anyway
        font.draw(batch, "GOAL", 530, 50);
        updateGoal();
        //display the collected parts for the gun
        batch.draw(gun,590, 10, 25, 25 );
        batch.draw(flare,550, 10, 25, 25 );
        batch.draw(charge,510, 14, 20, 10 );
        batch.end();
    }

    public void dispose() {

        font.dispose();
    }

}
