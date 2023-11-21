package com.luna.game.Components.Controls;

import com.luna.game.Components.SpriteComp;
import com.luna.game.Entities.Player;
import com.luna.game.Screens.InventoryScreen;
import com.luna.game.Screens.ProjectLuna;
import com.luna.game.Utilities.Constants;

public class PlayerControls implements Controls {

    final int WORLD_HEIGHT = Constants.WORLD_HEIGHT;
    final int WORLD_WIDTH = Constants.WORLD_WIDTH;

    private Player testPlayer;
    private SpriteComp playerSprite;
    private ProjectLuna game;

    public PlayerControls(Player player, ProjectLuna game) {
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

    @Override
    public void openInventory() {
        game.setScreen(new InventoryScreen(game));
    }


}
