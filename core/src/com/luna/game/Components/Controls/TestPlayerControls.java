package com.luna.game.Components.Controls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.luna.game.Components.SpriteComp;
import com.luna.game.Engine.RenderingFunctions;
import com.luna.game.Engine.Utilities;
import com.luna.game.Entities.TestPlayer;

public class TestPlayerControls implements Controls {

    final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
    final int WORLD_WIDTH = Utilities.WORLD_WIDTH;

    private TestPlayer testPlayer;
    private SpriteComp playerSprite;

    public TestPlayerControls(TestPlayer player) {
        this.testPlayer = player;
        this.playerSprite = (SpriteComp) testPlayer.getComponent("Sprite").get();

    }

    public void moveLeft() {
        this.playerSprite.getSprite().translateX(-1);
    }

    public void moveRight() {
        this.playerSprite.getSprite().translateX(1);
    }

    public void moveDown() {
        this.playerSprite.getSprite().translateY(-1);
    }

    public void moveUp() {
        this.playerSprite.getSprite().translateY(1);
    }

    @Override
    public Polygon attack() {
        Polygon attackTriangle = RenderingFunctions.CreateTriangle(this.playerSprite.getSprite(), 10, 10);

        return attackTriangle;
    }

    public Sprite rangedAttack(Texture missileImage) {

        Sprite missile = RenderingFunctions.CreateMissile(missileImage,
                this.playerSprite.getSprite(), 5, 1);

        return missile;
    }
}
