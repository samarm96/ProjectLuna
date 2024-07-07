package com.luna.game.Entities;

import java.util.HashMap;
import java.util.Map;
import com.luna.game.Components.Attributes;
import com.luna.game.Components.Collision;
import com.luna.game.Components.Component;
import com.luna.game.Components.ComponentNumberList;
import com.luna.game.Components.Health;
import com.luna.game.Components.Position;
import com.luna.game.Components.SpriteComp;

public class Entity {

    private String name;

    private Map<Integer, Component> componentMap;

    public Entity(String name) {
        this.componentMap = new HashMap<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addComponent(Component component) {
        componentMap.put(component.getComponentNo(), component);
    }

    public void removeComponent(Integer componentNo) {
        componentMap.remove(componentNo);
    }

    public Component getComponent(Integer componentNo) {
        if(componentMap.containsKey(componentNo)){
            return componentMap.get(componentNo);
        } else {
            throw new RuntimeException();
        }
    }


    public Health getHealth(){
        return (Health) componentMap.get(ComponentNumberList.HEALTH);
    }
    public SpriteComp getSpriteComponent(){
        return  (SpriteComp) componentMap.get(ComponentNumberList.SPRITECOMP);
    }
    public Position getPosition(){
        return  (Position) componentMap.get(ComponentNumberList.POSITION);
    }

    public Attributes getAttributes(){
        return  (Attributes) componentMap.get(ComponentNumberList.ATTRIBUTES); 
    }   

    public Collision getCollision(){
        return  (Collision) componentMap.get(ComponentNumberList.COLLISION);
    }   


}
