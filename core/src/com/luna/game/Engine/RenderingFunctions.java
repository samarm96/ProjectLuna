package com.luna.game.Engine;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.luna.game.Entities.Wall;

public class RenderingFunctions {

    final static int SCREEN_HEIGHT = Utilites.SCREEN_HEIGHT;
    final static int SCREEN_WIDTH = Utilites.SCREEN_WIDTH;

    public static List<Wall> RenderWallLine(Texture texture, float x, float y, float width,
            float height, int number, boolean verticalFlag, boolean downLeft) {
        List<Wall> walls = new ArrayList<>();

        if (y - height * number > SCREEN_HEIGHT - y && verticalFlag == true
                && downLeft == true) {
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception(
                        "Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (y + height * number > SCREEN_HEIGHT - y && verticalFlag == true
                && downLeft == false) {
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception(
                        "Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (x - width * number > SCREEN_WIDTH - x && verticalFlag == false
                && downLeft == true) {
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception(
                        "Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (x + width * number > SCREEN_WIDTH - x && verticalFlag == false
                && downLeft == false) {
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception(
                        "Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Wall wall;
        float[] dimensions = new float[] {width, height};
        for (int i = 0; i < number; i++) {
            wall = new Wall(texture, new float[]{0,0}, dimensions);
            if (verticalFlag == true) {
                if (downLeft == true) {
                    wall.setLocation(x, y - height * i);
                    walls.add(wall);
                } else {
                    wall.setLocation(x, y + height * i);
                    walls.add(wall);
                }
            }
            if (verticalFlag == false) {
                if (downLeft == true) {
                    wall.setLocation(x - width * i, y);
                    walls.add(wall);
                } else {
                    wall.setLocation(x + width * i, y);
                    walls.add(wall);
                }
            }

        }

        return walls;
    }

    public static List<Wall> RenderBoundaryWalls(Texture texture, float wallWidth,
            float wallHeight) {
        List<Wall> boundaryWalls = new ArrayList<>();

        float screenWidth = SCREEN_WIDTH;
        float screenHeight = SCREEN_HEIGHT;

        boundaryWalls = RenderSquareFromCorner(texture, wallWidth, wallHeight, (float) 0, (float) 0,
                screenHeight - 1, screenWidth - 1);

        return boundaryWalls;
    }

    public static List<Wall> RenderSquareFromCorner(Texture texture, float wallWidth,
            float wallHeight, float cornerX, float cornerY, float squareHeight, float squareWidth) {
        List<Wall> boundaryWalls = new ArrayList<>();

        List<Wall> topWalls = RenderWallLine(texture, cornerX, cornerY + squareHeight - wallHeight,
                wallWidth, wallHeight, Math.round(squareWidth / wallWidth), false, false);
        List<Wall> bottomWalls = RenderWallLine(texture, cornerX, cornerY, wallWidth, wallHeight,
                Math.round(squareWidth / wallWidth), false, false);
        List<Wall> leftWalls = RenderWallLine(texture, cornerX, cornerY + wallHeight, wallWidth,
                wallHeight, Math.round((squareHeight - wallHeight) / wallHeight), true, false);
        List<Wall> rightWalls = RenderWallLine(texture, cornerX + squareWidth - wallWidth,
                cornerY + wallHeight, wallWidth, wallHeight,
                Math.round((squareHeight - wallHeight) / wallHeight), true, false);

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


}


