package com.luna.game.Components;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComp extends Component {
    Sprite sprite;
    private static final Integer COMPONENT_NUMBER = ComponentNumberList.SPRITECOMP;

    public SpriteComp(Texture texture, float[] initialLocation, float[] initialDimensions){
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(initialLocation[0], initialLocation[1]);
        this.sprite.setSize(initialDimensions[0], initialDimensions[1]);
        setComponentNo(COMPONENT_NUMBER);
    }

    public SpriteComp(TextureRegion texture, float[] initialLocation, float[] initialDimensions){
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(initialLocation[0], initialLocation[1]);
        this.sprite.setSize(initialDimensions[0], initialDimensions[1]);
        setComponentNo(COMPONENT_NUMBER);
    }

    public Sprite getSprite(){
        return this.sprite;
    }

    public void setColorWhite() {
        sprite.setColor(com.badlogic.gdx.graphics.Color.WHITE);
    }

}
