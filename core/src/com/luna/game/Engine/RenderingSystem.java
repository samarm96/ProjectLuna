package com.luna.game.Engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.luna.game.Entities.Entity;

public class RenderingSystem {

    private SpriteBatch batch; // a reference to our spritebatch
    private Array<Entity> renderQueue; // an array used to allow sorting of images allowing us to draw images on top of each other
    //private Comparator<Entity> comparator; // a comparator to sort images based on the z position of the transfromComponent
    private OrthographicCamera camera; // a reference to our camera
    
    final public static int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
    final public static int WORLD_WIDTH = Utilities.WORLD_WIDTH;

    public RenderingSystem(SpriteBatch batch){

        renderQueue = new Array<Entity>();

        this.batch = batch; 
        OrthographicCamera camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        this.camera = camera;
    }

    public void update(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();
    }

    public void addEntity(Entity entity){
        renderQueue.add(entity);
    }
    
    public void updateCamera() {
        OrthographicCamera camera = new OrthographicCamera(Utilities.WORLD_WIDTH, Utilities.WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        this.camera = camera;
    }

    public OrthographicCamera getCamera(){
        return this.camera;
    }
}
