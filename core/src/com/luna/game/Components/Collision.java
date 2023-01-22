package com.luna.game.Components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Collision extends Component {

    public Collision(){
        setComponentNo(4);
    }

    /**
     * Checks for collision between two rectangles
     * 
     * @param object1 the object being checked
     * @param object2 the object being checked against
     */
    public boolean CollisonCheck(Rectangle object1, Rectangle object2) {
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
    public boolean CollisonCheck(Sprite object1, Sprite object2) {
        if (object1.getBoundingRectangle().overlaps(object2.getBoundingRectangle())) {
            return true;
        } else {
            return false;
        }

    }

	// Check if Polygon intersects Sprite
	public boolean PolySpriteCollisionCheck(Polygon p, Sprite player) {
		
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
