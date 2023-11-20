package com.luna.game.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Utilities {


    public static int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    public static int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static int WORLD_HEIGHT = SCREEN_HEIGHT;
    public static int WORLD_WIDTH = SCREEN_WIDTH;

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
     * Checks for collision between two sprites. Returns true if collision is present.
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


    public static boolean ProximityCheck(Sprite object1, Sprite object2, int radius) {
        Rectangle rec = object1.getBoundingRectangle(); 
        rec.setWidth(object1.getWidth() + radius);
        rec.setHeight(object1.getHeight() + radius);

        if (rec.overlaps(object2.getBoundingRectangle())) {
            return true;
        } else {
            return false;
        }
    }
	// Check if Polygon intersects Sprite
	public static boolean PolySpriteCollisionCheck(Polygon p, Sprite player) {
		
        float[] vertices = new float[] {
			
			player.getX(), 								player.getY(),
			player.getX() + player.getWidth(), 		    player.getY(), 
			player.getX() + player.getWidth(), 		    player.getY() + player.getHeight(),
			player.getX(),								player.getY() + player.getHeight()
			
				};

		Polygon rPoly = new Polygon(vertices);

		if (Intersector.overlapConvexPolygons(p, rPoly))
			return true;
		else {
			return false;
		}
	}

    

}
