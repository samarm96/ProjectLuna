package com.luna.game.DataFiles;

import com.badlogic.gdx.graphics.Texture;
import com.luna.game.Components.Attributes;
import com.luna.game.Components.Health;
import com.luna.game.Components.SpriteComp;
import com.luna.game.Entities.Player;

public class PlayerData {
    private String imagePath;
    private float[] initialLocation;
    private float[] dimensions;
    private int maxHealth;
    private float attack;

    public PlayerData(){

    }
    
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public String getImagePath(){
        return this.imagePath;
    }

    public void setInitialLocation(float[] initialLocation){
        this.initialLocation = initialLocation;
    }

    public float[] getInitialLocation(){
        return this.initialLocation;
    }

    public void setDimensions(float[] dimensions){
        this.dimensions = dimensions;
    }

    public float[] getDimensions(){
        return this.dimensions;
    }

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }

    public void setAttack(float attack){
        this.attack = attack;
    }

    public float getAttack(){
        return this.attack;
    }

    public Player createPlayer(){
        Player p = new Player();
        SpriteComp spriteComponent = new SpriteComp(new Texture(this.imagePath), this.initialLocation, this.dimensions);
        Health health = new Health(maxHealth, maxHealth);
        Attributes attributes = new Attributes((int) this.attack);
        p.addComponent(spriteComponent);    
        p.addComponent(health);        
        p.addComponent(attributes);        
    
        return p;
    }
}
