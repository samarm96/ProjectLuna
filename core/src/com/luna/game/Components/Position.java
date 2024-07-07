package com.luna.game.Components;

public class Position extends Component {
    float x;
    float y;
    private static final Integer COMPONENT_NUMBER = ComponentNumberList.POSITION;

    public Position(float x, float y){
        this.x = x;
        this.y = y;
        setComponentNo(COMPONENT_NUMBER);
    }

    public float[] getPos(){
        return new float[]{this.x,this.y};
    }

    public void setPos(float[] pos){
        this.x = pos[0];
        this.y = pos[1];
    }
}
