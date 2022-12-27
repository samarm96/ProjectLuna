package com.luna.game.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Utilites {


    final public static int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    final public static int SCREEN_WIDTH = Gdx.graphics.getWidth();

    /**
     * Checks for collision between two rectangles
     * 
     * @param object1 the object being checked
     * @param object2 the object being checked against
     */
    public static boolean CollisonCheck(Rectangle object1, Rectangle object2) {
        if (object1.overlaps(object2)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Checks for collision between two sprites
     * 
     * @param object1 the object being checked
     * @param object2 the object being checked against
     */
    public static boolean CollisonCheck(Sprite object1, Sprite object2) {
        if (object1.getBoundingRectangle().overlaps(object2.getBoundingRectangle())) {
            return true;
        } else {
            return false;
        }

    }
}
