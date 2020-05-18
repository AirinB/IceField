package com.rim.IceField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.rim.IceField.Textures.LifeBarTexture;

import java.util.ArrayList;

public class HealthPanelGUI {
    SpriteBatch batch;
    ArrayList<LifeBarTexture> healthPanels;

    public HealthPanelGUI(ArrayList<PlayerBase> players, int X, int Y) {
        batch = new SpriteBatch();
        healthPanels = new ArrayList<LifeBarTexture>();


        BackgroundColor backgroundColor = new BackgroundColor("resources/assets/ice_texture.jpg");

        Skin skin = new Skin(Gdx.files.internal("resources/skins/ice/freezing-ui.json"));
        Table container = new Table(); ;
        container.setFillParent(true);
        container.align(Align.topLeft);

        GameStage.stage.addActor(container);

        Table table = new Table();
        table.setBackground(backgroundColor);
        final ScrollPane scroll = new ScrollPane(table, skin);

        for (PlayerBase p: players) {
            table.row().padLeft(5).padTop(5);
            table.add(new Label("Player " + p.getID(), skin)).expandX().fillX();
            LifeBarTexture lifeBar = new LifeBarTexture(Math.max(4, p.heatLevel), p);
            healthPanels.add(lifeBar);
            table.add(lifeBar).bottom().left();
        }

        container.add(scroll).size(185, 60);
    }

    public void render(){
        for (LifeBarTexture healthBar: healthPanels) {
            healthBar.update();
        }
    }

    public void  dispose(){
        batch.dispose();

    }
}
