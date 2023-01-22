package com.luna.game.Components;

public class Position extends Component {
    float x;
    float y;

    public Position(float x, float y){
        this.x = x;
        this.y = y;
        setComponentNo(1);
    }

    public float[] getPos(){
        return new float[]{this.x,this.y};
    }

    public void setPos(float[] pos){
        this.x = pos[0];
        this.y = pos[1];
    }
}
