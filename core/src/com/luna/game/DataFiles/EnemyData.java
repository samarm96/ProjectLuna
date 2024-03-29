package com.luna.game.DataFiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.luna.game.Components.Attributes;
import com.luna.game.Components.Health;
import com.luna.game.Components.SpriteComp;
import com.luna.game.Entities.Enemy;

public class EnemyData {

    private String name;
    private String imagePath;
    private float[] initialLocation;
    private float[] dimensions;
    private int maxHealth;
    private float attack;

    public EnemyData(){

    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
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

    public Enemy create(){
        Enemy p = new Enemy("Demon");        
        SpriteComp spriteComponent = new SpriteComp(new Texture(this.imagePath), this.initialLocation, this.dimensions);
        Health health = new Health(maxHealth, maxHealth);
        Attributes attributes = new Attributes((int) this.attack);
        p.addComponent(spriteComponent);    
        p.addComponent(health);        
        p.addComponent(attributes);        
    

        return p;
    }

    public Enemy create(TextureRegion region){
        Enemy p = new Enemy("Demon");
        SpriteComp spriteComponent = new SpriteComp(region, this.initialLocation, this.dimensions);
        Health health = new Health(maxHealth, maxHealth);
        Attributes attributes = new Attributes((int) this.attack);
        p.addComponent(spriteComponent);    
        p.addComponent(health);        
        p.addComponent(attributes);        
    
        return p;
    }

}
