package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class InventoryGUI {
    PlayerBase player;
    Inventory inventory;

    //The inventory
    Texture food;
    Texture rope;
    Texture shovel;
    Texture divingSuit;
    Texture charge;
    Texture flare;
    Texture gun;

    BitmapFont font; // text near the life left

    public InventoryGUI(PlayerBase p) {
        player = p;
        inventory = p.inventory;
        initialize();

    }
    public void  initialize(){
        //the inventory
        charge = new Texture("assets/security.png");
        flare =  new Texture("assets/flare.png");
        food =  new Texture("assets/food.png");
        divingSuit =  new Texture("assets/diving-suit.png");
        gun = new Texture("assets/gun.png");
        rope =  new Texture("assets/rope.png");
        shovel =  new Texture("assets/shovel.png");

        //font2
        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("assets/8bitFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 12;
        parameter1.borderWidth = 1;
        parameter1.color = Color.WHITE;
        parameter1.shadowOffsetX = 1;
        parameter1.shadowOffsetY = 1;
        parameter1.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        font = generator1.generateFont(parameter1); // font size 12 pixels
        generator1.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    public void update(){

    }

    public void render(SpriteBatch batch){
        if(!player.isTurn) return;
        //render the useful items
        // 20  px difference on y
        batch.draw(food,570, 450, 15, 15 );
        font.draw(batch, " x6", 580, 460);

        batch.draw(rope,570, 430, 15, 15 );
        font.draw(batch, " x2", 580, 440);

        batch.draw(shovel,570, 410, 15, 15 );
        font.draw(batch, " x0", 580, 420);

        batch.draw(divingSuit,570, 390, 20, 20);
        font.draw(batch, " x0", 580, 400);

      //  batch.draw(charge,570, 410, 15, 15 );
      //  font.draw(batch, " x0", 580, 420);


        //this part should be taken from here to the map
        //or to the player, can be left here anyway
        font.draw(batch, "GOAL", 530, 50);
        //display the collected parts for the gun
        batch.draw(gun,590, 10, 25, 25 );
        batch.draw(flare,550, 10, 25, 25 );
        batch.draw(charge,510, 10, 25, 25 );





    }


}
