package com.rim.IceField.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.rim.IceField.GameMain;

//public class DesktopLauncher {
//	public static void main (String[] arg) {
//
////			DesktopMini2DxConfig config = new DesktopMini2DxConfig(IcefieldGame.GAME_IDENTIFIER);
////			config.vSyncEnabled = true;
////			new DesktopMini2DxGame(new IcefieldGame(), config);
//		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.foregroundFPS = 30;
//		new LwjglApplication(new IcefieldGame(), config);
//
//
//	}
//}

import com.rim.IceField.StartMenuGUI;

public class DesktopLauncher{
	public static void main(String[] args){
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(GameMain.GAME_IDENTIFIER);
		//DesktopMini2DxConfig config = new DesktopMini2DxConfig(StartMenuGUI.GAME_IDENTIFIER);
		config.vSyncEnabled = true;

		//new DesktopMini2DxGame(new StartMenuGUI(), config);

		new DesktopMini2DxGame(new GameMain(), config);
	}

}