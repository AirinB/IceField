package com.rim.IceField;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;

public class GameMain extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.rim.IceField";

	private Texture texture;
	
	@Override
    public void initialise() {
    	texture = new Texture("mini2Dx.png");
    }
    
    @Override
    public void update(float delta) {
    //Test
    }
    
    @Override
    public void interpolate(float alpha) {
    
    }
    
    @Override
    public void render(Graphics g) {
		g.drawTexture(texture, 0f, 0f);
    }

    public static void main(String[] args) {
      Game game = new Game();
      PlayerBase p1 = new Eskimo();
        ArrayList<PlayerBase> players = new ArrayList<PlayerBase>();
        players.add(p1);
      game.newGame(players);

    }
}
