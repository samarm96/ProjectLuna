package com.luna.game.Engine;

import com.badlogic.gdx.math.Polygon;
import com.luna.game.Entities.Player;

public class PlayerControls implements Controls {

    final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
    final int WORLD_WIDTH = Utilities.WORLD_WIDTH;

    private Player player;

    public PlayerControls(Player player) {
        this.player = player;
    }

    public void moveLeft() {
        this.player.getSprite().translateX(-1);
    }

    public void moveRight() {
        this.player.getSprite().translateX(1);
    }

    public void moveDown() {
        this.player.getSprite().translateY(-1);
    }

    public void moveUp() {
        this.player.getSprite().translateY(1);
    }

    @Override
    public Polygon attack() {
        Polygon attackTriangle = RenderingFunctions.CreateTriangle(this.player.getSprite(), 10, 10);

        return attackTriangle;
    }
}
