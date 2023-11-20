package com.luna.game.Components.Controls;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.luna.game.Engine.Utilities;
import com.luna.game.Entities.Enemy;

public class EnemyControls implements Controls {

    final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
    final int WORLD_WIDTH = Utilities.WORLD_WIDTH;

    private Enemy enemy;
    private Sprite enemySprite;

    public EnemyControls(Enemy enemy) {
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

}
