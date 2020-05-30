package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.rim.IceField.Textures.ClickableButton;

public class InventoryGUI {
    PlayerBase player;
    Inventory inventory;

    //The inventory
    Texture rope;
    Texture shovel;
    Texture divingSuit;
    Texture charge;
    Texture flare;
    Texture gun;
    Texture collectionBg;
    SpriteBatch batch;
    ClickableButton foodButton;
    ClickableButton ropeButton;
    Texture colletedGun;
    Texture colletedFlare;
    Texture colletedCharge;

    BitmapFont font; // text near the life left

    public InventoryGUI(PlayerBase p) {
        player = p;
        inventory = p.inventory;
        initialize();

    }

    public void  initialize(){
        //the inventory
        charge = new Texture("resources/assets/securityNotCollected.png");
        flare =  new Texture("resources/assets/flareNotCollected.png");
        divingSuit =  new Texture("resources/assets/diving-suit.png");
        gun = new Texture("resources/assets/gunNotCollected.png");
        rope =  new Texture("resources/assets/rope.png");
        shovel =  new Texture("resources/assets/shovel.png");
        collectionBg =  new Texture("resources/assets/bg_deck.png");

        foodButton = new ClickableButton("resources/assets/food_1x.png", 570, 450);
        foodButton.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hope it works!");
            }
        });

        ropeButton =  new ClickableButton("resources/assets/rope_1x.png", 570, 430);
        ropeButton.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("Rope clicked!");
            }
        });

        colletedGun =  new Texture("resources/assets/gun.png");
        colletedFlare = new Texture("resources/assets/flare.png");
        colletedCharge =  new Texture("resources/assets/security.png");


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

            if(Inventory.isGunCollected) gun = colletedGun;
            if(Inventory.isChargeCollected) charge = colletedCharge;
            if(Inventory.isFlareCollected) flare = colletedFlare;


    }

    public void render(){
        if(!player.isTurn) return;
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
        updateGoal();
        //display the collected parts for the gun
        batch.draw(collectionBg,470, 0, 170, 0.41f * 170);
        font.draw(batch, "GOAL", 530, 50);
        batch.draw(gun,590, 10, 25, 25 );
        batch.draw(flare,550, 10, 25, 25 );
        batch.draw(charge,510, 14, 20, 10 );
        batch.end();
    }

    public void dispose() {

        font.dispose();
    }

}
