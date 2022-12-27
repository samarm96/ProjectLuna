package com.luna.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class HealthBar extends Entity {

    private float originalHealthBarWidth;// = SCREEN_WIDTH / 4;


    public HealthBar(Texture image, float[] initialLocation, float[] initialDimensions,
            int health) {
        super(image, initialLocation, initialDimensions);

        this.setSprite(RenderHealthBar(health));
        this.getSprite().setTexture(image);
        this.originalHealthBarWidth = this.getSprite().getWidth();
    }

    public static Sprite RenderHealthBar(int health) {
        int maxHealth = 100;

        float healthBarBuffer = 10;

        float xLocation = SCREEN_WIDTH - SCREEN_WIDTH / 4 + healthBarBuffer;
        float yLocation = SCREEN_HEIGHT / 2;
        float height = 24;

        float width = (health / maxHealth) * SCREEN_WIDTH / 4;
        if (width < 0) {
            width = 0;
        }

        Sprite healthBar = new Sprite();
        healthBar.setPosition(xLocation, yLocation);
        healthBar.setSize(width, height);

        return healthBar;
    }



    public void reduceHealth(final Player player) {

        int health = player.getHealth();
        int maxHealth = player.getMaxHealth();
        Sprite sprite = this.getSprite();

        if ((originalHealthBarWidth - (originalHealthBarWidth
                - ((float) health / maxHealth) * originalHealthBarWidth)) < 0) {
            sprite.setSize(0, sprite.getHeight());

        } else {

            sprite.setSize(
                    originalHealthBarWidth - (originalHealthBarWidth
                            - ((float) health / maxHealth) * originalHealthBarWidth),
                    sprite.getHeight());

            System.out.println("Health: " + player.getHealth());
        }
    }

}
