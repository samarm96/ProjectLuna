package com.luna.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

	final static int SCREEN_HEIGHT = Gdx.graphics.getHeight();
	final static int SCREEN_WIDTH = Gdx.graphics.getWidth();
	final ProjectLuna game;

	OrthographicCamera camera;
	Rectangle bucket;
    Texture playerSprite;
	Texture wallImage;
	float lastBucketY;
	float lastBucketX;
	Wall wall;
	List<Wall> walls;
	public GameScreen(final ProjectLuna game) {
		this.game = game;

        playerSprite = new Texture("../assets/kirby.jpg");
		wallImage = new Texture("../assets/wall.jpg");

		// -------- CREATE CAMERA --------
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// -------- REPRESENT PLAYER AS A RECTANGLE
		bucket = new Rectangle();
		bucket.x = Gdx.graphics.getWidth() / 4; // center the bucket horizontally
		bucket.y = Gdx.graphics.getHeight() / 4; // bottom left corner of the bucket is 20 pixels above
						// the bottom screen edge
		bucket.width = 12;
		bucket.height = 12;

		// -------- REPRESENT WALLS AS RECTANGLES --------
		//walls = RenderingFunctions.RenderWallLine(wallImage, SCREEN_WIDTH/2, SCREEN_HEIGHT/2, SCREEN_WIDTH/20, SCREEN_HEIGHT/20, 5, false, false);
		wall = new Wall(wallImage);
		float wallWidth = SCREEN_WIDTH/20;
		float wallHeight = SCREEN_HEIGHT/20;
		wall.setDimensions(wallWidth, wallHeight);
        walls = RenderingFunctions.RenderWallLine(wallImage, (float) 0.0, SCREEN_HEIGHT-wall.getHeight(), wall.getWidth(), wall.getHeight(), Math.round(SCREEN_WIDTH/wall.getWidth()), false, false);

		walls = RenderingFunctions.createBoundaryWalls(wallImage, wall.getWidth(), wall.getHeight());

	}

	@Override
	public void render(float delta) {

        // Clear screen with set color
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// tell the camera to update its matrices.
		camera.update();

		// -------- RENDER COORDINATE SYSTEM TO CAMERA --------
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		// -------- DRAW PLAYER CHARACTER 
		game.batch.draw(playerSprite, bucket.x, bucket.y);
		
		// -------- DRAW WALLS --------
		for(int i = 0; i < walls.size();i++){
			game.batch.draw(wallImage, walls.get(i).getLocation()[0], walls.get(i).getLocation()[1]);
		}
		game.batch.end();

		// -------- USER INPUT (KEYBOARD) --------
		if (Gdx.input.isKeyPressed(Keys.LEFT)){
			lastBucketX = bucket.x;
			bucket.x -= 24 * Gdx.graphics.getDeltaTime();
			// Make sure character can't leave edge of screen
			if (bucket.x < 0){
				bucket.x = 0;
			}
			for(int i = 0; i < walls.size();i++){
				if(Utilites.CollisonCheck(bucket, walls.get(i).getWall())){
					bucket.x = lastBucketX;
				}				
			}

        }
		if (Gdx.input.isKeyPressed(Keys.RIGHT)){
			lastBucketX = bucket.x;
			bucket.x += 24 * Gdx.graphics.getDeltaTime();
			// Make sure character can't leave edge of screen
			if (bucket.x > Gdx.graphics.getWidth() - bucket.width){
				bucket.x = Gdx.graphics.getWidth() - bucket.width;
			}
			for(int i = 0; i < walls.size();i++){
				if(Utilites.CollisonCheck(bucket, walls.get(i).getWall())){
					bucket.x = lastBucketX;
				}				
			}      				
        }
		if (Gdx.input.isKeyPressed(Keys.UP)){
			lastBucketY = bucket.y;
			// Make sure character can't leave edge of screen
			if (bucket.y > Gdx.graphics.getHeight() - bucket.height){
				bucket.y = Gdx.graphics.getHeight() - bucket.height;
			}
			// Make sure wall can't be passed
			bucket.y += 24 * Gdx.graphics.getDeltaTime();
			for(int i = 0; i < walls.size();i++){
				if(Utilites.CollisonCheck(bucket, walls.get(i).getWall())){
					bucket.y = lastBucketY;
				}				
			}  		
        }
		if (Gdx.input.isKeyPressed(Keys.DOWN)){
			lastBucketY = bucket.y;
			bucket.y -= 24 * Gdx.graphics.getDeltaTime();
			// Make sure character can't leave edge of screen
			if (bucket.y < 0){
				bucket.y = 0;
			}
			// Make sure wall can't be passed
			for(int i = 0; i < walls.size();i++){
				if(Utilites.CollisonCheck(bucket, walls.get(i).getWall())){
					bucket.y = lastBucketY;
				}				
			}  	
		}

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {

	}

}