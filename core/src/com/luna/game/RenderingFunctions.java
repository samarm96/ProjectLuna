package com.luna.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class RenderingFunctions {
   
    
    public static List<Wall> RenderWallLine(Texture texture, float x, float y, float width, float height, int number, boolean verticalFlag, boolean downLeft) {
        List<Wall> walls = new ArrayList<>();

        if(y-height*number > Gdx.graphics.getHeight()-y && verticalFlag == true && downLeft == true){
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception("Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(y+height*number > Gdx.graphics.getHeight()-y && verticalFlag == true && downLeft == false){
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception("Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
         }
        if(x-width*number > Gdx.graphics.getWidth()-x && verticalFlag == false && downLeft == true){
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception("Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(x+width*number > Gdx.graphics.getWidth()-x && verticalFlag == false && downLeft == false){
            System.out.println("Cannot create walls, desired size is larger than screen size.");
            try {
                throw new Exception("Cannot create walls, desired size is larger than screen size.");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        for(int i = 0; i < number; i++){
            Wall wall = new Wall(texture);
            wall.setDimensions(width, height);
            if(verticalFlag == true){
                if(downLeft == true){
                    wall.setLocation(x, y-height*i);
                    walls.add(wall);
                } else { 
                    wall.setLocation(x, y+height*i);
                    walls.add(wall);
                }
            }
            if(verticalFlag == false){
                if(downLeft == true){
                    wall.setLocation(x-width*i, y);
                    walls.add(wall);
                } else {
                    wall.setLocation(x+width*i, y);
                    walls.add(wall);
                }
            }            
            
        }


        return walls;
    }

    public static List<Wall> createBoundaryWalls(Texture texture, float wallWidth, float wallHeight){
        List<Wall> boundaryWalls = new ArrayList<>();

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        List<Wall> topWalls = RenderWallLine(texture, (float) 0.0, screenHeight-wallHeight, wallWidth, wallHeight, Math.round(screenWidth/wallWidth), false, false);
        List<Wall> bottomWalls = RenderWallLine(texture, (float) 0.0, (float) 0.0, wallWidth, wallHeight, Math.round(screenWidth/wallWidth), false, false);
        List<Wall> leftWalls = RenderWallLine(texture, (float) 0.0, 0+wallHeight, wallWidth, wallHeight, Math.round((screenHeight-wallHeight)/wallHeight), true, false);
        List<Wall> rightWalls = RenderWallLine(texture, screenWidth-wallWidth, 0+wallHeight, wallWidth, wallHeight, Math.round((screenHeight-wallHeight)/wallHeight), true, false);

        for(int i = 0; i < topWalls.size(); i++){
            boundaryWalls.add(topWalls.get(i));
        }
        for(int i = 0; i < bottomWalls.size(); i++){
            boundaryWalls.add(bottomWalls.get(i));
        }
        for(int i = 0; i < leftWalls.size(); i++){
            boundaryWalls.add(leftWalls.get(i));
        }
        for(int i = 0; i < rightWalls.size(); i++){
            boundaryWalls.add(rightWalls.get(i));
        }
        

        return boundaryWalls;
    }
}


