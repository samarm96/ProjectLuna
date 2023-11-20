package com.luna.game.Components.Controls;

import com.luna.game.Components.SpriteComp;
import com.luna.game.Engine.Utilities;
import com.luna.game.Entities.Player;

public class PlayerControls implements Controls {

    final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
    final int WORLD_WIDTH = Utilities.WORLD_WIDTH;

    private Player testPlayer;
    private SpriteComp playerSprite;

    public PlayerControls(Player player) {
        this.testPlayer = player;
        this.playerSprite = (SpriteComp) testPlayer.getComponent("Sprite").get();

    }

    public void moveLeft() {
        this.playerSprite.getSprite().translateX(-WORLD_WIDTH/100);
    }

    public void moveRight() {
        this.playerSprite.getSprite().translateX(WORLD_WIDTH/100);
    }

    public void moveDown() {
        this.playerSprite.getSprite().translateY(-WORLD_HEIGHT/100);
    }

    public void moveUp() {
        this.playerSprite.getSprite().translateY(WORLD_HEIGHT/100);
    }


}
