package com.luna.game.Components.Controls;

import com.badlogic.gdx.math.Polygon;

public interface Controls {
    
    /**
     * Moves the character left
     */
    public void moveLeft();


    /**
     * Moves the character right
     */    
    public void moveRight();
    
    /**
     * Moves the character down
     */
    public void moveDown();

    /**
     * Moves the character up
     */    
    public void moveUp();   

    /**
     * Performs a basic attack
     */
    public Polygon attack();


    
}
