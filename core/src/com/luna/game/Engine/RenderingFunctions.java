package com.luna.game.Engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.luna.game.Components.SpriteComp;
import com.luna.game.Entities.Wall;
import com.luna.game.Utilities.CollisionUtils;

public class RenderingFunctions {

    /**
     * Renders a line of sprites from an x y coordinate
     * @param texture the texture to render each individual block with
     * @param x the x coordinate to start at
     * @param y the y coordinate to start at
     * @param width the width of each sprite
     * @param height the height of each sprite
     * @param number the number of sprites to render
     * @param verticalFlag a flag indicating whether the line should be rendered vertically or horizontal. Defaults to rendering up
     * @param downLeft a flag indicating whether the line should be rendered to the left or down
     * @returns a list containing each sprite to be rednered
     */
    public static List<Wall> RenderWallLine(TextureRegion texture, float x, float y, float width,
            float height, int number, boolean verticalFlag, boolean downLeft) {
        List<Wall> walls = new ArrayList<>();

        final int worldHeight = CollisionUtils.WORLD_HEIGHT;
        final int worldWidth = CollisionUtils.WORLD_WIDTH;

        // Checks to ensure that the wall is within bounds
        if (y - height * number > worldHeight - y && verticalFlag == true
                && downLeft == true) {
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception(
                        "Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (y + height * number > worldHeight - y && verticalFlag == true
                && downLeft == false) {
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception(
                        "Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (x - width * number > worldWidth - x && verticalFlag == false
                && downLeft == true) {
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception(
                        "Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (x + width * number > worldWidth - x && verticalFlag == false
                && downLeft == false) {
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception(
                        "Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        float[] dimensions = new float[] {width, height};
        for (int i = 0; i < number; i++) {
            
            Wall wall = new Wall("Basic Wall");
            SpriteComp spriteComp = new SpriteComp(texture, new float[]{0,0}, dimensions);

            if (verticalFlag == true) {
                if (downLeft == true) {
                    spriteComp.getSprite().setPosition(x, y - height * i);
                    wall.addComponent(spriteComp);
                    walls.add(wall);

                } else {
                    spriteComp.getSprite().setPosition(x, y + height * i);
                    wall.addComponent(spriteComp);
                    walls.add(wall);

                }
            }
            if (verticalFlag == false) {
                if (downLeft == true) {
                    spriteComp.getSprite().setPosition(x - width * i, y);
                    wall.addComponent(spriteComp);
                    walls.add(wall);

                } else {
                    spriteComp.getSprite().setPosition(x + width * i, y);
                    wall.addComponent(spriteComp);
                    walls.add(wall);
                }
            }

        }

        return walls;
    }

    /**
     * Uses the render square from corner function to render walls across the boundary of the screen
     * @param texture the texture to render each individual block with
     * @param wallWidth the width of each individual sprite
     * @param wallHeight the height of each individual sprite
     * @returns a list containing each sprite to be rednered
     */
    public static List<Wall> RenderBoundaryWalls(TextureRegion texture, float wallWidth,
            float wallHeight) {
        List<Wall> boundaryWalls = new ArrayList<>();

        float worldWidth = CollisionUtils.WORLD_WIDTH;;
        float worldHeight = CollisionUtils.WORLD_HEIGHT;

        boundaryWalls = RenderSquareFromCorner(texture, wallWidth, wallHeight, (float) 0, (float) 0,
        worldHeight, worldWidth);

        return boundaryWalls;
    }

     /**
     * Uses the render sprite line function to render a square. 
     * @param texture the texture to render each individual block with
     * @param wallWidth the width of each individual sprite
     * @param wallHeight the height of each individual sprite
     * @param cornerX the x coordinate of the corner where the square is rendered from
     * @param cornerY the y coordinate of the corner where the square is rendered from
     * @param squareHeight the height of the overall square
     * @param squareWidth the width of the overall square
     * 
     * @returns a list containing each sprite to be rednered
     */
    public static List<Wall> RenderSquareFromCorner(TextureRegion texture, float wallWidth,
            float wallHeight, float cornerX, float cornerY, float squareHeight, float squareWidth) {

        List<Wall> boundaryWalls = new ArrayList<>();

        List<Wall> topWalls = RenderWallLine(texture, cornerX, cornerY + squareHeight - wallHeight,
                wallWidth, wallHeight, Math.round(squareWidth / wallWidth), false, false);

        List<Wall> bottomWalls = RenderWallLine(texture, cornerX, cornerY, wallWidth, wallHeight,
                Math.round(squareWidth / wallWidth), false, false);

        List<Wall> leftWalls = RenderWallLine(texture, cornerX, cornerY + wallHeight, wallWidth,
                wallHeight, Math.round((squareHeight - wallHeight) / wallHeight)-1, true, false);

        List<Wall> rightWalls = RenderWallLine(texture, cornerX + squareWidth - wallWidth,
                cornerY + wallHeight, wallWidth, wallHeight,
                Math.round((squareHeight - wallHeight) / wallHeight)-1, true, false);

        for (int i = 0; i < topWalls.size(); i++) {
            boundaryWalls.add(topWalls.get(i));
        }
        for (int i = 0; i < bottomWalls.size(); i++) {
            boundaryWalls.add(bottomWalls.get(i));
        }
        for (int i = 0; i < leftWalls.size(); i++) {
            boundaryWalls.add(leftWalls.get(i));
        }
        for (int i = 0; i < rightWalls.size(); i++) {
            boundaryWalls.add(rightWalls.get(i));
        }


        return boundaryWalls;
    }

    /**
     * Creates a polygon object in the shape of a triangle at the front of a sprite
     * 
     *                  3
     *                  -
     *       height     - b
     * 1 ---------------- a
     *                  - s
     *                  - e
     *                  2 
     * 
     * @param sprite the sprite that the triangle will be created in front of
     * @param height the height of the triangle
     * @param baseLength the length of the triangle
     * @returns a polygon representing the triangle
     */
    public static Polygon CreateTriangle(Sprite sprite, float height, float baseLength) {

        Rectangle charRectangle = sprite.getBoundingRectangle();
        Vector2 rectCenter = charRectangle.getCenter(new Vector2());

        float[] originVertex = 
                new float[] {rectCenter.x + charRectangle.getWidth() / 2,                       rectCenter.y};
        float[] topVertex =
                new float[] {originVertex[0] + height,                                          originVertex[1] + baseLength/2};
        float[] bottomVertex =
                new float[] {originVertex[0] + height,                                          originVertex[1] - baseLength/2};

        float[] vertices = concatArray(concatArray(originVertex, topVertex), bottomVertex);

        return new Polygon(vertices);
    }


    public static Sprite CreateMissile(TextureRegion texture, Sprite sprite, float height, float width){
        Rectangle charRectangle = sprite.getBoundingRectangle();
        Vector2 rectCenter = charRectangle.getCenter(new Vector2());

        float[] corner = new float[] {rectCenter.x, rectCenter.y + charRectangle.getHeight()/2};

        Sprite missile = new Sprite(texture);
        missile.setPosition(corner[0], corner[1]);
        missile.setSize(width, height);

        return missile;
    }

    /**
     * Utility array that concatenates two arrays of the same type
     * @param array1 the first array 
     * @param array2 the second array
     * @returns a concatenated array of the input type
     */
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


