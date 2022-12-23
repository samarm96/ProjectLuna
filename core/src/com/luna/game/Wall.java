package com.luna.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Wall {
    private Sprite wall;
      
    public Wall(Texture image){
        this.wall = new Sprite(image);
    }

    public Texture getTexture(){return this.wall.getTexture();}
    public Sprite getWall(){return this.wall;}
    public float getWidth(){return wall.getWidth();}
    public float getHeight(){return wall.getHeight();}
    
    /* 
     * Function that returns a two element float array.
     * Element zero returns the wall's x location.
     * Element 1 returns the wall's y location.
     */
    public float[] getLocation(){
        float[] location = new float[2];
        location[0] = wall.getX();
        location[1] = wall.getY();
        return location;
    }

    public void setLocation(float x, float y){
        this.wall.setX(x);
        this.wall.setY(y);
    }
    public void setDimensions(float[] dimensions){
        this.wall.setSize(dimensions[0], dimensions[1]);
    }    

}
