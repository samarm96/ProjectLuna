package com.luna.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.luna.game.Engine.Utilities;

public class HostileNpc extends Character {

    public HostileNpc(Texture image, float[] initialLocation, float[] dimensions, int maxHealth) {
        super(image, initialLocation, dimensions, maxHealth);
    }

    public Polygon attack(final Player player) {

        Polygon attackTriangle = Utilities.CreateTriangle(this.getSprite(), 10, 10);

        return attackTriangle;

    }


}
