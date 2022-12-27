package com.luna.game.Entities;

import com.badlogic.gdx.graphics.Texture;

public class Character extends Entity {

    private int health;
    private int maxHealth;
    private float attack;

    public Character(Texture image, float[] initialLocation, float[] dimensions, int maxHealth) {
        super(image, initialLocation, dimensions);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    /**
     * Getter for the player's max health parameter.
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Setter for the player's max health parameter.
     */
    public void setMaxHealth(int health) {
        this.maxHealth = health;
    }

    /**
     * Getter for the player's health parameter.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Setter for the player's health parameter.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Adds a given amount of health the the player's total health. If the health added puts the
     * total health over 100, the health is maintained at 100
     * 
     * @param healthIncrement the amount of health to add
     */
    public void addHealth(int healthIncrement) {
        if ((this.health += healthIncrement) > 100) {
            this.health = 100;
        } else {
            this.health += healthIncrement;
        }
    }

    /**
     * Subtracts a given amount of health the the player's total health. If the health subtracted
     * puts the total health under 0, the health is maintained at 0
     * 
     * @param healthIncrement the amount of health to remove
     */
    public void removeHealth(int healthIncrement) {
        if ((this.health -= healthIncrement) < 0) {
            this.health = 0;
        } else {
            this.health -= healthIncrement;
        }
    }

    /**
     * Getter for the player's attack parameter.
     */
    public float getAttack() {
        return this.attack;
    }

    /**
     * Setter for the player's attack parameter.
     */
    public void setAttack(float attack) {
        this.attack = attack;
    }

}
