package com.rim.IceField;

import com.badlogic.gdx.graphics.Texture;

public class Gun extends ItemBase {
    Texture tex = new Texture("mini2Dx.png");
    public Gun() {
        super();
        tag = "gun"; //name of the derived class
    }
}
