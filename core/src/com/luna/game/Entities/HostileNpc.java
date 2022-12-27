package com.luna.game.Entities;

import com.badlogic.gdx.graphics.Texture;

public class HostileNpc extends Character {

    public HostileNpc(Texture image, float[] initialLocation, float[] dimensions, int maxHealth) {
        super(image, initialLocation, dimensions, maxHealth);
        //TODO Auto-generated constructor stub
    }
    
    public void attackPlayer(final Player player, final float delta){
        player.removeHealth((int) (this.getAttack()*delta));
    }
}
