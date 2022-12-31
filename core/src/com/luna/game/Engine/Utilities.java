package com.luna.game.Engine;

import java.lang.reflect.Array;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Utilities {


    final public static int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    final public static int SCREEN_WIDTH = Gdx.graphics.getWidth();
    final public static int WORLD_HEIGHT = 100;
    final public static int WORLD_WIDTH = 100;

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

	// Check if Polygon intersects Rectangle
	public static boolean isCollision(Polygon p, Sprite player) {
		
        Rectangle rect = player.getBoundingRectangle();
		float[] vertices = new float[] {player.getX(), player.getY(),
				player.getX() + rect.width, player.getY(),
				player.getX(), player.getY() + rect.height, 
				player.getX() + rect.width, player.getY() + rect.height};


        Polygon rPoly = new Polygon(vertices);

		if (Intersector.overlapConvexPolygons(p, rPoly))
			return true;
		else {
			return false;
		}
	}

    public static Polygon CreateTriangle(Sprite sprite, float height, float baseLength) {

        Rectangle charRectangle = sprite.getBoundingRectangle();
        Vector2 rectCenter = charRectangle.getCenter(new Vector2());

        float[] originVertex = new float[] {rectCenter.x + charRectangle.getWidth() / 2,
                rectCenter.y};
        float[] topVertex =
                new float[] {originVertex[0] + height, originVertex[1] + baseLength/2};
        float[] bottomVertex =
                new float[] {originVertex[0] + height, originVertex[1] - baseLength/2};

        float[] vertices = concatArray(concatArray(originVertex, topVertex), bottomVertex);

        return new Polygon(vertices);
    }

    static <T> T concatArray(T array1, T array2) {
        if (!array1.getClass().isArray() || !array2.getClass().isArray()) {
            throw new IllegalArgumentException("Only arrays are accepted.");
        }


        Class<?> compType1 = array1.getClass().getComponentType();
        Class<?> compType2 = array2.getClass().getComponentType();

        if (!compType1.equals(compType2)) {
            throw new IllegalArgumentException("Two arrays have different types.");
        }
        int len1 = Array.getLength(array1);
        int len2 = Array.getLength(array2);

        @SuppressWarnings("unchecked")
        // the cast is safe due to the previous checks
        T result = (T) Array.newInstance(compType1, len1 + len2);

        System.arraycopy(array1, 0, result, 0, len1);
        System.arraycopy(array2, 0, result, len1, len2);

        return result;

    }

}
