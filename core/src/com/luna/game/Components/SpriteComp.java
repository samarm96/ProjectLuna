package com.luna.game.Components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComp extends Component {
    Sprite sprite;

    public SpriteComp(Texture image, float[] initialLocation, float[] initialDimensions){
        this.sprite = new Sprite(image);
        this.sprite.setPosition(initialLocation[0], initialLocation[1]);
        this.sprite.setSize(initialDimensions[0], initialDimensions[1]);
        setComponentNo(2);
    }

    public Sprite getSprite(){
        return this.sprite;
    }


}
