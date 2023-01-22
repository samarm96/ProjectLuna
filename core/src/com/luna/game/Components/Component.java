package com.luna.game.Components;

public class Component {
    
    private int componentNo;

    public Component(){
        
    }

    public int getComponentNo(){
        return componentNo;
    }

    protected void setComponentNo(int n){
        this.componentNo = n;
    }
}
