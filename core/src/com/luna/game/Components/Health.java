package com.luna.game.Components;

public class Health extends Component {
    
    private int health;
    private int maxHealth;
    private static final Integer COMPONENT_NUMBER = ComponentNumberList.HEALTH;

    public Health(int maxHealth, int health){
        this.maxHealth = maxHealth;
        this.health = health;
        setComponentNo(COMPONENT_NUMBER);        
    }

    /**
     * Getter for the player's max health parameter.
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Setter for the player's max health parameter.
     */
    public void setMaxHealth(int health) {
        this.maxHealth = health;
    }

    /**
     * Getter for the player's health parameter.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Setter for the player's health parameter.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Adds a given amount of health the the player's total health. If the health added puts the
     * total health over 100, the health is maintained at 100
     * 
     * @param healthIncrement the amount of health to add
     */
    public void addHealth(int healthIncrement) {
        if ((this.health += healthIncrement) > 100) {
            this.health = 100;
        } else {
            this.health += healthIncrement;
        }
    }

    /**
     * Subtracts a given amount of health the the player's total health. If the health subtracted
     * puts the total health under 0, the health is maintained at 0
     * 
     * @param healthIncrement the amount of health to remove
     */
    public void removeHealth(int healthIncrement) {
        if ((this.health -= healthIncrement) < 0) {
            this.health = 0;
        } else {
            this.health -= healthIncrement;
        }
    }
}
