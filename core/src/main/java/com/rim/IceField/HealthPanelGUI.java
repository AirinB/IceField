package com.rim.IceField;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class HealthPanelGUI {
    SpriteBatch batch;
    ArrayList<HealthGUI> healthDescriptors;
   // ArrayList<PlayerBase> players;
    int posX;
    int posY;

    public HealthPanelGUI(ArrayList<PlayerBase> players, int X, int Y) {
        //add the health descriptors for all the players
        batch = new SpriteBatch();
        healthDescriptors = new ArrayList<HealthGUI>();

        for (PlayerBase p: players) {
            HealthGUI h = new HealthGUI(p);
            healthDescriptors.add(h);
        }

        posX = X;
        posY = Y;
    }

    public void render(){
        batch.begin();
       // int offsetX = 0;
        //is negative, go down on the y axis
        int offsetY = 0;

        for (HealthGUI h: healthDescriptors) {
            h.updatePlayerLife();
            h.render(batch, posX, posY - offsetY);
            offsetY += 25;
        }
        batch.end();
    }

    public void  dispose(){
        batch.dispose();

    }



}
