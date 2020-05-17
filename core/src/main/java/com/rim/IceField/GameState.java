package com.rim.IceField;

public class GameState {
    private static boolean isGameState = false;

    public static void setGameState(boolean state)
    {
        isGameState = state;
    }
    public static boolean getGameState()
    {
        return isGameState;
    }
}
