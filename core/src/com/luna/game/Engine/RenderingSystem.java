package com.luna.game.Engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.luna.game.Entities.Entity;
import com.luna.game.Utilities.Constants;



public class RenderingSystem {

    private SpriteBatch batch; // a reference to our spritebatch
    private Array<Entity> renderQueue; // an array used to allow sorting of images allowing us to draw images on top of each other
    //private Comparator<Entity> comparator; // a comparator to sort images based on the z position of the transfromComponent
    private OrthographicCamera camera; // a reference to our camera
    
    public RenderingSystem(SpriteBatch batch){

        renderQueue = new Array<Entity>();

        this.batch = batch; 
        OrthographicCamera camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
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
    
    public void updateCamera(int width, int height) {

        camera.setToOrtho(false, width, height);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

    public OrthographicCamera getCamera(){
        return this.camera;
    }
}

/* 

public class RenderingSystem {

    private SpriteBatch batch; // a reference to our spritebatch
    private Array<Entity> renderQueue; // an array used to allow sorting of images allowing us to
                                       // draw images on top of each other
    // private Comparator<Entity> comparator; // a comparator to sort images based on the z position
    // of the transfromComponent
    private OrthographicCamera camera; // a reference to our camera

    final public static int WORLD_HEIGHT = Constants.WORLD_HEIGHT;
    final public static int WORLD_WIDTH = Constants.WORLD_WIDTH;

    public RenderingSystem(SpriteBatch batch) {

        renderQueue = new Array<Entity>();

        this.batch = batch;
        OrthographicCamera camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        this.camera = camera;
    }

    public void update() {
        camera.update();
    }

    public void addEntity(Entity entity) {
        renderQueue.add(entity);
    }

    public void updateCamera(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }
}
*/