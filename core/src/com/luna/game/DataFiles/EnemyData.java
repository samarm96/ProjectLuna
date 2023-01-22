package com.luna.game.DataFiles;

import com.badlogic.gdx.graphics.Texture;
import com.luna.game.Entities.HostileNpc;

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

    public HostileNpc create(){
        HostileNpc p = new HostileNpc(new Texture(this.imagePath), this.initialLocation, this.dimensions, this.maxHealth);
        p.setAttack(attack);
        
        return p;
    }
}
