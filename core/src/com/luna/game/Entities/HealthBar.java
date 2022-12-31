package com.luna.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.luna.game.Engine.Utilities;

public class HealthBar extends Entity {

    private float originalHealthBarWidth;
	private float healthBarBuffer;

    public HealthBar(Texture image, float[] initialLocation, float[] initialDimensions,
            int health, float healthBarBuffer) {
        super(image, initialLocation, initialDimensions);
        
        this.healthBarBuffer = healthBarBuffer;
        this.setSprite(RenderHealthBar(health));
        this.getSprite().setTexture(image);
        this.originalHealthBarWidth = this.getSprite().getWidth();
    }

    public Sprite RenderHealthBar(int health) {
        int maxHealth = 100;

        final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
        final int WORLD_WIDTH = Utilities.WORLD_WIDTH;

        float xLocation = this.getLocation()[0] + healthBarBuffer; 
        float yLocation = this.getLocation()[0]; 
        float height = WORLD_HEIGHT/20;

        float width = (health / maxHealth) * WORLD_WIDTH / 4 - healthBarBuffer*2;
        if (width < 0) {
            width = 0;
        }

        Sprite healthBar = new Sprite();
        healthBar.setPosition(xLocation, yLocation);
        healthBar.setSize(width, height);

        return healthBar;
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
