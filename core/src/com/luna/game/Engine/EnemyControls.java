package com.luna.game.Engine;

import com.badlogic.gdx.math.Polygon;
import com.luna.game.Entities.HostileNpc;

public class EnemyControls implements Controls {

    final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
    final int WORLD_WIDTH = Utilities.WORLD_WIDTH;

    private HostileNpc enemy;

    public EnemyControls(HostileNpc enemy) {
        this.enemy = enemy;
    }


    @Override
    public void moveLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveDown() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public Polygon attack() {

        Polygon attackTriangle = RenderingFunctions.CreateTriangle(this.enemy.getSprite(), 10, 10);

        return attackTriangle;
    }

}
