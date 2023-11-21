package com.luna.game.Utilities;

import com.badlogic.gdx.Gdx;

public class Constants {
    public static int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    public static int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static int WORLD_HEIGHT = SCREEN_HEIGHT;
    public static int WORLD_WIDTH = SCREEN_WIDTH;

    public static void updateConstants(int width, int height) {
        SCREEN_HEIGHT = height;
        SCREEN_WIDTH = width;
        WORLD_HEIGHT = height;
        WORLD_WIDTH = width;
    }

}
