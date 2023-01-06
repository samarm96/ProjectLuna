package com.luna.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.luna.game.Engine.Utilities;

public class HealthBar extends Entity {

    private float originalHealthBarWidth;
	private float healthBarBuffer;
    private Character character;
    /**
     * 
     * @param image
     * @param initialLocation
     * @param initialDimensions
     * @param health
     * @param healthBarBuffer
     */
    public HealthBar(Texture image, Character character) {
        super(image, character.getLocation(), new float[] {0, 0});
        
        this.character = character;
        this.healthBarBuffer = 0;
        this.setSprite(RenderHealthBar(this.character.getHealth()));
        this.getSprite().setTexture(image);
        this.originalHealthBarWidth = this.getSprite().getWidth();
    }

    /**
     * 
     * @param health
     * @return
     */
    public Sprite RenderHealthBar(int health) {
        int maxHealth = this.character.getMaxHealth();

        final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;

        float xLocation = this.getLocation()[0] + healthBarBuffer; 
        float yLocation = this.getLocation()[1] + this.character.getSprite().getHeight(); 
        float height = WORLD_HEIGHT/40;

        float width = (health / maxHealth) * this.character.getDimensions()[0] - healthBarBuffer*2;
        if (width < 0) {
            width = 0;
        }

        Sprite healthBar = new Sprite();
        healthBar.setPosition(xLocation, yLocation);
        healthBar.setSize(width, height);

        return healthBar;
    }

    /**
     * Set to keep the bar above the character
     */
    public void updateLocation(){
        this.getSprite().setPosition(character.getSprite().getX(), character.getSprite().getY()+ character.getDimensions()[1]);
    }

    public void draw(SpriteBatch batch){
        this.updateLocation();
        getSprite().draw(batch);
    }

    public void reduceHealth(final Character character) {

        int health = character.getHealth();
        int maxHealth = character.getMaxHealth();
        Sprite sprite = this.getSprite();

        if ((originalHealthBarWidth - (originalHealthBarWidth
                - ((float) health / maxHealth) * originalHealthBarWidth)) < 0) {
            sprite.setSize(0, sprite.getHeight());

        } else {

            sprite.setSize(
                    originalHealthBarWidth - (originalHealthBarWidth
                            - ((float) health / maxHealth) * originalHealthBarWidth),
                    sprite.getHeight());

            System.out.println("Health: " + character.getHealth());
        }
    }



}
