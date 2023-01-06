package com.luna.game.Entities;

import com.badlogic.gdx.graphics.Texture;


public class HostileNpc extends Character {
    
    HealthBar healthBar;

    public HostileNpc(Texture image, float[] initialLocation, float[] dimensions, int maxHealth) {
        super(image, initialLocation, dimensions, maxHealth);
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public HealthBar getHealthBar() {
        return this.healthBar;
    }


}
