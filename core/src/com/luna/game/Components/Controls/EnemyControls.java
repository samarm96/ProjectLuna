package com.luna.game.Components.Controls;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.luna.game.Entities.Enemy;

public class EnemyControls implements Controls {



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


    @Override
    public void openInventory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openInventory'");
    }

}
