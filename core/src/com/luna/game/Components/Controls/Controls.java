package com.luna.game.Components.Controls;

import com.luna.game.Screens.ProjectLuna;

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
    
    public void openInventory(ProjectLuna game);
}
