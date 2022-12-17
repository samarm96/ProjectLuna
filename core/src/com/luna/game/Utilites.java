package com.luna.game;

import com.badlogic.gdx.math.Rectangle;

public class Utilites {
    

    public static boolean CollisonCheck(Rectangle object1, Rectangle object2){

        float objectOneRightBounds = object1.x+object1.width;
        float objectOneLeftBounds = object1.x;
        float objectOneTopBounds = object1.y + object1.height;
        float objectOneBottomBounds = object1.y;

        float objectTwoRightBounds = object2.x+object2.width;
        float objectTwoLeftBounds = object2.x;
        float objectTwoTopBounds = object2.y + object2.height;
        float oobjectTwoBottomBounds = object2.y;

        if((objectOneLeftBounds >= objectTwoLeftBounds || objectOneRightBounds >= objectTwoLeftBounds) && (objectOneLeftBounds <= objectTwoRightBounds || objectOneRightBounds <= objectTwoRightBounds)){
            if((objectOneBottomBounds >= oobjectTwoBottomBounds || objectOneTopBounds >= oobjectTwoBottomBounds) && (objectOneBottomBounds <= objectTwoTopBounds || objectOneTopBounds <= objectTwoTopBounds)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
