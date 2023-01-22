package com.luna.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.luna.game.Components.Component;
import com.luna.game.Components.Health;
import com.luna.game.Engine.Utilities;
import com.luna.game.Components.SpriteComp;

public class HealthBar extends Component {

    private float originalHealthBarWidth;
	private float healthBarBuffer;
    private Entity character;
    private Sprite characterSprite;
    private Health characterHealth;
    private Sprite healthBarSprite;

    /**
     * 
     * @param image
     * @param initialLocation
     * @param initialDimensions
     * @param health
     * @param healthBarBuffer
     */
    public HealthBar(Texture image, Entity character) {
        
        
        this.character = character;

        this.characterSprite = ((SpriteComp) this.character.getComponent("Sprite").get()).getSprite();

        characterHealth = ((Health) this.character.getComponent("Health").get());

        this.healthBarBuffer = 0;
        this.setSprite(RenderHealthBar());
        this.getSprite().setTexture(image);
        this.originalHealthBarWidth = this.getSprite().getWidth();
    }

    public void setSprite(Sprite sprite){
        this.healthBarSprite = sprite;
    }

    public Sprite getSprite(){
        return this.healthBarSprite;
    }

    /**
     * 
     * @param health
     * @return
     */
    public Sprite RenderHealthBar() {

        
        int maxHealth = this.characterHealth.getMaxHealth();
        
        final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;

        float xLocation = this.characterSprite.getX() + healthBarBuffer; 
        float yLocation = this.characterSprite.getY() + this.characterSprite.getHeight(); 
        float height = WORLD_HEIGHT/40;

        float width = (this.characterHealth.getHealth() / maxHealth) * this.characterSprite.getWidth() - healthBarBuffer*2;
        if (width < 0) {
            width = 0;
        }

        Sprite healthBar = new Sprite();
        healthBar.setPosition(xLocation, yLocation);
        healthBar.setSize(width, height);

        return healthBar;
    }

    /**
     * Set to keep the bar above the character
     */
    public void updateLocation(){
        this.getSprite().setPosition(this.characterSprite.getX(), this.characterSprite.getY()+ this.characterSprite.getHeight());
    }

    public void draw(SpriteBatch batch){
        this.updateLocation();
        getSprite().draw(batch);
        
    }

    public void reduceHealth() {

        int health = this.characterHealth.getHealth();
        int maxHealth =  this.characterHealth.getMaxHealth();
        Sprite sprite = this.getSprite();

        if ((originalHealthBarWidth - (originalHealthBarWidth
                - ((float) health / maxHealth) * originalHealthBarWidth)) < 0) {
            sprite.setSize(0, sprite.getHeight());

        } else {

            sprite.setSize(
                    originalHealthBarWidth - (originalHealthBarWidth
                            - ((float) health / maxHealth) * originalHealthBarWidth),
                    sprite.getHeight());

            System.out.println("Health: " +  this.characterHealth.getHealth());
        }
    }



}


