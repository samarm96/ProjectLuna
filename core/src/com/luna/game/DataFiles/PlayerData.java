package com.luna.game.DataFiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.luna.game.Components.Attributes;
import com.luna.game.Components.Health;
import com.luna.game.Components.Position;
import com.luna.game.Components.SpriteComp;
import com.luna.game.Entities.Player;

public class PlayerData {
    private String imagePath;
    private float[] initialLocation;
    private float[] dimensions;
    private int maxHealth;
    private float attack;
    private Texture texture;

    public PlayerData(){

    }
    
    public void setImagePath(String imagePath){
        texture = new Texture(imagePath);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
        
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

    public Player createPlayer(String name){
        
        Player p = Player.getPlayer(name);
        SpriteComp spriteComponent = new SpriteComp(this.texture, this.initialLocation, new float[]{24f, 24f});
        Health health = new Health(maxHealth, maxHealth);
        Attributes attributes = new Attributes((int) this.attack);
        Position pos = new Position(initialLocation[0], initialLocation[1]);
        p.addComponent(spriteComponent);    
        p.addComponent(health);        
        p.addComponent(attributes);        
        p.addComponent(pos);
    
        return p;
    }

    public Player createPlayer(String name, TextureRegion region){
        Player p = Player.getPlayer(name);
        SpriteComp spriteComponent = new SpriteComp(region, this.initialLocation, new float[]{24f, 24f});
        Health health = new Health(maxHealth, maxHealth);
        Attributes attributes = new Attributes((int) this.attack);
        Position pos = new Position(initialLocation[0], initialLocation[1]);

        p.addComponent(spriteComponent);    
        p.addComponent(health);        
        p.addComponent(attributes);        
        p.addComponent(pos);

        return p;
    }

}
