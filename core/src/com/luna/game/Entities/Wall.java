package com.luna.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Wall extends Entity {


    public Wall(Texture image, float[] initialLocation, float[] dimensions) {
        super(image, initialLocation, dimensions);
        // TODO Auto-generated constructor stub
    }

    public Sprite getWall() {
        return this.getSprite();
    }
}
