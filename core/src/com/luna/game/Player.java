package com.luna.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    final static int SCREEN_HEIGHT = Gdx.graphics.getHeight();
	final static int SCREEN_WIDTH = Gdx.graphics.getWidth();
    
    private float[] location;
    private Sprite sprite;
    private int health;

    public Player(Texture image, float[] initialLocation, float[] dimensions){
        this.location = initialLocation;

        this.sprite = new Sprite(image);
		sprite.setX(initialLocation[0]);
		sprite.setY(initialLocation[1]);

		// Set width/height of player character
		sprite.setSize(dimensions[0], dimensions[1]);

        this.health = 100;
    }

    public int getHealth(){
        return this.health;
    }
    public void setHealth(int health){
        this.health = health;
    }

    public void addHealth(int healthIncrement){
        if((this.health += healthIncrement) > 100){
            this.health = 100;
        } else {
        this.health += healthIncrement;
        }    }

    public void removeHealth(int healthIncrement){
        if((this.health -= healthIncrement) < 0){
            this.health = 0;
        } else {
        this.health -= healthIncrement;
        }
    }


    public Sprite getSprite(){
        return this.sprite;
    }
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public void setLocation(float[] location){
        this.location = location;
    }

    public float[] getLocation(){
        return this.location;
    }

}
