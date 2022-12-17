package com.luna.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Wall {
    private Texture wallImage;
    private Rectangle wall;
      
    public Wall(Texture image){
        this.wallImage = image;
        this.wall = new Rectangle();
    }

    public Texture getTexture(){return this.wallImage;}
    public Rectangle getWall(){return this.wall;}
    public float getWidth(){return wall.width;}
    public float getHeight(){return wall.height;}
    
    /* 
     * Function that returns a two element float array.
     * Element zero returns the wall's x location.
     * Element 1 returns the wall's y location.
     */
    public float[] getLocation(){
        float[] location = new float[2];
        location[0] = wall.x;
        location[1] = wall.y;
        return location;
    }

    public void setLocation(float x, float y){
        this.wall.x = x;
        this.wall.y = y;
    }
    public void setDimensions(float width, float height){
        this.wall.width = width;
        this.wall.height = height;
    }    

}
