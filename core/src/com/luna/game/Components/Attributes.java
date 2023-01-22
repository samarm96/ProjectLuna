package com.luna.game.Components;

public class Attributes extends Component {
    
    int attack;

    public Attributes(int attack){
        this.attack = attack;
        setComponentNo(5);
    }

    public int getAttack(){
        return this.attack;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }
}
