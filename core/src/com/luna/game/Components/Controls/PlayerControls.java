package com.luna.game.Components.Controls;

import com.luna.game.Components.ComponentNumberList;
import com.luna.game.Components.SpriteComp;
import com.luna.game.Entities.Player;
import com.luna.game.Screens.InventoryScreen;
import com.luna.game.Screens.ProjectLuna;
import com.luna.game.Utilities.Constants;

public class PlayerControls implements Controls {

    final int WORLD_HEIGHT = Constants.WORLD_HEIGHT;
    final int WORLD_WIDTH = Constants.WORLD_WIDTH;

    private Player testPlayer;
    private Integer SPRITE_COMPONENT_NO = ComponentNumberList.SPRITECOMP;

    public PlayerControls(Player player) {
        this.testPlayer = player;
    }

    public void moveLeft() {
        SpriteComp playerSprite = (SpriteComp) testPlayer.getComponent(SPRITE_COMPONENT_NO);
        playerSprite.getSprite().translateX(-WORLD_WIDTH/100);
    }

    public void moveRight() {
        SpriteComp playerSprite = (SpriteComp) testPlayer.getComponent(SPRITE_COMPONENT_NO);
        playerSprite.getSprite().translateX(WORLD_WIDTH/100);
    }

    public void moveDown() {
        SpriteComp playerSprite = (SpriteComp) testPlayer.getComponent(SPRITE_COMPONENT_NO);
        playerSprite.getSprite().translateY(-WORLD_HEIGHT/100);
    }

    public void moveUp() {
        SpriteComp playerSprite = (SpriteComp) testPlayer.getComponent(SPRITE_COMPONENT_NO);
        playerSprite.getSprite().translateY(WORLD_HEIGHT/100);
    }

    @Override
    public void openInventory(ProjectLuna game) {
        game.setScreen(new InventoryScreen(game));
    }


}
