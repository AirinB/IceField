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
        batch.draw(charge,400, 400, 10, 10 );


    }


}
