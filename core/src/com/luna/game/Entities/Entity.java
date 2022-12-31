package com.luna.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.luna.game.Engine.Utilities;

public class Entity {

    final static int SCREEN_HEIGHT = Utilities.SCREEN_HEIGHT;
    final static int SCREEN_WIDTH = Utilities.SCREEN_WIDTH;

    private Sprite sprite;

    public Entity(Texture image, float[] initialLocation, float[] initialDimensions) {

        this.sprite = new Sprite(image);
        this.sprite.setX(initialLocation[0]);
        this.sprite.setY(initialLocation[1]);

        // Set width/height of player character
        this.sprite.setSize(initialDimensions[0], initialDimensions[1]);

    }

    /**
     * Setter for the player's sprite parameter.
     */
    public Sprite getSprite() {
        return this.sprite;
    }

    /**
     * Getter for the player's sprite parameter.
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Setter for the sprite's dimensions. Dimensions are represented as a 2 element float array.
     * Element 0 - width [pixels] Element 1 - height [pixels]
     * 
     * @param dimensions - float array containing the dimensions
     */
    public void setDimensions(float[] dimensions) {
        this.sprite.setSize(dimensions[0], dimensions[1]);
    }

    /**
     * Getter for the sprite's dimensions. Dimensions are represented as a 2 element float array.
     * Element 0 - width [pixels] Element 1 - height [pixels]
     */
    public float[] getDimensions() {
        return createDimensionArray(this.sprite);
    }

    /**
     * Setter for the sprite's location parameter. Location is represented as a 2 element float
     * array. Element 0 - x position [pixels] Element 1 - y position [pixels]
     * 
     * @param dimensions - float array containing the location
     * 
     */
    public void setLocation(float[] location) {
        this.sprite.setX(location[0]);
        this.sprite.setY(location[1]);
    }

    /**
     * Setter for the sprite's location parameter. Location is represented as an x and y float value
     * 
     * @param x - float containing the x position
     * @param x - float containing the y position
     */
    public void setLocation(float x, float y) {
        this.sprite.setX(x);
        this.sprite.setY(y);
    }

    /**
     * Getter for the sprite's location parameter. Location is represented as a 2 element float
     * array. Element 0 - x position [pixels] Element 1 - y position [pixels]
     */
    public float[] getLocation() {
        return createLocationArray(this.sprite);
    }

    private float[] createLocationArray(final Sprite sprite){
        return new float[]{this.sprite.getX(), this.sprite.getY()};

    }

    private float[] createDimensionArray(final Sprite sprite){
        return new float[]{this.sprite.getWidth(), this.sprite.getHeight()};

    }



}
