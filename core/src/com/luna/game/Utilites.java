package com.luna.game;

import com.badlogic.gdx.math.Rectangle;

public class Utilites {
    

    public static boolean CollisonCheck(Rectangle object1, Rectangle object2){


        if(object1.overlaps(object2)){
            return true;
        } else {
            return false;
        }
       
    }
    
}
