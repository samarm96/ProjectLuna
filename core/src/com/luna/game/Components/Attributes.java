package com.luna.game.Components;

public class Attributes extends Component {
    
    int attack;
    private static final Integer COMPONENT_NUMBER = ComponentNumberList.ATTRIBUTES;

    public Attributes(int attack){
        this.attack = attack;
        setComponentNo(COMPONENT_NUMBER);
    }

    public int getAttack(){
        return this.attack;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }
}
