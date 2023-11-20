package com.luna.game.Entities;

import java.util.Optional;

import com.badlogic.gdx.utils.Array;
import com.luna.game.Components.Attributes;
import com.luna.game.Components.Collision;
import com.luna.game.Components.Component;
import com.luna.game.Components.Health;
import com.luna.game.Components.Position;
import com.luna.game.Components.SpriteComp;

public class Entity {

    private Array<Component> components;
    private String name;

    public Entity(String name) {
        this.components = new Array<Component>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(String s) {
        int num = 0;

        // Assign a number based on the component provided
        switch (s) {
            case "Position":
                num = 1;
                for (int i = 0; i < components.size; i++) {
                    if (components.get(i).getComponentNo() == num) {
                        components.removeIndex(i);
                    }
                }
            case "Sprite":
                num = 2;
                for (int i = 0; i < components.size; i++) {
                    if (components.get(i).getComponentNo() == num) {
                        components.removeIndex(i);
                    }
                }
            case "Health":
                num = 3;
                for (int i = 0; i < components.size; i++) {
                    if (components.get(i).getComponentNo() == num) {
                        components.removeIndex(i);
                    }
                }
            case "Collision":
                num = 4;
                for (int i = 0; i < components.size; i++) {
                    if (components.get(i).getComponentNo() == num) {
                        components.removeIndex(i);
                    }
                }
            case "Attributes":
                num = 5;
                for (int i = 0; i < components.size; i++) {
                    if (components.get(i).getComponentNo() == num) {
                        components.removeIndex(i);
                    }
                }
        }
        // TODO: Need to add exception here if the wrong input was given
        if (num == 0) {

        }



    }

    public Optional<Component> getComponent(String s) {
        int num = 0;
        //
        // Assign a number based on the component provided
        switch (s) {
            case "Position":
                num = 1;
                for (Component component : components) {
                    if (component.getComponentNo() == num) {
                        return Optional.of(component);
                    }
                }
            case "Sprite":
                num = 2;
                for (Component component : components) {
                    if (component.getComponentNo() == num) {
                        return Optional.of(component);
                    }
                }
            case "Health":
                num = 3;
                for (Component component : components) {
                    if (component.getComponentNo() == num) {
                        return Optional.of(component);
                    }
                }
            case "Collision":
                num = 4;
                for (Component component : components) {
                    if (component.getComponentNo() == num) {
                        return Optional.of(component);
                    }
                }
            case "Attributes":
                num = 5;
                for (Component component : components) {
                    if (component.getComponentNo() == num) {
                        return Optional.of(component);
                    }
                }
        }

        // TODO: Need to add exception here if the wrong input was given
        if (num == 0) {
            return Optional.empty();
        }

        return Optional.empty();
    }


    public Health getHealth(){
        return  (Health) getComponent("Health").get(); 
    }
    public SpriteComp getSpriteComponent(){
        return  (SpriteComp) getComponent("Sprite").get(); 
    }
    public Position getPosition(){
        return  (Position) getComponent("Position").get(); 
    }

    public Attributes getAttributes(){
        return  (Attributes) getComponent("Attributes").get(); 
    }   

    public Collision getCollision(){
        return  (Collision) getComponent("Collision").get(); 
    }   


}
